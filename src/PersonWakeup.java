// Strategie Pattern

public class PersonWakeup implements WakeupBehaviour{

    // Gets called when the person wakesup
    @Override
    public boolean wakeup() {
    System.out.println("Du wurdest erwischt");
    return true;
    }
}
