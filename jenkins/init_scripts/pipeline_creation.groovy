import hudson.plugins.git.*;
import jenkins.model.Jenkins
import hudson.model.*

// pipeline creation
def scm = new GitSCM("https://github.com/josembar/liquibase-unit-test-python.git")
scm.branches = [new BranchSpec("*/main")];

def pipelineDefinition = ["liquibase-run-update": "Jenkinsfile-Update", "liquibase-run-rollback": "Jenkinsfile-Rollback", "database-unit-test": "Jenkinsfile-UnitTest"]
def flowDefinition, parent, job

for (element in pipelineDefinition) {
  	flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition(scm, element.value)
	parent = Jenkins.instance
	job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(parent, element.key)
	job.definition = flowDefinition
	if (element.key == "liquibase-run-rollback") {
		List<ParameterDefinition> newParams = new ArrayList<>()
		newParams.add(new StringParameterDefinition("count", "1", "Number of changesets to rollback", true))
		job.addProperty(new ParametersDefinitionProperty(newParams))
	}
	parent.reload()
}