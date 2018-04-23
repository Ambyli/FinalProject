package io.jenkins.plugins.sample;

import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;

public class BranchBuilder extends Builder implements SimpleBuildStep {

    private final String name;

    @DataBoundConstructor
    public BranchBuilder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public int makeBranchScript() throws IOException {
    	//this function will make
    	String script = "git checkout " + name;
    	String fileName = "script.sh";
    	File f = new File(fileName);
    	String path = f.getAbsolutePath();
    	PrintWriter writer = new PrintWriter(new FileWriter(f));
        writer.write(script, 0, script.length());
        writer.close();
    	return 0;
    }
    
    public int executeBranchScript() throws IOException, InterruptedException {
    	//this function will execute the script makeBranchScript() made
    	ProcessBuilder pb = new ProcessBuilder("git checkout" + name);
    	Process p = pb.start();
    	p.waitFor();
    	return 0;
    }

   
    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
    	makeBranchScript();
    	executeBranchScript();
    }

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public FormValidation doCheckName(@QueryParameter String value)
                throws IOException, ServletException {
            if (value.length() == 0)
                return FormValidation.error(Messages.BranchBuilder_DescriptorImpl_errors_missingName());
            if (value.length() < 1)
                return FormValidation.warning(Messages.BranchBuilder_DescriptorImpl_warnings_tooShort());
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return Messages.BranchBuilder_DescriptorImpl_DisplayName();
        }

    }

}
