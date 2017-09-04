/*
 * Copyright 2017 Ribose Inc. <https://www.ribose.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ribose.jenkins.plugin.awscodecommittrigger.it.feature.subscribed_branch;

import com.ribose.jenkins.plugin.awscodecommittrigger.it.AbstractFreestyleIT;
import com.ribose.jenkins.plugin.awscodecommittrigger.it.fixture.ProjectFixture;
import com.ribose.jenkins.plugin.awscodecommittrigger.it.mock.MockGitSCM;
import hudson.plugins.git.BranchSpec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RunWith(Parameterized.class)
public class JenkinsIT extends AbstractFreestyleIT {

    @Parameterized.Parameter
    public String name;

    @Parameterized.Parameter(1)
    public ProjectFixture fixture;

    /** Subscribe branches integration test, Freestyle Job SCM (type="IR") used as default SCM

    ------------------------------------------------------------------------------------------
    | Trigger on Branch             | Event coming for branch              | Job should RUN? |
    ------------------------------------------------------------------------------------------
    | foo                           | refs/heads/foo                       | True            |
    | refs/heads/foo                |                                      |                 |
    ------------------------------------------------------------------------------------------
    | refs/heads/foo/bar            | refs/heads/foo/bar                   | True            |
    ------------------------------------------------------------------------------------------
    | refs/heads/foo/bar/foo        | refs/heads/foo/bar/foo               | True            |
    ------------------------------------------------------------------------------------------
    | refs/heads/foo/bar/foo        | refs/heads/foo/bar                   | False           |
    ------------------------------------------------------------------------------------------
    | *foo                          | refs/heads/foo-bar                   | False           |
    |                               | refs/heads/bar/foo                   |                 |
    |                               | refs/heads/foo/bar                   |                 |
    ------------------------------------------------------------------------------------------
    | *foo                          | refs/heads/bar/foo                   | True            |
    |                               | refs/heads/bar-foo                   |                 |
    ------------------------------------------------------------------------------------------
    | foo*                          | refs/heads/foo/bar                   | False           |
    |                               | refs/heads/bar/foo                   |                 |
    |                               | refs/heads/bar-foo                   |                 |
    ------------------------------------------------------------------------------------------
    | foo*                          | refs/heads/bar/foo                   | True            |
    |                               | refs/heads/foo-bar                   |                 |
    ------------------------------------------------------------------------------------------
    | *                             | refs/heads/foo/bar                   | False           |
    |                               | refs/heads/bar/foo                   |                 |
    |                               | refs/heads/bar/foo                   |                 |
    ------------------------------------------------------------------------------------------
    | *                             | refs/heads/foo                       | True            |
    |                               | refs/heads/foo-bar                   |                 |
    ------------------------------------------------------------------------------------------
    | foo**                         | refs/heads/bar/foo                   | False           |
    |                               | refs/heads/bar/foo                   |                 |
    |                               | refs/heads/bar/foo-bar               |                 |
    |                               | refs/heads/bar/foo/bar               |                 |
    ------------------------------------------------------------------------------------------
    | foo**                         | refs/heads/foo/bar                   | True            |
    |                               | refs/heads/foo-bar                   |                 |
    ------------------------------------------------------------------------------------------
    | **                            | refs/heads/foo/bar                   | True            |
    |                               | refs/heads/bar/foo                   |                 |
    |                               | refs/heads/bar/foo                   |                 |
    |                               | refs/heads/foo                       |                 |
    |                               | refs/heads/foo-bar                   |                 |
    ------------------------------------------------------------------------------------------
    * */

    @Parameters(name = "{0}")
    public static List<Object[]> fixtures() {
        String scmUrl = ((MockGitSCM) defaultSCM).getUrl();

        return Arrays.asList(new Object[][]{
            {
                "should_trigger_branches_without_wildcard_1",
                new ProjectFixture()//without wildcard
                    .setSendBranches("refs/heads/foo")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("foo"))))
                    .setShouldStarted(true)
            },
            {
                "should_trigger_branches_without_wildcard_2",
                new ProjectFixture()//without wildcard
                    .setSendBranches("refs/heads/foo")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("refs/heads/foo"))))
                    .setShouldStarted(true)
            },
            {
                "should_trigger_branches_without_wildcard_3",
                new ProjectFixture()//without wildcard
                    .setSendBranches("refs/heads/foo/bar")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("refs/heads/foo/bar"))))
                    .setShouldStarted(true)
            },
            {
                "should_trigger_branches_without_wildcard_4",
                new ProjectFixture()//without wildcard
                    .setSendBranches("refs/heads/foo/bar/foo")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("refs/heads/foo/bar/foo"))))
                    .setShouldStarted(true)
            },
            {
                "should_trigger_branches_without_wildcard_5",
                new ProjectFixture()//without wildcard
                    .setSendBranches("refs/heads/foo/bar/foo")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("refs/heads/foo/bar/foo"))))
                    .setShouldStarted(true)
            },
            {
                "should_not_trigger_prefix_wildcard_branches_1",
                new ProjectFixture()//without wildcard
                    .setSendBranches("refs/heads/foo/bar/foo")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("refs/heads/foo/bar"))))
                    .setShouldStarted(false)
            },
            {
                "should_not_trigger_prefix_wildcard_branches_2",
                new ProjectFixture()//prefix wildcard
                    .setSendBranches("refs/heads/foo-bar", "refs/heads/bar/foo", "refs/heads/foo/bar")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("*foo"))))
                    .setShouldStarted(false)
            },
            {
                "should_trigger_prefix_wildcard_branches",
                new ProjectFixture()//prefix wildcard
                    .setSendBranches("refs/heads/bar/foo", "refs/heads/bar-foo")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("*foo"))))
                    .setShouldStarted(true),//triggered because of msg "refs/heads/bar-foo"

            },
            {
                "should_not_trigger_suffix_wildcard_branches",
                new ProjectFixture()//suffix wildcard
                    .setSendBranches("refs/heads/foo/bar", "refs/heads/bar/foo", "refs/heads/bar-foo")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("foo*"))))
                    .setShouldStarted(false)
            },
            {
                "should_trigger_suffix_wildcard_branches",
                new ProjectFixture()//suffix wildcard
                    .setSendBranches("refs/heads/bar/foo", "refs/heads/foo-bar")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("foo*"))))
                    .setShouldStarted(true),
            },
            {
                "should_not_trigger_single_star_branches",
                new ProjectFixture()// "*"
                    .setSendBranches("refs/heads/foo/bar", "refs/heads/bar/foo", "refs/heads/bar/foo")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("*"))))
                    .setShouldStarted(false),
            },
            {
                "should_trigger_single_star_branches",
                new ProjectFixture()// "*"
                    .setSendBranches("refs/heads/foo", "refs/heads/foo-bar")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("*"))))
                    .setShouldStarted(true),
            },
            {
                "should_not_trigger_double_stars_branches",
                new ProjectFixture()// "**"
                    .setSendBranches("refs/heads/bar/foo", "refs/heads/bar/foo", "refs/heads/bar/foo-bar", "refs/heads/bar/foo/bar")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("foo**"))))
                    .setShouldStarted(false),
            },
            {
                "should_trigger_double_stars_branches",
                new ProjectFixture()// "**"
                    .setSendBranches("refs/heads/foo/bar", "refs/heads/foo-bar")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("foo**"))))
                    .setShouldStarted(true)
            },
            {
                "should_trigger_all_branches",
                new ProjectFixture()// "**"
                    .setSendBranches("refs/heads/foo/bar", "refs/heads/bar/foo", "refs/heads/bar/foo", "refs/heads/foo", "refs/heads/foo-bar")
                    .setScm(MockGitSCM.fromUrlAndBranchSpecs(scmUrl, Collections.singletonList(new BranchSpec("**"))))
                    .setShouldStarted(true),
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
