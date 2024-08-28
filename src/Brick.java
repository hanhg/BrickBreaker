
import java.util.*;
import java.awt.*;
public class Brick 
{
    private double x;
    private double y;
    private int halfWidth;
    private int strength;
    private Point[] corners = new Point[4];
    private Color color = new Color(55, 170, 219);
    //private Point[] brickBordersX;// the points on the horizontal borders (first half is the bottom boundary; 2nd half the top)
    //private Point[] brickBordersY;// the points on the vertical borders (first half is the left boundary; 2nd half the right)
    
    public Brick(double x, double y, int halfWidth)
    {
        this.x = x;
        this.y = y;
        this.halfWidth = halfWidth;
        strength = 1;
        corners[0] = new Point(x-halfWidth, y+halfWidth);
        corners[1] = new Point(x+halfWidth, y+halfWidth);
        corners[2] = new Point(x-halfWidth, y-halfWidth);
        corners[3] = new Point(x+halfWidth, y-halfWidth);
    }
    
//    public void initializeBrickBorders()
//    {
//        brickBordersX = new Point[(halfWidth*2)*2];
//        brickBordersY = new Point[(halfWidth*2)*2];
//        for(int i = 0; i < halfWidth; i++)
//        {
//            brickBordersX[i] = new Point(x-halfWidth + i, y-halfWidth);
//            brickBordersX[i + halfWidth] = new Point(x-halfWidth + i, y+halfWidth);
//            
//            brickBordersY[i] = new Point(x-halfWidth, y-halfWidth + i);
//            brickBordersY[i + halfWidth] = new Point(x+halfWidth, y-halfWidth + i);
//        }
//    }
    
    //------------------------------------------------------------
    
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getHalfWidth()
    {
        return halfWidth;
    }
    public int getStrength()
    {
        return strength;
    }
    
    //------------------------------------------------------------
    
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public void setHalfWidth(int halfWidth)
    {
        this.halfWidth = halfWidth;
    }
    public void setStrength(int strength)
    {
        this.strength = strength;
    }
    public void reduce(int value)
    {
        strength -= value;
        color.darker();
    }
    
    //------------------------------------------------------------
    
    public void draw()
    {
        StdDraw.setPenColor(color);//39, 176, 160
        StdDraw.filledSquare(x, y, halfWidth);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.square(x, y, halfWidth);
    }
    
    //------------------------------------------------------------
    
    public double findBottomY()
    {
        return y-=halfWidth-1;
    }
    public double findTopY()
    {
        return y+=halfWidth+1;
    }
    public double findLeftX()
    {
        return x-=halfWidth-1;
    }
    public double findRightX()
    {
        return x+=halfWidth+1;
    }
    
    //------------------------------------------------------------
    
    public String closestSide(Ball b)
    {
        String rtn = "bottom";
        double distance = b.distance(x, findBottomY());
        
        if(b.distance(x, findTopY()) < distance)
        {
            rtn = "top";
            distance = b.distance(x, findTopY());
        }
        if(b.distance(findLeftX(), y) < distance)
        {
            rtn = "left";
            distance = b.distance(findLeftX(), y);
        }
        if(b.distance(findRightX(), y) < distance)
        {
            rtn = "top";
        }
        
        return rtn;
        
    }
    
    public boolean withinX(double x)
    {
        return x >= findLeftX() && x <= findRightX();
    }
    
    public boolean withinY(double y)
    {
        return y >= findBottomY() && y <= findTopY();
    }
    
    public boolean isPointOnBrick(double x, double y)
    {
        if(withinX(x))
        {
            if(withinY(y))
            {
                return true;
            }
        }
        return false; 
    }
    
}
