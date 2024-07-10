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