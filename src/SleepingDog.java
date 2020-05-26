// Type of enemy Strategy Pattern
public class SleepingDog extends Enemy {


    public SleepingDog(){
        setWakeupBehaviour(new DogWakeup());
    }

}
