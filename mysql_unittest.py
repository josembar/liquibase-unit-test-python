import unittest
from sqlalchemy import create_engine, text
from os import environ

class TestMySQLDBMethods(unittest.TestCase):
    
    @staticmethod
    def run_query(query:str):
        # Create database url
        user = environ['MYSQL_USER']
        password = environ['MYSQL_PASSWORD']
        host = environ['DEMO_DB_HOST']
        port = environ['DEMO_DB_PORT']
        database = environ['DEMO_DB_NAME']
        url = f"mysql+mysqlconnector://{user}:{password}@{host}/{database}"
        # Connect to the database
        engine = create_engine(url)
        connection = engine.connect()
        # Run the query
        result = connection.execute(text(query))
        # Get a list of tuples as result
        result = result.all()
        #print("result {0}".format(result))
        # Close connection
        connection.close()
        engine.dispose()
        # Return query result
        return result

    def test_connection(self):
        result = self.run_query("SELECT 1")
        result = result[0][0]
        self.assertEqual(result, 1)
    
    def test_check_if_table_exist(self):
        query = """
            SELECT EXISTS (
                SELECT TABLE_NAME
                FROM information_schema.TABLES 
                WHERE TABLE_SCHEMA = (SELECT DATABASE())
                AND TABLE_TYPE = 'BASE TABLE'
                AND TABLE_NAME = 'test_table'
            ) AS DO_EXIST;
        """
        result = self.run_query(query)
        result = result[0][0]
        self.assertEqual(result, 1)
    
    def test_check_inserts(self):
        query = """
            SELECT * FROM test_table
            WHERE test_id IN (1,10)
        """
        result = self.run_query(query)
        self.assertTrue(len(result) > 0)
    
    def test_check_specific_record(self):
        query = """
            SELECT * FROM test_table
            WHERE test_column = 20000
        """
        result = self.run_query(query)
        self.assertTrue(len(result) > 0)

if __name__ == '__main__':
    unittest.main()