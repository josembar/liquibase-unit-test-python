--liquibase formatted sql

--changeset jose.barrantes:1
CREATE TABLE test_table (test_id INT NOT NULL, test_column INT, PRIMARY KEY (test_id))
--rollback DROP TABLE test_table

