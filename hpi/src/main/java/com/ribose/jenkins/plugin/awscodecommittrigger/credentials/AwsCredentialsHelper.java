package com.ribose.jenkins.plugin.awscodecommittrigger.credentials;

import com.cloudbees.plugins.credentials.CredentialsMatchers;
import com.cloudbees.plugins.credentials.CredentialsProvider;
import com.cloudbees.plugins.credentials.domains.DomainRequirement;
import hudson.model.Item;
import hudson.model.ItemGroup;
import hudson.security.ACL;
import org.apache.commons.lang.StringUtils;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import java.util.Collections;

public class AwsCredentialsHelper {

    private AwsCredentialsHelper() {
    }

    @CheckForNull
    public static AwsCredentials getCredentials(@Nullable String credentialsId) {
        if (StringUtils.isBlank(credentialsId)) {
            return null;
        }
        return CredentialsMatchers.firstOrNull(
            CredentialsProvider.lookupCredentials(AwsCredentials.class, (Item) null, ACL.SYSTEM, null, null),
            CredentialsMatchers.withId(credentialsId)
        );
    }
}
