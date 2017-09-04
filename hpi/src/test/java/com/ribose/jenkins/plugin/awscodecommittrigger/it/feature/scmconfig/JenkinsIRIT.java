package com.ribose.jenkins.plugin.awscodecommittrigger.it.feature.scmconfig;

import com.ribose.jenkins.plugin.awscodecommittrigger.it.AbstractFreestyleIT;
import org.junit.Test;

public class JenkinsIRIT extends AbstractFreestyleIT {
    /** Freestyle Job SCM integration test (type="IR")
    ------------------------------------------------------------------------------------------------------------------
    | Job SCM                          | Trigger Configuration  | Event coming                     | Job should RUN? |
    ------------------------------------------------------------------------------------------------------------------
    | git_url: "https://git.url.com"   | type: "IR"             | url: "https://git.url.com"       | True            |
    | branch: "ref/heads/foo"          |                        | branch: "ref/heads/foo"          |                 |
    ------------------------------------------------------------------------------------------------------------------
    | git_url: "https://git.url.com"   | type: "IR"             | url: "https://git.url.com"       | False           |
    | branch: "ref/heads/foo"          |                        | branch: "ref/heads/bar"          |                 |
    ------------------------------------------------------------------------------------------------------------------
    | git_url: undefined               | type: "IR"             | url: "https://git.url.com"       | False           |
    |                                  |                        | branch: "ref/heads/bar"          |                 |
    ------------------------------------------------------------------------------------------------------------------
    | git_url: undefined               | undefined              | url: "https://git.url.com"       | False           |
    |                                  |                        | branch: "ref/heads/bar"          |                 |
    ------------------------------------------------------------------------------------------------------------------
    | git_url: undefined               | undefined              | url: "https://git.url.com"       | False           |
    |                                  |                        | branch: "ref/heads/bar"          |                 |
    ------------------------------------------------------------------------------------------------------------------
    | git_url: "https://git.url.com"   | undefined              | url: "https://git.xxx.com"       | False           |
    |                                  |                        | branch: "ref/heads/bar"          |                 |
    ------------------------------------------------------------------------------------------------------------------
    | git_url: "https://git.url.com"   | type: "IR"             | url: "https://git.url.com"       | ?????           |
    | branch: undefined                |                        | branch: "ref/heads/bar"          |                 |
    ------------------------------------------------------------------------------------------------------------------
    */

    @Test
    public void shouldPassIt() {

    }
}
