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

public class Branch extends Builder implements SimpleBuildStep {

    private final String name;

    @DataBoundConstructor
    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
    	Runtime rt = Runtime.getRuntime();
       	Process pr = rt.exec("git checkout name"); //pr doesnt need to be used so ignore warning
    }
}
