import hudson.plugins.git.*;
import jenkins.model.Jenkins

def scm = new GitSCM("https://github.com/josembar/liquibase-unit-test-python.git")
scm.branches = [new BranchSpec("*/main")];

def pipelineDefinition = ["liquibase-run-update": "Jenkinsfile-Update", "liquibase-run-rollback": "Jenkinsfile-Rollback", "database-unit-test": "Jenkinsfile-UnitTest"]
def flowDefinition, parent, job

for (element in pipelineDefinition) {
  	flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition(scm, element.value)
	parent = Jenkins.instance
	job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(parent, element.key)
	job.definition = flowDefinition
	parent.reload()
}