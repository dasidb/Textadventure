public class SleepingDog extends Enemy {


    public SleepingDog(){
        setWakeupBehaviour(new DogWakeup());
    }

}
