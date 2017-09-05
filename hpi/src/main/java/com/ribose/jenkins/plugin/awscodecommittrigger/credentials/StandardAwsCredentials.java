package com.ribose.jenkins.plugin.awscodecommittrigger.credentials;

import com.amazonaws.auth.AWSCredentials;
import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.impl.BaseStandardCredentials;
import com.ribose.jenkins.plugin.awscodecommittrigger.logging.Log;
import hudson.Extension;
import hudson.util.Secret;
import org.kohsuke.stapler.DataBoundConstructor;

public class StandardAwsCredentials extends BaseStandardCredentials implements AwsCredentials  {

    private static final Log log = Log.get(StandardAwsCredentials.class);

    private final String displayName;
    private final String accessKey;
    private final Secret secretKey;

    @DataBoundConstructor
    public StandardAwsCredentials(CredentialsScope scope, String id, String description, String displayName, String accessKey, String secretKey) {
        super(scope, id, description);

        this.displayName = displayName;
        this.accessKey = accessKey;
        this.secretKey = Secret.fromString(secretKey);
    }

    public String getAccessKey() {
        return accessKey;
    }

    public Secret getSecretKey() {
        return secretKey;
    }

    @Override
    public String getAWSAccessKeyId() {
        return this.accessKey;
    }

    @Override
    public String getAWSSecretKey() {
        return this.secretKey.getPlainText();
    }

    public AWSCredentials getCredentials() {
        return null;
    }

    @Override
    public void refresh() {
        log.debug("no-op method");
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Extension
    public static class DescriptorImpl extends BaseStandardCredentialsDescriptor {

        @Override
        public String getDisplayName() {
            return "Standard Aws Credentials";
        }
    }
}
