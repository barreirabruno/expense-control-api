package infra;

import infra.database.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.*;

public class DatabaseHelperTest {

    private String databaseUrlFromPropertyFile = null;

    @Before
    public void getEnvProperties() throws IOException {
        Properties prop = new Properties();
        String propFileName = "expense-persistence.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if(inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        this.databaseUrlFromPropertyFile = prop.getProperty("jdbc.url");
    }

    @Test
    public void should_connect_to_the_database() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this.databaseUrlFromPropertyFile);
        databaseHelper.makeDatabaseConnection();
        assertNotNull(databaseHelper.getDatabaseConnection());
        databaseHelper.destroyDatabaseConnection();
    }

    @Test
    public void should_destroy_database_connection() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this.databaseUrlFromPropertyFile);
        databaseHelper.makeDatabaseConnection();
        boolean isConnected = databaseHelper.destroyDatabaseConnection();
        assertEquals(false, isConnected);
    }
}
