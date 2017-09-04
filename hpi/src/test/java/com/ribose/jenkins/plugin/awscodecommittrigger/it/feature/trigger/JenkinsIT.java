package com.ribose.jenkins.plugin.awscodecommittrigger.it.feature.trigger;

import com.ribose.jenkins.plugin.awscodecommittrigger.it.AbstractFreestyleIT;
import org.junit.Test;

public class JenkinsIT extends AbstractFreestyleIT {

    /* Freestyle Job SCM integration test
     - git_url: https://git-codecommit.us-west-2.amazonaws.com/v1/repos/testjenkins

     ----------------------------------------------------------------------------------------------------------------------
     | Job SCM                          | Trigger Configuration      | Event coming                     | Job should RUN? |
     ----------------------------------------------------------------------------------------------------------------------
     | git_url: undefined               | type: "undefined"          | url: "https://git.url.com"       | False           |
     |                                  |                            | branch: "ref/heads/bar"          |                 |
     ----------------------------------------------------------------------------------------------------------------------
     | git_url: "${git_url}"            | type: "undefined"          | url: "https://git.xxx.com"       | False           |
     |                                  |                            | branch: "ref/heads/bar"          |                 |
     ----------------------------------------------------------------------------------------------------------------------
     | git_url: "{git_url}"             | type: "undefined"          | url: "https://git.xxx.com"       | False           |
     |                                  |                            | branch: "ref/heads/bar"          |                 |
     ----------------------------------------------------------------------------------------------------------------------
     | git_url: "{git_url}"             | type: "IR"                 | url: "${git_url}"                | True            |
     | branch: "ref/heads/foo"          |                            | branch: "ref/heads/foo"          |                 |
     ----------------------------------------------------------------------------------------------------------------------

     * */

}
