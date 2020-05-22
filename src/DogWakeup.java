public class DogWakeup implements WakeupBehaviour {

    @Override
    public boolean wakeup() {
        System.out.println("kommt an");
        if(Math.round(Math.random()*10) > 8){
            System.out.println("der Hund ist aufgewacht");
            return true;
        }
        return false;
    }
}
