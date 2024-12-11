import java.util.Random;

public class Dice
{
    private int sides;
    private Random rng;
    
    public Dice(int sides)
    {
        this.sides = sides;
        this.rng = new Random();
    }
    
    public int roll()
    {
        //returns a value between 1 and sides
        return this.rng.nextInt(this.sides) + 1;
    }
}