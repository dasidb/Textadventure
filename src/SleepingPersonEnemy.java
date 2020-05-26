// GGF Anwenden des Strategy Pattern
// Interface für verhalten erstellen z.B. aufweck verhalten
// dieses vererbt an "PersonenAufweckVerhalten/WachHundAufweckVerhalten
// Nun ein Typ von AufweckVerhalten in die Enemy Klasse einbauen + eine Get und Set methode
// Set Methode wird dann in den Child Klassen aufgerufen z.B. in Dieser Klasse PersonenAufweckVerhalten
// that was legecy commentary

// Strategy Pattern
public class SleepingPersonEnemy extends Enemy{


    public SleepingPersonEnemy(){
        setWakeupBehaviour(new PersonWakeup());
    }
    
}
