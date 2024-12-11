public class Room 
{
    private Room west=null;
    private Room east=null;
    private Room south=null;
    private Room north=null;
    private String name="";
    
    //setting min&max coords up here to make changes easier
    final static int minX=1;
    final static int maxX=5;
    final static int minY=1;
    final static int maxY=5;

    private Goon[] goons=new Goon[3]; //an ArrayList might be better here...
    private Coords[] goonCoords=new Coords[3];
    private int existingGoons=0;

    private Player player;
    private Coords playerCoords;

    private Arena arena;

    //die for random enemy movement
    private Dice enemyDie = new Dice(5);

    Room(String name, Player player)
    {
        this.name=name;

        this.player=player;
        
        //create a 5x5 grid, placing the player in the center (3x3)
        playerCoords=new Coords(3,3,minX,maxX,minY,maxY);

        enemyGen();
    }

    Room(String name, Player player, Room west, Room east, Room south, Room north)
    {
        this.name=name;

        this.player=player; 

        this.west=west;
        this.east=east;
        this.south=south;
        this.north=north;

        //create a 5x5 grid, placing the player in the center (3x3)
        playerCoords=new Coords(3,3,minX,maxX,minY,maxY);

        enemyGen();
    }

    public void setConnections(Room west, Room east, Room south, Room north)
    {
        this.west=west;
        this.east=east;
        this.south=south;
        this.north=north;
    }
    
    public String getName()
    {
        return this.name;
    }

    public boolean isWest()
    {
        if (west!=null)
        {
            return true;
        }
        return false;
    }

    public Room getWest()
    {
        return this.west;
    }

    public boolean isEast()
    {
        if (east!=null)
        {
            return true;
        }
        return false;
    }

    public Room getEast()
    {
        return this.east;
    }

    public boolean isSouth()
    {
        if (south!=null)
        {
            return true;
        }
        return false;
    }

    public Room getSouth()
    {
        return this.south;
    }

    public boolean isNorth()
    {
        if (north!=null)
        {
            return true;
        }
        return false;
    }

    public Room getNorth()
    {
        return this.north;
    }
    
    public String getConnections()
    {
        return " w:"+this.west+" e:"+this.east+" s:"+this.south+" n:"+this.north;
    }

    public void moveUp()
    {
        if (!isPlayerDead())
        {
            playerCoords.moveUp();
        }
        for (int i=0; i<existingGoons; i++)
        {
            if(!goons[i].isDead())
            {
                goonCoords[i].autoMove();
            }
        }

        displayRoom();

        fightCheck();
    }

    public void moveDown()
    {
        if (!isPlayerDead())
        {
            playerCoords.moveDown();
        }
        for (int i=0; i<existingGoons; i++)
        {
            if(!goons[i].isDead())
            {
                goonCoords[i].autoMove();
            }
        }

        displayRoom();

        fightCheck();
    }

    public void moveRight()
    {
        if (!isPlayerDead())
        {
            playerCoords.moveRight();
        }
        for (int i=0; i<existingGoons; i++)
        {
            if(!goons[i].isDead())
            {
                goonCoords[i].autoMove();
            }
        }

        displayRoom();

        fightCheck();
    }

    public void moveLeft()
    {
        if (!isPlayerDead())
        {
            playerCoords.moveLeft();
        }
        for (int i=0; i<existingGoons; i++)
        {
            if(!goons[i].isDead())
            {
                goonCoords[i].autoMove();
            }
        }

        displayRoom();

        fightCheck();
    }
 
    public int getCoordX()
    {
        return playerCoords.getX();
    }

    public int getCenterX()
    {
        return playerCoords.getCenterX();
    }

    public int getCoordY()
    {
        return playerCoords.getY();
    }

    public int getCenterY()
    {
        return playerCoords.getCenterY();
    }

    public void setCoords(int x, int y)
    {
        playerCoords.setX(x);
        playerCoords.setY(y);
    }

    private void enemyGen()
    {
        int num = enemyDie.roll(); //used to determine the number of enemies, from zero to three
        if (num>1)
        {
            goonCoords[0]=new Coords(enemyDie.roll(), enemyDie.roll(), minX, maxX, minY, maxY);
            goons[0]=new Goon("Baddie");
            existingGoons++;
        }
        if (num>3)
        {
            goonCoords[1]=new Coords(enemyDie.roll(), enemyDie.roll(), minX, maxX, minY, maxY);
            goons[1]=new Goon("Thuggy");
            existingGoons++;
        }
        if (num>4)
        {
            goonCoords[2]=new Coords(enemyDie.roll(), enemyDie.roll(), minX, maxX, minY, maxY);
            goons[2]=new Goon("George");
            existingGoons++;
        }
    }

    public boolean isPlayerDead()
    {
        return player.isDead();
    }

    private void fightCheck()
    {
        if(isPlayerDead())
        {
            System.out.println("You're dead, you can't fight!");
        }
        for (int i=0; i<existingGoons; i++)
        {
            if (playerCoords.enemyCollisionDetect(goonCoords[i])&&!goons[i].isDead())
            {
                arena=new Arena(this.player, goons[i]);
                arena.Fight();
                displayRoom();
            }
        }
    }

    public void displayRoom() 
    //modify this to show if there's a door (use bools to check if isWest, player.is to make it look right, you have to print in reverse
    {
        String room;
        if (isNorth()) 
        {
            room=" __ __\n";
        }
        else 
        {
            room=" _____\n";
        }

        for(int i=maxY; i>=minY; i--)
        {
            if (isWest() && i==3)
            {
                room+=" ";
            }
            else
            {
                room+="|";
            }
            for (int j=minX; j<=maxX; j++)
            {
                boolean playerIsPrinted=false;
                boolean goonIsPrinted=false;
                if (i==playerCoords.getY() && j==playerCoords.getX() && !isPlayerDead())
                {
                    room+="o"; //player character
                    playerIsPrinted=true;
                }
                else if (!playerIsPrinted)
                {
                    for (int k=0; k<existingGoons; k++)
                    {
                        if (i==goonCoords[k].getY() && j==goonCoords[k].getX())
                        {
                            if(!goons[k].isDead())
                            {
                                room+="x"; //enemy character
                                goonIsPrinted=true;
                                break;
                            }
                        }
                    }
                }
                if (!playerIsPrinted && !goonIsPrinted)
                {
                    room+=" "; //blank space
                }
            }
            if (isEast() && i==3)
            {
                room+="\n";
            }
            else
            {
                room+="|\n";
            }
        }
        if (isSouth())
        {
            room+=" ‾‾ ‾‾";
        }
        else
        {
            room+=" ‾‾‾‾‾";
        }
        System.out.println(room);
    }
    
    @Override
    public String toString()
    {
        return this.name;
    }
}