public class Warrior extends Player
{
    private int rage; //rage - a value between 0 and 50; initialized to a value between 15 and 35

    //if a warrior is enraged, stat changes of str x1.5 and ac x.5
    private int rage_str;
    private int rage_ac;

    public Warrior(String name)
    {
        super(name);
        this.player_type="Warrior";
        this.str += 3;
        this.ac += 2;
        this.hp -= 5;
        this.max_hp = this.hp;

        //set values for when the Warrior is enraged
        this.rage_str = (int)(this.str*1.75);
        this.rage_ac = (int)(this.ac*0.5);
        
        Dice rage_die = new Dice(21);
        this.rage = rage_die.roll()+14;
    }
    
    private boolean isEnraged()
    {
        //warrior is enraged if their rage is at least 47
        if (this.rage>=47) System.out.println(this.name+" is enraged!");
        return this.rage>=47;
    }

    @Override
    public int getSTR()
    {
        if (isEnraged()) return this.rage_str;
        return this.str;
    }

    @Override
    public int getAC()
    {
        if (isEnraged()) return this.rage_ac;
        return this.ac;
    }

    @Override
    public void takeDamage(int damage)
    {
        super.takeDamage(damage);
        rage(damage);
    }

    @Override
    public void takeDamage(int damage, int str)
    {
        super.takeDamage(damage, str);
        rage(damage);
    }

    @Override
    public void display()
    {
        super.display();
        System.out.println("Rage: "+this.rage);
    }

    private void rage(int damage)
    {
        //once rage reaches 50, it resets to 0. if rage is not yet 50 and would go over 50, it is set to 50
        int rageIncrease =(int)(damage/2);

        if (this.rage>=50)
        {
            this.rage = 0;
        }

        else if (this.rage+rageIncrease > 50 && this.rage<50)
        {
            this.rage = 50;
        }

        else
        {
            this.rage+=rageIncrease;
        }
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
                "   \t\t\t|\n| RAGE="+this.rage+
                "   \t\t\t|\n|\t\t\t\t|\n"+bottom;
        }

        else if (this.name.length()<=13)
        {
            return top+"\n| "+this.name+"\t       HP="+this.hp+"/"+this.max_hp+
                " |\n|\t\t\t\t|\n| STR="+this.str+
                "  \t\t\t|\n| AC="+this.ac+
                "   \t\t\t|\n| RAGE="+this.rage+
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
                "   \t\t\t|\n| RAGE="+this.rage+
                "   \t\t\t|\n|\t\t\t\t|\n"+bottom;
        }
    }
}