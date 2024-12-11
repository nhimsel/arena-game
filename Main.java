import java.util.Scanner;

public class Main
{

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) 
    {
        String player_type="";
        String player_name="";
        Mage playerM;
        Warrior playerW;
        Map map;

        System.out.println("Welcome, traveler...");
        Arena.pause(1);
        boolean classChosen=false;
        while(!classChosen)
        {
            System.out.println("What is it you specialize in? Is it magic, or brute force?");
            player_type=scanner.nextLine();
            if (player_type.equals("magic"))
            {
                player_type="Mage";
                classChosen=true;
            }
            else if (player_type.equals("brute force"))
            {
                player_type="Warrior";
                classChosen=true;
            }
            else if (player_type.equals("devm"))
            {
                Mage dev = new Mage("dev");
                map = new Map(dev);
                movement(map);
            }
            else if (player_type.equals("devw"))
            {
                Warrior dev = new Warrior("dev");
                map = new Map(dev);
                movement(map);
            }
            else
            {
                System.out.println("Sorry, I didn't catch that...");
            }
            Arena.pause(2);
        }
        System.out.println("Good...");
        Arena.pause(2);
        System.out.println("Now then... Please tell me your name.");
        player_name=scanner.nextLine();
        System.out.println("It was nice to meet you, "+player_name);
        Arena.pause(3);
        System.out.println("I wish you the best of luck on your adventure...");
        Arena.pause(3);
        if(player_type.equals("Mage"))
        {
            playerM=new Mage(player_name);
            map=new Map(playerM);
            movement(map);
        }
        else if(player_type.equals("Warrior"))
        {
            playerW=new Warrior(player_name);
            map=new Map(playerW);
            movement(map);
        }
    }

    public static void movement(Map map)
    {
        while (!map.isDead())
        {
            System.out.println("Which direction would you like to move?");
            String move = scanner.nextLine();
            if(move.equals("up")||move.equals("w"))
            {
                map.moveUp();
            }
            else if(move.equals("down")||move.equals("s"))
            {
                map.moveDown();
            }
            else if(move.equals("right")||move.equals("d"))
            {
                map.moveRight();
            }
            else if(move.equals("left")||move.equals("a"))
            {
                map.moveLeft();
            }
            else if(move.equals("gameover")||move.equals("die")||move.equals("kill me"))
            {
                System.exit(0);
            }
            else
            {
                System.out.println("Move not understood");
            }
        }
    }
}