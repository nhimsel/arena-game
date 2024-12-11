public class Goon extends Player
{
    Goon(String name)
    {
        super(name);
        this.player_type="Goon";

        this.hp-=5;
        this.max_hp=this.hp;
        this.str-=8;
        this.ac-=5;
    }
}
