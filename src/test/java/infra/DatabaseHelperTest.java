package infra;

import infra.database.DatabaseHelper;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseHelperTest {

    @Test
    public void should_connect_to_the_database() {
        DatabaseHelper databaseHelper = new DatabaseHelper("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        databaseHelper.makeDatabaseConnection();
        assertNotNull(databaseHelper.getDatabaseConnection());
    }

}
