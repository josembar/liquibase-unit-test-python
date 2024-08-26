# Apply changes to your database and do unit testing using Liquibase and Python
## Requirements
* Linux OS or WSL2 if you're using Windows.
* docker and docker compose installed.
## Start the environment
To start the environment run the following command: 
```console
docker compose -f docker-compose.yml up
```
## Clean up
1. First type *ctrl + c* to stop docker compose.
2. Run the following commands:
```console
docker system prune -af
docker volume rm liquibase-unit-test-python_jenkins
```