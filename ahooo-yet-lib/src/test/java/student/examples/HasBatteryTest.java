package student.examples;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.examples.devices.HasBattery;
import student.examples.devices.HasPowerStates;
import student.examples.devices.VacumCleaner;

public class HasBatteryTest {

    private HasBattery hasBattery;
    private HasPowerStates hasPowerStates;

    @BeforeEach
    public void setup(){
        hasBattery = new VacumCleaner(1,"Atom");
        hasBattery.setCharge(50);

        hasPowerStates = new VacumCleaner(2,"Jimmy");
    }

    @Test
    public void testOverCharge(){
        final int TARGET_CHARGE = 100;
        int chargeBefore = hasBattery.getCharge();
        hasBattery.charge();
        int chargeAfter = hasBattery.getCharge();

        int deltaCharge = chargeAfter - chargeBefore;
        int steps = (TARGET_CHARGE - chargeAfter) / deltaCharge;
        steps++;

        while (steps-- != 0){
            hasBattery.charge();
        }
        Assert.assertEquals(100,hasBattery.getCharge());

    }
    @Test
    public void testUnderDischarge(){
        final int MINIMAL_TARGET = 0;
        int chargeBefore = hasBattery.getCharge();
        hasBattery.disCharge();
        int chargeAfter = hasBattery.getCharge();

        int deltaDischarge = chargeBefore - chargeAfter;
        int steps = (MINIMAL_TARGET + chargeAfter) / deltaDischarge;

        for (int i = 0; i <= steps; i++) {
            hasBattery.disCharge();
        }


        Assert.assertEquals(0,hasBattery.getCharge());
    }

}
