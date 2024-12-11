abstract class Player
{
    protected String name;
    protected String player_type;
    protected int hp; //hit points - a value between 15 and 30
    protected int max_hp;
    protected int str; //strength - a value between 8 and 12
    protected int ac; //armor class - a value between 5 and 17
    
    public Player(String name)
    {
        Dice hp_die = new Dice(16);
        Dice str_die = new Dice(5);
        Dice ac_die = new Dice(13);

        this.name = name;
        this.player_type = "Player";
        this.hp = hp_die.roll()+14;
        this.max_hp = this.hp;
        this.str = str_die.roll()+7;
        this.ac = ac_die.roll()+4;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPlayerType()
    {
        return this.player_type;
    }

    public int getHP()
    {
        return this.hp;
    }

    public int getSTR()
    {
        return this.str;
    }

    public int getAC()
    {
        return this.ac;
    }

    public void takeDamage(int damage)
    {
        //take set damage, for something like poison damage
        this.hp-=damage;
    }

    public void takeDamage(int damage, int str)
    {
        //actual attack damage taking into account attacker's strength and defender's defense
        this.hp-=(int)((str / 3) * damage)/((int)(this.ac / 4)+1);
    }

    public boolean isDead()
    {
        return this.hp<=0;
    }
    
    public void display()
    {
        System.out.println(this.name+" ("+this.player_type+")\nHP: "+this.hp);
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
                "   \t\t\t|\n|\t\t\t\t|\n"+bottom;
        }

        else if (this.name.length()<=13)
        {
            return top+"\n| "+this.name+"\t       HP="+this.hp+"/"+this.max_hp+
                " |\n|\t\t\t\t|\n| STR="+this.str+
                "  \t\t\t|\n| AC="+this.ac+
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
                "\t\t\t\n| STR="+this.str+
                "  \t\t\t\n| AC="+this.ac+
                "   \t\t\t\n|\n"+bottom;
        }
    }    
}