// Strategie Pattern
public class DogWakeup implements WakeupBehaviour {

    //chance of 20 % that the dog wakesup when method is called
    @Override
    public boolean wakeup() {
        System.out.println("kommt an");
       // if(Game.isAdmin()){
         //   return true;
      //  }
        if(Math.round(Math.random()*10) > 8){
            System.out.println("der Hund ist aufgewacht");
            return true;
        }
        return false;
    }
}
