
import java.awt.*;
public class Slider 
{
    private double x;
    private double y;
    private Point[] corners = new Point[4];
    private int halfWidth;
    private int halfHeight;    
    private int speed = 3;
    
    public Slider(int x, int y)
    {
        this.x = x;
        this.y = y;
        halfWidth = 50;
        halfHeight = 5;
        corners[0] = new Point(x-halfWidth, y+halfHeight);
        corners[1] = new Point(x+halfWidth, y+halfHeight);
        corners[2] = new Point(x-halfWidth, y-halfHeight);
        corners[3] = new Point(x+halfWidth, y-halfHeight);
    }
    public Slider(int x, int y, int halfWidth, int halfHeight)
    {
        this.x = x;
        this.y = y;
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
        
        corners[0] = new Point(x-halfWidth, y+halfHeight);
        corners[1] = new Point(x+halfWidth, y+halfHeight);
        corners[2] = new Point(x-halfWidth, y-halfHeight);
        corners[3] = new Point(x+halfWidth, y-halfHeight);
    }
    
    //----------------------------------------------------------
    
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
    public double getHalfHeight()
    {
        return halfHeight;
    }
    public Point[] getCorners()
    {
        return corners;
    }
    
    //----------------------------------------------------------
    
    public void draw()
    {
        StdDraw.setPenColor(39, 176, 160);//13, 77, 94
        StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(x, y, halfWidth, halfHeight);
    }
    
    //----------------------------------------------------------
    
    public void moveRight(int width)
    {
        if(x + halfWidth + speed-.2 < width)
            x += speed;
    }
    
    public void moveLeft(int width)
    {
        if(x - halfWidth - speed-.2 > 0)
            x -= speed;
    }
}
