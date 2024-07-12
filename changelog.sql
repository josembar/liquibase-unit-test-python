--liquibase formatted sql

--changeset jose.barrantes:1
CREATE TABLE test_table (test_id INT NOT NULL, test_column INT, PRIMARY KEY (test_id))
--rollback DROP TABLE test_table

--changeset jose.barrantes:2
INSERT INTO test_table VALUES (1, 1000)
--rollback DELETE FROM test_table

--changeset jose.barrantes:3
INSERT INTO test_table VALUES (10, 10000)
--rollback DELETE FROM test_table WHERE test_id = 10

--changeset jose.barrantes:4
UPDATE test_table
SET test_column = 20000
WHERE test_id = 10
--rollback empty
