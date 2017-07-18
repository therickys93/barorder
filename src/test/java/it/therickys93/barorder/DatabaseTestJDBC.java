package it.therickys93.barorder;

import java.sql.SQLException;

import org.junit.Test;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;
import com.mockrunner.jdbc.StatementResultSetHandler;
import com.mockrunner.mock.jdbc.MockConnection;
import com.mockrunner.mock.jdbc.MockResultSet;

import it.therickys93.barorder.database.DatabaseIntegration;

public class DatabaseTestJDBC extends BasicJDBCTestCaseAdapter {

	private void prepareEmptyResultSet() {
        MockConnection connection = 
            getJDBCMockObjectFactory().getMockConnection();
        StatementResultSetHandler statementHandler = 
            connection.getStatementResultSetHandler();
        MockResultSet result = statementHandler.createResultSet();
        statementHandler.prepareGlobalResultSet(result);
    }
	
	private void teardown() {
        verifyNotCommitted();
        verifyAllResultSetsClosed();
        verifyAllStatementsClosed();
        verifyConnectionClosed();
	}
	
	@Test
	public void checkDatabaseStatus() throws Exception {
        prepareEmptyResultSet();
        DatabaseIntegration database = new DatabaseIntegration();
        database.open();
        database.checkDatabaseStatus();
        database.close();
        verifySQLStatementExecuted("select 1");
        teardown();
    }
	
	@Test
	public void getOrders() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.allOrders();
		database.close();
		verifySQLStatementExecuted("select ");
		teardown();
	}
	
}
