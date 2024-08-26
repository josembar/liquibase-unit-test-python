import hudson.EnvVars;
import hudson.slaves.EnvironmentVariablesNodeProperty;
import hudson.slaves.NodeProperty;
import hudson.slaves.NodePropertyDescriptor;
import hudson.util.DescribableList;
import jenkins.model.Jenkins;

public createGlobalEnvironmentVariables(String key, String value){

   Jenkins instance = Jenkins.getInstance();

   DescribableList<NodeProperty<?>, NodePropertyDescriptor> globalNodeProperties = instance.getGlobalNodeProperties();
   List<EnvironmentVariablesNodeProperty> envVarsNodePropertyList = globalNodeProperties.getAll(EnvironmentVariablesNodeProperty.class);

   EnvironmentVariablesNodeProperty newEnvVarsNodeProperty = null;
   EnvVars envVars = null;

   if ( envVarsNodePropertyList == null || envVarsNodePropertyList.size() == 0 ) {
       newEnvVarsNodeProperty = new hudson.slaves.EnvironmentVariablesNodeProperty();
       globalNodeProperties.add(newEnvVarsNodeProperty);
       envVars = newEnvVarsNodeProperty.getEnvVars();
   } else {
       envVars = envVarsNodePropertyList.get(0).getEnvVars();
   }
   envVars.put(key, value)
   instance.save()
}

def environmentVariables = [
    "DRIVER_PATH_LIQUIBASE": "/usr/local/bin/liquibase/internal/lib/mysql-connector-j-9.0.0.jar",
    "DEMO_DB_HOST": "172.17.0.2",
    "DEMO_DB_NAME": "demo",
    "DEMO_DB_PORT": "3306"
]

for (element in environmentVariables) {
    createGlobalEnvironmentVariables(element.key,element.value)
}