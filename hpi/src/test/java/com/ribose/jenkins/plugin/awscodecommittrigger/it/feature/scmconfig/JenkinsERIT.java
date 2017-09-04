package com.ribose.jenkins.plugin.awscodecommittrigger.it.feature.scmconfig;

import com.ribose.jenkins.plugin.awscodecommittrigger.it.AbstractFreestyleIT;
import org.junit.Test;

public class JenkinsERIT extends AbstractFreestyleIT {
    /* Freestyle Job SCM integration test (type="ER")

     ------------------------------------------------------------------------------------------------------------------
     | Job SCM                              | Trigger Configuration  | Event coming                     | Job should RUN? |
     ------------------------------------------------------------------------------------------------------------------
     | git_url: "https://git.private.com"   | git: "https://git.url.com/foo.git"             | url: "https://git.url.com/foo.git"       | True            |
     | branch: "ref/heads/foo"              |                        | branch: "ref/heads/foo"          |                 |


     */

    @Test
    public void shouldPassIt() {

    }
}
