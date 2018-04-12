//This file will be used to locally change the git branch to be built
package io.jenkins.plugins.sample;

import java.lang.Runtime;
import hudson.Launcher;
//import hudson.Extension;
import hudson.FilePath;
//import hudson.util.FormValidation;
//import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
//import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
//import org.kohsuke.stapler.QueryParameter;

//import javax.servlet.ServletException;
import java.io.IOException;
import jenkins.tasks.SimpleBuildStep;
//import org.jenkinsci.Symbol;
//import org.kohsuke.stapler.DataBoundSetter;
import java.io.InputStream;

public class Branch extends Builder implements SimpleBuildStep {

    private String runCommand;
    
    public Branch() {
    	this.runCommand = "git checkout master"; //default branch is usually master
    }

    @DataBoundConstructor
    public Branch(String runCommand) {
        this.runCommand = "git checkout " + runCommand;
    }

    public String getCommand() {
        return runCommand;
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
    	run.addAction(new BranchAction("Current Branch: " + runCommand));
    	Runtime rt = Runtime.getRuntime();
       	Process pr = rt.exec(runCommand); //pr doesnt need to be used so ignore warning
    }
}
