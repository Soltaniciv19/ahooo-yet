package student.examples;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.apache.commons.io.FileUtils;
import student.examples.devices.VacumCleaner;
import student.examples.listeners.VacumCleanerTestListener;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Listeners(VacumCleanerTestListener.class)
public class VacuumCleanerTest {
    VacumCleaner vacumCleaner;

    @BeforeTest(alwaysRun = true)
    public void setup() throws Exception {
        vacumCleaner = new VacumCleaner();
    }



    @DataProvider(name = "chargeData")
    public Object[][] getChargeData() throws Exception {
        List<Object[]> data = new ArrayList<>();
        SAXReader reader = new SAXReader();
        File file = new File(getClass().getClassLoader().getResource("data/charge-range-test.xml").getFile());
        Document document = reader.read(file);
        Element root = document.getRootElement();
        for (Iterator<Element> it = root.elementIterator("entry"); it.hasNext();) {
            Element entry = it.next();
            int input = Integer.parseInt(entry.elementText("input"));
            int expected = Integer.parseInt(entry.elementText("expected"));
            data.add(new Object[]{input, expected});
        }
        return data.toArray(new Object[0][0]);
    }


    @Test(dataProvider = "chargeData")
    public void testCharge(int input, int expected) {
        VacumCleaner vacuumCleaner = new VacumCleaner(1, "Test Vacuum");
        vacuumCleaner.setCharge(0);
        vacuumCleaner.setCharge(input);
        Assert.assertEquals(vacuumCleaner.getCharge(), expected);
    }
}
