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
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getUrlName() {
        return null;
    }
}