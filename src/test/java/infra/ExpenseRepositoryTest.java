package infra;

import data.protocols.database.AddExpenseRepository;
import domain.ExpenseMock;
import domain.entity.Expense;
import infra.database.AddExpenseRepositoryImpl;
import infra.database.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

class PrepareInMemoryDatabase {
    public PrepareInMemoryDatabase prepareDatabase(Connection databaseConnection) throws IOException {
        String schemaFileName = "in-mem-schema.sql";
        InputStream getSqlFile = getClass().getClassLoader().getResourceAsStream(schemaFileName);
        InputStreamReader streamReaderSqlFile = new InputStreamReader(getSqlFile, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReaderSqlFile);
        StringBuilder buffer = new StringBuilder();
        for(String line; (line = reader.readLine()) != null;) {
            buffer.append(line);
        }
        try {
            PreparedStatement statement = databaseConnection.prepareStatement(buffer.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

public class ExpenseRepositoryTest {

    private String databaseUrlFromPropertyFile = null;
    private DatabaseHelper databaseConnectionToTest = null;

    @Before
    public void setupDatabase() throws IOException {
        try {
            Properties prop = new Properties();
            String propFileName = "expense-persistence.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if(inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            this.databaseUrlFromPropertyFile = prop.getProperty("jdbc.url");
            this.databaseConnectionToTest = new DatabaseHelper(this.databaseUrlFromPropertyFile);
            Connection currentDatabaseConnection = this.databaseConnectionToTest.makeDatabaseConnection();
            new PrepareInMemoryDatabase().prepareDatabase(currentDatabaseConnection);
            currentDatabaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_add_a_new_expense() {
        Expense newExpense = new ExpenseMock().mockExpense();
        Connection databaseConnection = this.databaseConnectionToTest.makeDatabaseConnection();
        AddExpenseRepository addExpense = new AddExpenseRepositoryImpl(databaseConnection);
        assertEquals(1, addExpense.add(newExpense));
    }
}
