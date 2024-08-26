#!/bin/bash

liquibase rollback-count \
    --count=$count \
    --classpath=$DRIVER_PATH_LIQUIBASE \
    --url=jdbc:mysql://$DEMO_DB_HOST:$DEMO_DB_PORT/$DEMO_DB_NAME \
    --changeLogFile=changelog.sql \
    --username=$MYSQL_USER \
    --password=$MYSQL_PASSWORD