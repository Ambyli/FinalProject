//This file will be used to locally change the git branch to be built
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
import java.io.IOException;
import java.io.InputStream;

import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;

public class BranchBuilder extends Builder implements SimpleBuildStep {

	private String branch;
    private String runCommand;
    
    public BranchBuilder() {
    	this.branch = "master"; //default branch is usually master
    	this.runCommand = "git checkout" + branch;
    }

    @DataBoundConstructor
    public BranchBuilder(String branch) {
        this.branch = branch;
        this.runCommand = "git checkout" + branch;
    }

    public String getCommand() {
        return runCommand;
    }
    
    public String getBranch() {
    	return branch;
    }
    
    public String execBranchSearch() throws IOException {
    	String command = "git branches -a";
		Process child = Runtime.getRuntime().exec(command);
    	InputStream in = child.getInputStream();
    	/*int c;
    	while((c = in.read()) != -1)
    	{
    		System.out.println((char) c);
    	}*/
    	in.close();
    	return null;
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
    	run.addAction(new BranchAction("Current Branch: " + branch));
    	Runtime rt = Runtime.getRuntime();
       	Process pr = rt.exec(runCommand); //pr doesnt need to be used so ignore warning
    }
    
    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public FormValidation doCheckName(@QueryParameter String value, @QueryParameter boolean useFrench)
                throws IOException, ServletException {
            if (value.length() == 0)
                return FormValidation.error(Messages._BranchBuilder_DescriptorImpl_errors_missingName());
            if (value.length() < 4)
                return FormValidation.warning(Messages._BranchBuilder_DescriptorImpl_warnings_tooShort());
            if (!useFrench && value.matches(".*[éáàç].*")) {
                return FormValidation.warning(Messages._BranchBuilder_DescriptorImpl_warnings_reallyFrench());
            }
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return Messages._BranchBuilder_DescriptorImpl_DisplayName();
        }

    }
}
