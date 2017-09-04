package com.ribose.jenkins.plugin.awscodecommittrigger.it.feature.scmconfig;

import com.ribose.jenkins.plugin.awscodecommittrigger.it.AbstractFreestyleIT;
import com.ribose.jenkins.plugin.awscodecommittrigger.it.fixture.ProjectFixture;
import com.ribose.jenkins.plugin.awscodecommittrigger.it.mock.MockGitSCM;
import hudson.plugins.git.BranchSpec;
import hudson.scm.NullSCM;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class JenkinsIRIT extends AbstractFreestyleIT {
    @Parameterized.Parameter
    public String name;

    @Parameterized.Parameter(1)
    public ProjectFixture fixture;

    /* Freestyle Job SCM integration test (type="IR")

    -----------------------------------------------------------------------------------------
    | Job SCM                          | Event coming                     | Job should RUN? |
    -----------------------------------------------------------------------------------------
    | branch: "refs/heads/foo"         | branch: "refs/heads/foo"         | True            | branch matched
    -----------------------------------------------------------------------------------------
    | branch: "refs/heads/foo"         | branch: "refs/heads/bar"         | False           | no branch not match
    -----------------------------------------------------------------------------------------
    | git_url: undefined               | branch: "refs/heads/bar"         | False           | scm is undefined
    |                                  |                                  |                 |
    -----------------------------------------------------------------------------------------
    | branch: undefined                | branch: "refs/heads/bar"         | False           | branch is undefined
    -----------------------------------------------------------------------------------------
    */

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> fixtures() {
        String scmUrl = ((MockGitSCM) defaultSCM).getUrl();

        return Arrays.asList(new Object[][]{
            {
                "branch matched",
                new ProjectFixture()
                    .setSendBranches("refs/heads/foo")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("refs/heads/foo"))))
                    .setShouldStarted(true)
            },
            {
                "no branch not match",
                new ProjectFixture()
                    .setSendBranches("refs/heads/bar")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("refs/heads/foo"))))
                    .setShouldStarted(false)
            },
            {
                "scm is undefined",
                new ProjectFixture()
                    .setSendBranches("refs/heads/bar")
                    .setScm(new NullSCM())
                    .setShouldStarted(false)
            },
            {
                "branch is undefined",
                new ProjectFixture()
                    .setSendBranches("refs/heads/bar")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.<BranchSpec>emptyList()))
                    .setShouldStarted(false)
            }
        });
    }

    @Test
    public void shouldPassIt() throws Exception {
        this.fixture.setSubscribeInternalScm(true);
        this.mockAwsSqs.send(this.fixture.getSendBranches());
        this.submitAndAssertFixture(this.fixture);
    }
}
