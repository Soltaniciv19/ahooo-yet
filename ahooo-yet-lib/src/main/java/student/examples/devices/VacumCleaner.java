package student.examples.devices;

import static student.examples.devices.PowerState.OFF;
import static student.examples.devices.PowerState.ON;

public class VacumCleaner extends Device implements HasPowerStates,HasBattery {
    private final int MIN_CHARGE = 10;
    private PowerState powerState;
    private int charge;


    public VacumCleaner(){
        init();
    }

    public VacumCleaner(int id, String name){
        super(id,name);
        init();
    }

    private void init(){
        switchOff();
        setCharge(0);
    }


    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    @Override
    public boolean switchOn() {
        powerState = ON;
        return true;
    }

    @Override
    public boolean switchOff() {
        powerState = OFF;
        return true;
    }

    @Override
    public boolean isOn() {
        return powerState != OFF;
    }

    @Override
    public boolean charge() {
        setCharge(charge + 5);
        return false;
    }

    @Override
    public boolean disCharge() {
        setCharge(charge - 5);
        return false;
    }

    @Override
    public boolean isCharge() {
        return charge >= MIN_CHARGE;
    }
}


