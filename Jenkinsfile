pipeline {
    agent any
    stages {
        stage('Check status') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'mysql_credentials', passwordVariable: 'MYSQL_PASSWORD', usernameVariable: 'MYSQL_USER')]) {
                    //sh 'liquibase --version'
                    sh 'printenv'
                    sh '''
                        liquibase status \
                        --url=jdbc:mysql://mysqldb:3306/demo?allowPublicKeyRetrieval=true&useSSL=False \
                        --search-path=$WORKSPACE \
                        --changeLogFile=changelog.xml \
                        --username=$MYSQL_USER \
                        --password=$MYSQL_PASSWORD
                    '''
                }
            }
        }
    }
}