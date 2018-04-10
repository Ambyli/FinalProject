package io.jenkins.plugins.sample;

import hudson.model.Run;
import jenkins.model.RunAction2;

public class BranchAction implements RunAction2 {
	
	private String name;
	private transient Run run; 

    @Override
    public void onAttached(Run<?, ?> run) {
        this.run = run; 
    }

    @Override
    public void onLoad(Run<?, ?> run) {
        this.run = run; 
    }

    public Run getRun() { 
        return run;
    }

    public BranchAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String getIconFileName() {
        return null; //predefined icon bundled with jenkins
    }

    @Override
    public String getDisplayName() {
        return "Branches"; //This is the label used for the side panel
    }

    @Override
    public String getUrlName() {
        return "branches"; //this is the URL fragment used for this action
    }
}