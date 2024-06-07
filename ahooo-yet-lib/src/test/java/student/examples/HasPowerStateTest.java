package student.examples;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import student.examples.devices.HasPowerStates;
import student.examples.devices.VacumCleaner;

public class HasPowerStateTest {
    private HasPowerStates hasPowerStates;

    @BeforeTest(alwaysRun = true)
    public void setup(){
        hasPowerStates = new VacumCleaner(2,"Jimmy");
    }
    @Test(groups = {"unit"})
    public void testSwitchOn(){
        hasPowerStates.switchOn();
        Assert.assertTrue(hasPowerStates.isOn());
    }

    @Test(groups = {"unit"})
    public void testSwitchOff(){
        hasPowerStates.switchOff();
        Assert.assertFalse(hasPowerStates.isOn());

    }
}
