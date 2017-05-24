// The ArrayList of slaves is not serializable, so fetching them should be marked as @NonCPS so that
// no attempt is made to serialize and save the local state of the function. See here for details:
// https://github.com/jenkinsci/pipeline-plugin/blob/master/TUTORIAL.md#serializing-local-variables
@NonCPS
def getSlaves() {
    def slaves = []
    hudson.model.Hudson.instance.slaves.each {
        slaves << it.name
    }
    return slaves
}

// Run a command on each slave in series
getSlaves().each {
    node(it) {
        bat "hostname"   
    }
}

// Run a command on each slave in parallel
def jobs = [:]
getSlaves().each {
    
    // Use a local variable to avoid closing over a reference to 'it',
    // the value of which changes on each iteration
    def slave = it
    
    // Create a closure for each slave and put it in the map of jobs
    jobs[slave] = {
        node(slave) {
            bat "hostname"
        }
    }
}

// Run the closures in parallel
parallel jobs
