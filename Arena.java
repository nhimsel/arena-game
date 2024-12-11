import java.util.concurrent.TimeUnit;

public class Arena
{
    private Player p1;
    private Player p2;
    final private Dice d20 = new Dice(20);
    final private Dice d6 = new Dice(6);
    final private Dice d2 = new Dice(2);
    final private Dice d100 = new Dice(100);

    public Arena(Player p1, Player p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void Fight()
    {
        Player curAttacker;
        Player curDefender;

        //randomly determine who attacks first
        if (this.d20.roll()>10)
        {
            curAttacker = this.p1; 
            curDefender = this.p2;
        }
        
        else
        {
            curAttacker = this.p2; 
            curDefender = this.p1;
        }

        //print stats of characters
        System.out.println(curAttacker + "\n" + curDefender);
        
        while (true) 
        //loop runs until a player dies
        {
            curAttacker.display();
            System.out.println("");
            curDefender.display();
            System.out.println("\n");
            System.out.println(curAttacker.getName() + " is on the offensive.");

            int atkRoll = this.d20.roll();
            if (atkRoll >= curDefender.getAC())
            {
                //defender has been hit
                int tempHP = curDefender.getHP();
                int damage = this.d6.roll();

                //scoring a critical hit
                Boolean crit = false;
                if (d100.roll()==77)
                {
                    damage*=2;
                    crit = true;
                }

                curDefender.takeDamage(damage, curAttacker.getSTR());

                System.out.println(curAttacker.getName() + " deals " + 
                (tempHP-curDefender.getHP()) + " points of damage to " + 
                curDefender.getName()+".");

                if (crit) System.out.println("It was a critical hit!");

                if (curDefender.isDead())
                {
                    //defender is dead, attacker wins
                    System.out.println(curDefender.getName() + " was killed. " + 
                    curAttacker.getName() + " wins.");
                    return;
                }
            }

            else
            {
                //attack defended
                if (atkRoll<=2)
                {
                    //attack missed, defender counterattacks
                    int damage = this.d2.roll();
                    curAttacker.takeDamage(damage);

                    //scoring a critical hit
                    Boolean crit = false;
                    if (d100.roll()==77)
                    {
                        damage*=2;
                        crit = true;
                    }

                    System.out.println(curAttacker.getName() + 
                    " missed their attack! " + curDefender.getName() + 
                    " counterattacks and deals " + damage + " points of damage.");

                    if (crit) System.out.println("It was a critical hit!");

                    if (curAttacker.isDead())
                    {
                        //attacker was killed, defender wins
                        System.out.println(curAttacker.getName() + " was killed. " 
                        + curDefender.getName() + " wins.");
                        return;
                    }
                }
                else
                {
                    System.out.println(curDefender.getName() + " defended themselves from the hit.");
                }
            }

            //swap players
            Player tmp = curAttacker;
            curAttacker = curDefender;
            curDefender = tmp;

            pause(1);

            //add a blank line to make output more clear
            System.out.println("");
        }
    }

    public static void pause(int s)
    {
        //add a pause to make it possible to follow along in real time
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}