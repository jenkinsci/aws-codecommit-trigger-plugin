package com.ribose.jenkins.plugin.awscodecommittrigger.it.feature.scmconfig;

import org.junit.Test;

public class JenkinsIT {

    /* Freestyle Job SCM integration test

     -----------------------------------------------------------------------------------------------------------------------
     | Job SCM                          | Trigger Configuration       | Event coming                     | Job should RUN? |
     -----------------------------------------------------------------------------------------------------------------------
     | branch: "refs/head/bar"          | type: "IR"                  | branch: "refs/heads/bar"         | True            |
     -----------------------------------------------------------------------------------------------------------------------
     | branch: "refs/head/bar"          | type: "ER"                  | branch: "refs/heads/bar"         | False           |
     |                                  | empty url/branch            |                                  |                 |
     -----------------------------------------------------------------------------------------------------------------------
     | branch: "refs/head/bar"          | type: "ER"                  | branch: "refs/heads/bar"         | True            |
     |                                  | branch: "refs/heads/bar"    |                                  |                 |
     -----------------------------------------------------------------------------------------------------------------------

     */

    @Test
    public void shouldPassIt() {

    }
}
