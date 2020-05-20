public class PersonWakeup implements WakeupBehaviour{

    @Override
    public boolean wakeup() {
    System.out.println("Du wurdest erwischt");
    return true;
    }
}
