pipeline {
    agent any
    stages {
        stage('Check status') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'mysql_credentials', passwordVariable: 'MYSQL_PASSWORD', usernameVariable: 'MYSQL_USER')]) {
                    //sh 'liquibase --version'
                    sh '''
                        liquibase status \
                        --classpath=/usr/local/bin/liquibase/internal/lib/mysql-connector-j-9.0.0.jar \
                        --url=jdbc:mysql://172.17.0.2:3306/demo \
                        --changeLogFile=changelog.xml \
                        --username=$MYSQL_USER \
                        --password=$MYSQL_PASSWORD
                    '''
                }
            }
        }
        stage('Apply changeset') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'mysql_credentials', passwordVariable: 'MYSQL_PASSWORD', usernameVariable: 'MYSQL_USER')]) {
                    sh '''
                        liquibase update \
                        --classpath=/usr/local/bin/liquibase/internal/lib/mysql-connector-j-9.0.0.jar \
                        --url=jdbc:mysql://172.17.0.2:3306/demo \
                        --changeLogFile=changelog.xml \
                        --username=$MYSQL_USER \
                        --password=$MYSQL_PASSWORD
                    '''
                }
            }
        }
    }
}