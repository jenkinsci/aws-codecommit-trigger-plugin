
package com.ribose.jenkins.plugin.awscodecommittrigger.model;

import com.ribose.jenkins.plugin.awscodecommittrigger.SQSTrigger;
import com.ribose.jenkins.plugin.awscodecommittrigger.interfaces.SQSQueue;
import com.ribose.jenkins.plugin.awscodecommittrigger.interfaces.SQSQueueProvider;
import jenkins.model.Jenkins;

import javax.annotation.Nonnull;
import java.util.List;


public class SQSQueueProviderImpl implements SQSQueueProvider {

    @Override
    public List<? extends SQSQueue> getSqsQueues() {
        @Nonnull final SQSTrigger.DescriptorImpl descriptor = (SQSTrigger.DescriptorImpl) Jenkins.getActiveInstance().getDescriptor(SQSTrigger.class);//SQSTrigger.DescriptorImpl.get();
        return descriptor.getSqsQueues();
    }

    @Override
    public SQSQueue getSqsQueue(final String uuid) {
        @Nonnull final SQSTrigger.DescriptorImpl descriptor = (SQSTrigger.DescriptorImpl) Jenkins.getActiveInstance().getDescriptor(SQSTrigger.class);// SQSTrigger.DescriptorImpl.get();
        return descriptor.getSqsQueue(uuid);
    }
}
