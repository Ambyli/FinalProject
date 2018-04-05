package jenkins.branch;

public class BranchBuilder extends Builder {

  @Override
  public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
    //listener.getLogger().println("Hello there user!"); 
    //  Used to print information out to the jenkins console for the user
    //Run<?, ?> run represents a single run of the jenkins project
    //  Use this to get information about the build: was it successful?, did it fail?, etc.
    //  Get information about the previous build with:
    //  run = run.getPreviousBuild();

  }
} 
