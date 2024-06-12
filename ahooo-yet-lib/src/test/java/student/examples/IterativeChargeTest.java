package student.examples;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import student.examples.devices.HasBattery;
import student.examples.devices.VacumCleaner;

import java.sql.*;

public class IterativeChargeTest {
    private Connection connection;
    private HasBattery vacuumCleaner;
    @BeforeTest(alwaysRun = true)
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://192.168.187.130/test_data_db?user=postgres&password=qazwsx&ssl=false");
        vacuumCleaner = new VacumCleaner();
    }

    @Test
    public void testCharge() throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * from \"charge_test_data\"\n" +
                "ORDER BY (id) ASC;");

        while(result.next()){
            vacuumCleaner.setCharge(result.getInt(2));
            Assert.assertEquals(vacuumCleaner.getCharge(),result.getInt(3));
        }


    }

}
