public class Mage extends Player
{
    private int mp; //magic points - a value between 10 and 30

    final private Dice d10 = new Dice(10);

    public Mage(String name)
    {
        super(name);
        this.player_type="Mage";
        this.str -=3;
        this.ac-=2;
        this.hp+=5;
        this.max_hp = this.hp;

        Dice mp_die = new Dice(21);
        this.mp = mp_die.roll()+9;
    }

    @Override
    public int getSTR()
    {
        int d10_roll = d10.roll();

        //each attack costs between one and 10 mp; more mp = stronger attack
        if (d10_roll>3 && (this.mp-d10_roll)>=0)
        { 
            //magic attack!
            this.mp-=d10_roll;
            System.out.println(this.name+" launched a magic attack for "+d10_roll+"MP!");
            
            return (int)((this.str*.75)*d10_roll);
        }
        else
        {
            this.mp+=d10_roll;
            System.out.println(this.name+" recovered "+d10_roll+" mp.");
        }
        return this.str;
    }

    @Override
    public void display()
    {
        super.display();
        System.out.println("MP: "+this.mp);
    }

    @Override
    public String toString()
    {
        String top = " _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _";
        String bottom = " ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾";

        if (this.name.length()<=5)
        {
            return top+"\n| "+this.name+"\t\t       HP="+this.hp+"/"+this.max_hp+
                " |\n|\t\t\t\t|\n| STR="+this.str+
                "  \t\t\t|\n| AC="+this.ac+
                "   \t\t\t|\n| MP="+this.mp+
                "   \t\t\t|\n|\t\t\t\t|\n"+bottom;
        }

        else if (this.name.length()<=13)
        {
            return top+"\n| "+this.name+"\t       HP="+this.hp+"/"+this.max_hp+
                " |\n|\t\t\t\t|\n| STR="+this.str+
                "  \t\t\t|\n| AC="+this.ac+
                "   \t\t\t|\n| MP="+this.mp+
                "   \t\t\t|\n|\t\t\t\t|\n"+bottom;
        }

        else
        {
            top = ""; bottom = "";
            for (int i=0; i<this.name.length()/2+2; i++)
            {
                top += " _";
                bottom += " ‾";
            }

            return top+"\n| "+this.name+"\t   \n|\n| HP="+this.hp+"/"+this.max_hp+
                " |\n|\t\t\t\t|\n| STR="+this.str+
                "  \t\t\t|\n| AC="+this.ac+
                "   \t\t\t|\n| MP="+this.mp+
                "   \t\t\t|\n|\t\t\t\t|\n"+bottom;
        }
    }
}