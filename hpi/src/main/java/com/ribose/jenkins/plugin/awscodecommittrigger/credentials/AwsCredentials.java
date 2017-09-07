package com.ribose.jenkins.plugin.awscodecommittrigger.credentials;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.cloudbees.plugins.credentials.CredentialsNameProvider;
import com.cloudbees.plugins.credentials.NameWith;
import com.cloudbees.plugins.credentials.common.StandardCredentials;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Util;
import hudson.util.Secret;
import org.apache.commons.lang3.StringUtils;

@NameWith(value = AwsCredentials.NameProvider.class, priority = 1)
public interface AwsCredentials extends StandardCredentials, AWSCredentials, AWSCredentialsProvider {
    String getDisplayName();

    class NameProvider extends CredentialsNameProvider<AwsCredentials> {

        @NonNull
        @Override
        public String getName(@NonNull AwsCredentials credentials) {
            String displayName = credentials.getDisplayName();
            if (StringUtils.isNotBlank(displayName)) {
                return displayName;
            }

            String desc = credentials.getDescription();
            if (StringUtils.isBlank(desc)) {
                desc = "--no desc--";
            }
            else {
                desc = desc.trim().substring(0, 10) + "...";
            }
            return String.format("%sxxx (%s)", credentials.getAWSSecretKey().substring(0, 5), desc);
        }
    }
}
