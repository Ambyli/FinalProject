package io.jenkins.plugins.sample;

import hudson.model.Action;

public class BranchAction implements Action {
	
	private String name;

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