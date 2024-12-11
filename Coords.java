public class Coords 
{
    private int x;
    private int minX;
    private int maxX;
    private int y;
    private int minY;
    private int maxY;

    Coords(int x, int y, int minX, int maxX, int minY, int maxY)
    {
        this.x=x;
        this.minX=minX;
        this.maxX=maxX;
        this.y=y;
        this.minY=minY;
        this.maxY=maxY;
    }

    public int getX()
    {
        return this.x;
    }

    public void setX(int x)
    {
        this.x=x;
    }

    public int getCenterX()
    {
        return (maxX+minX)/2;
    }

    public int getY()
    {
        return this.y;
    }

    public void setY(int y)
    {
        this.y=y;
    }

    public int getCenterY()
    {
        return (maxY+minY)/2;
    }

    public void moveUp()
    {
        this.y++;
        wallCollisionDetect();
    }

    public void moveDown()
    {
        this.y--;
        wallCollisionDetect();
    }

    public void moveRight()
    {
        this.x++;
        wallCollisionDetect();
    }

    public void moveLeft()
    {
        this.x--;
        wallCollisionDetect();
    }

    public void autoMove()
    {
        Dice d5 = new Dice(5);
        int moveRoll=d5.roll();
        if (moveRoll==1)
        {
            moveUp();
        }
        else if (moveRoll==2)
        {
            moveLeft();
        }
        else if (moveRoll==3)
        {
            moveDown();
        }
        else if (moveRoll==4)
        {
            moveRight();
        }
        //else no move.
    }

    private void wallCollisionDetect()
    {
        if(this.x>this.maxX)
        {
            this.x=this.maxX;
        }
        else if(this.x<this.minX)
        {
            this.x=this.minX;
        }
        else if(this.y>this.maxY)
        {
            this.y=this.maxY;
        }
        else if(this.y<this.minY)
        {
            this.y=this.minY;
        }
    }

    public boolean enemyCollisionDetect(Coords player2)
    {
        if (this.x==player2.x && this.y==player2.y)
        {
            return true;
        }
        return false;
    }
}
