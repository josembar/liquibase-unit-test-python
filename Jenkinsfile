pipeline {
    agent any
    stages {
        stage('Check status') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'mysql_credentials', passwordVariable: 'MYSQL_PASSWORD', usernameVariable: 'MYSQL_USER')]) {
                    //sh 'liquibase --version'
                    sh 'printenv'
                    sh 'ls -lha $WORKSPACE'
                    sh '''
                        liquibase status \
                        --classpath=/usr/local/bin/liquibase/internal/lib/mysql-connector-j-9.0.0 \
                        --url=jdbc:mysql://mysqldb:3306/demo \
                        --changeLogFile=changelog.xml \
                        --username=$MYSQL_USER \
                        --password=$MYSQL_PASSWORD
                    '''
                }
            }
        }
    }
}