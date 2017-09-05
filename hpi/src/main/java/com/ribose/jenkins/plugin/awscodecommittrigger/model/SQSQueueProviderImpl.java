
package com.ribose.jenkins.plugin.awscodecommittrigger.model;

import com.ribose.jenkins.plugin.awscodecommittrigger.SQSTrigger;
import com.ribose.jenkins.plugin.awscodecommittrigger.interfaces.SQSQueue;
import com.ribose.jenkins.plugin.awscodecommittrigger.interfaces.SQSQueueProvider;

import java.util.List;


public class SQSQueueProviderImpl implements SQSQueueProvider {

    @Override
    public List<? extends SQSQueue> getSqsQueues() {
        final SQSTrigger.DescriptorImpl descriptor = SQSTrigger.DescriptorImpl.get();
        return descriptor.getSqsQueues();
    }

    @Override
    public SQSQueue getSqsQueue(final String uuid) {
        final SQSTrigger.DescriptorImpl descriptor = SQSTrigger.DescriptorImpl.get();
        return descriptor.getSqsQueue(uuid);
    }
}
