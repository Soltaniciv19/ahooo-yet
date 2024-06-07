package student.examples;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import student.examples.devices.Device;
import student.examples.devices.VacumCleaner;

public class DeviceIdentificationTest {
    private Device device;

    @BeforeClass(alwaysRun = true)
    public void setup(){
        device = new VacumCleaner();
    }

    @Test(groups = {"unit"})
    public void testNegativeId(){
        int previousId = device.getId();
        device.setId(-1);
        Assert.assertEquals(device.getId(),previousId);
    }


}
