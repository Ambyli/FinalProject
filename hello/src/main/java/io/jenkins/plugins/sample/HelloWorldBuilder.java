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

public class HelloWorldBuilder extends Builder implements SimpleBuildStep {

    private String branch = "master"; //holds the branch of workspace
    private String runCommand; //holds the command for swapping branch of workspace
    private HelloWorldAction Use = new HelloWorldAction(branch);
    
    //default constructor
    /*public HelloWorldBuilder() {
    	this.branch = "master"; //default branch is usually master
    	this.runCommand = "git checkout" + branch;
    }*/

    //custom constructor
    @DataBoundConstructor
    public HelloWorldBuilder(String branch) {
    	this.branch = "master";
        this.runCommand = "git checkout" + branch;
    }

    //getter function
    public String getBranch() {
        return branch;
    }
    
    //getter function
    public String getCommand() {
        return runCommand;
    }
    
    //This function retrieves the list of branches for the given project
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

    //this function creates an instance in the build cycle
    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
    	run.addAction(Use);
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("git checkout test"); //pr doesnt need to be used so ignore warning
    }

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return Messages.HelloWorldBuilder_DescriptorImpl_DisplayName();
        }

    }

}
