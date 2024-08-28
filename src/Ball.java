
import java.awt.*;
import java.util.*;
public class Ball 
{
    private double x;
    private double y;
    private int radius;
    //velocity
    private double dx;
    private double dy;
    private double speed = 4; //this is really distance travelled within every run but same thing
    
    //-----------------------------------------------------------------------
    
    public Ball(double x, double y)
    {
        this.x = x;
        this.y = y;
        dx = -1.4;
        dy = -1.4;
        radius = 5;
    }
    
    public Ball(double x, double y, int radius)
    {
        this.x = x;
        this.y = y;
        dx = -1.4;
        dy = -1.4;
        this.radius = radius;
    }
    
    //-----------------------------------------------------------------------
    
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getRadius()
    {
        return radius;
    }
    
    //-----------------------------------------------------------------------
    
    public void draw()
    {
        StdDraw.setPenColor(39, 176, 160);//39, 176, 160
        StdDraw.filledCircle(x, y, radius);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.circle(x, y, radius);
    }
    
    //-----------------------------------------------------------------------
    
    public double distance(double x, double y)
    {
        return Math.sqrt(Math.pow(this.x-x,2)+Math.pow(this.y- y,2));
    }
    
    public boolean hits(double x, double y) //does the ball hit this point
    {
        return distance(x,y) <= radius+1; //distance < radius
    }
    
    public boolean hits(Point point) //does the ball hit this point
    {
        return distance(point.getX(), point.getY()) <= radius; //distance < radius
    }
    
    public void hitsBorder/*canvas border*/(int width, int height) //parameter is size of canvas 
    {
        //points on the border closest to ball
        Point[] borderPoints = {new Point(0, y), new Point(width, y), new Point(x, height), new Point(x, 0)};  //{left, right, top, bottom}
        for(int i = 0; i < 4; i++)
        {
            if(hits(borderPoints[i]))
            {
                if(i == 0 || i == 1)
                    dx *= -1;// vertical bounce
                if(i == 2 || i == 3)
                    dy *= -1;// horizaontal vounce
                break;
            }
        }
    }
    
    public boolean lose()
    {
        return hits(new Point(x, 0));
    }
    
    public void hitsSlider(Slider slider)
    {
        
        double[] rangeNumer = new double[50];
        for(int i = 1; i <= slider.getHalfWidth(); i++)
        {
            rangeNumer[i-1] = (i * ((3 * slider.getHalfWidth() - (3 * slider.getHalfWidth()/7.0)))/50) + (3 * slider.getHalfWidth()/7.0);
        }   
         
        for(double i = -50, x1 = slider.getX()-slider.getHalfWidth(); i < slider.getHalfWidth(); x1++, i++) // x1 is range of x's in the slider; i is the position in relation to x of slider
        {
            
            if(i == 0)
            {
                if(hits(x1, slider.getY() + slider.getHalfHeight()))
                {
                    dx = 0;
                    dy = speed;
                    break;
                }
                
            }
            
            if(hits(x1, slider.getY() + slider.getHalfHeight()))
            {
                double denom = (slider.getHalfWidth());
                double numer = rangeNumer[(int)(slider.getHalfWidth() - Math.abs(i))]; // range of 150 - 30
                //double numer = (slider.getHalfWidth() * 3)-Math.abs(i)*1.5; 
                
                if(i < 0)
                    numer *= -1;
                
                dx = findRun(denom, speed * speed, numer);
                dy = findRise(denom, speed * speed, numer);
                if(dy < 0)
                {
                    dx *= -1;
                    dy *= -1;
                }
                break;
            }
        }
    }
    
    //-----------------------------------------------------------------------
    
    public void setDx(double dx)
    {
        this.dx = dx;
    }
    
    public void setDy(double dy)
    {
        this.dy = dy;
    }
    
    //-----------------------------------------------------------------------
    
    public void update()
    {
        x += dx;
        y += dy;
    }
    
    //-----------------------------------------------------------------------
    
    public double findRun(double slopeRun, double distanceSquared, double slopeRise)//SPECIFICALLY FOR DISTANCE FORMULA finding rise and run
    {
        
        double run = Math.sqrt((Math.pow(slopeRun, 2) * distanceSquared)/(Math.pow(slopeRise, 2) + Math.pow(slopeRun, 2)));
        return run;
    }
    public double findRise(double slopeRun, double distanceSquared, double slopeRise)
    {
        return (findRun(slopeRun, distanceSquared, slopeRise) * slopeRise)/slopeRun;
    }
    
    //-----------------------------------------------------------------------
    
    public void bounceVertically()
    {
        dy *= -1;
    }
    public void bounceHorizontally()
    {
        dx *= -1;
    }
}
