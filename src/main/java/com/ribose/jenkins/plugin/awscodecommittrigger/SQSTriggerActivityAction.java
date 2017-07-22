package com.ribose.jenkins.plugin.awscodecommittrigger;

import com.ribose.jenkins.plugin.awscodecommittrigger.logging.Log;
import hudson.model.Action;
import hudson.model.Job;
import hudson.util.FormValidation;
import org.apache.commons.io.FileUtils;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SQSTriggerActivityAction implements Action {

    private static final Log log = Log.get(SQSTriggerActivityAction.class);

    private final transient Job job;
    private final transient String sqsLogPath;
    private final transient File sqsLogFile;

    private static final String SQS_LOG_NAME = "sqs-activity.log";
    private static final Long DEFAULT_BIG_SIZE = 10L;

    public SQSTriggerActivityAction(Job job) {
        this.job = job;
        this.sqsLogFile = new File(this.job.getRootDir().getAbsolutePath(), SQS_LOG_NAME);
        this.sqsLogPath = this.sqsLogFile.getAbsolutePath();
    }

    @Override
    public String getIconFileName() {
        return "clipboard.png";
    }

    @Override
    public String getDisplayName() {
        return "SQS Activity";
    }

    @Override
    public String getUrlName() {
        return "SQSActivity";
    }

    public String getSqsLogPath() {
        return sqsLogPath;
    }

    public void doRaw() throws ServletException, IOException {
        StaplerRequest request = Stapler.getCurrentRequest();
        StaplerResponse response = Stapler.getCurrentResponse();
        FileInputStream is = FileUtils.openInputStream(this.sqsLogFile);
        response.serveFile(request, is, 0L, 60000L, this.sqsLogFile.length(), SQS_LOG_NAME);
    }

    public boolean isBig() {
        long sizeInMB = this.sqsLogFile.length() / 1048576L; // 1048576 B = 1 MB
        return sizeInMB > DEFAULT_BIG_SIZE;
    }

    public String getLogSize() {
        return FileUtils.byteCountToDisplaySize(this.sqsLogFile.length());
    }

    public String getLog() throws IOException {
        return FileUtils.readFileToString(this.sqsLogFile, "UTF-8");
    }

    public FormValidation doClear() {
        try {
            FileUtils.write(new File(this.sqsLogPath), "");
        } catch (IOException e) {
            return FormValidation.error(e, "Unable clear Activity");
        }
        return FormValidation.ok("Done. Please refresh the page.");
    }

    public File getSqsLogFile() {
        return sqsLogFile;
    }
}
