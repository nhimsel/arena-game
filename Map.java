public class Map
{
    private Room[] rooms;
    private Room current;

    Map(Player player)
    {
        rooms=new Room[6]; //generates a cross-shaped map
        for (int i=0; i<rooms.length; i++)
        {
            //define all rooms before organizing
            this.rooms[i]=new Room("Room "+(i+1), player);
        }
        this.rooms[0].setConnections(null,null,null,rooms[1]); //bottom
        this.rooms[1].setConnections(null,null,rooms[0],rooms[2]); //second from bottom
        this.rooms[2].setConnections(rooms[3],rooms[4],rooms[1],rooms[5]); //third from bottom, intersection point
        this.rooms[3].setConnections(null,rooms[2],null,null); //limb to the left
        this.rooms[4].setConnections(rooms[2],null,null,null); //limb to the right
        this.rooms[5].setConnections(null,null,rooms[2],null); //top

        this.current=this.rooms[0];
        System.out.println("\n\n"+current.getName());
        current.displayRoom();
    }

    public boolean isDead()
    {
        return current.isPlayerDead();
    }

    public Room getRoom()
    {
        return current;
    }

    public void moveUp()
    {
        if (current.getCoordX()==current.getCenterX() && current.getCoordY()==current.maxY && current.isNorth() && !current.isPlayerDead())
        {
            moveRoomNorth();
            current.setCoords(current.getCenterX(),current.minY);
            current.displayRoom();
        }
        else
        {
            current.moveUp();
        }
    }

    public void moveDown()
    {
        if (current.getCoordX()==current.getCenterX() && current.getCoordY()==current.minY && current.isSouth() && !current.isPlayerDead())
        {
            moveRoomSouth();
            current.setCoords(current.getCenterX(),current.maxY);
            current.displayRoom();
        }
        else
        {
            current.moveDown();
        }
    }

    public void moveLeft()
    {
        if (current.getCoordX()==current.minX && current.getCoordY()==current.getCenterY() && current.isWest() && !current.isPlayerDead())
        {
            moveRoomWest();
            current.setCoords(current.maxX,current.getCenterY());
            current.displayRoom();
        }
        else
        {
            current.moveLeft();
        }
    }

    public void moveRight()
    {
        if (current.getCoordX()==current.maxX && current.getCoordY()==current.getCenterY() && current.isEast() && !current.isPlayerDead())
        {
            moveRoomEast();
            current.setCoords(current.minX,current.getCenterY());
            current.displayRoom();
        }
        else
        {
            current.moveRight();
        }
    }

    private void moveRoomWest()
    {
        this.current=this.current.getWest();
        System.out.println("\n\n"+current.getName());
    }

    private void moveRoomEast()
    {
        this.current=this.current.getEast();
        System.out.println("\n\n"+current.getName());
    }

    private void moveRoomSouth()
    {
        this.current=this.current.getSouth();
        System.out.println("\n\n"+current.getName());
    }

    private void moveRoomNorth()
    {
        this.current=this.current.getNorth();
        System.out.println("\n\n"+current.getName());
    }

    public void display()
    {
        for (int i=0; i<rooms.length; i++)
        {
            System.out.println(this.rooms[i].getName()+this.rooms[i].getConnections());
        }
    }
}