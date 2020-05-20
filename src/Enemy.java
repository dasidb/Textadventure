import processing.core.PVector;

public class Enemy {
    PVector positíon;
    String name;
    boolean isSleeping = true;
    WakeupBehaviour wakeupBehaviour;


    public void setWakeupBehaviour(WakeupBehaviour wakeupBehaviour){
        this.wakeupBehaviour = wakeupBehaviour;
    }

    public WakeupBehaviour getWakeupBehaviour(){
        return wakeupBehaviour;
    }

    public PVector getPositíon() {
        return positíon;
    }

    public void setPositíon(PVector positíon) {
        this.positíon = positíon;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }
}
