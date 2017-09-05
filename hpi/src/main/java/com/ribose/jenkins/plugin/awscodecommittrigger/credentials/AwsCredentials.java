package com.ribose.jenkins.plugin.awscodecommittrigger.credentials;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.cloudbees.plugins.credentials.CredentialsNameProvider;
import com.cloudbees.plugins.credentials.NameWith;
import com.cloudbees.plugins.credentials.common.StandardCredentials;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Util;
import hudson.util.Secret;

@NameWith(value = AwsCredentials.NameProvider.class, priority = 1)
public interface AwsCredentials extends StandardCredentials, AWSCredentials, AWSCredentialsProvider {
    String getDisplayName();

//    String getAccessKey();
//    Secret getSecretKey();

    class NameProvider extends CredentialsNameProvider<AwsCredentials> {

        @NonNull
        @Override
        public String getName(@NonNull AwsCredentials credentials) {
            String description = Util.fixEmptyAndTrim(credentials.getDescription());
            return credentials.getDisplayName() + (description != null ? " (" + description + ")" : "");
        }
    }
}
