//animations

import java.awt.*;
import java.util.*;
public class Game 
{
    private Slider slider;
    private ArrayList<Ball> balls = new ArrayList<Ball>();   
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    private ArrayList<Brick> setBricks = new ArrayList<Brick>();
        
    //-----------------------------------------------------------------------
    
    public Game(int width, int height, int bricks)
    {
        slider = new Slider(300, 150);
        balls.add(new Ball(324, 325));
        for(int i = 0; i < bricks; i++)
        {
            this.bricks.add(new Brick(0, 0, 11));
        }
    }
    
    //-----------------------------------------------------------------------
    
    public void addBall()
    {
        balls.add(new Ball(balls.get(0).getX(), balls.get(0).getY()));
    }
    
    //-----------------------------------------------------------------------
    
    public void updateBalls()
    {
        for(int i = 0; i < balls.size(); i++)
        {
            balls.get(i).update();
        }
    }
    
    //-----------------------------------------------------------------------
    
    public void drawBalls()
    {
        for(int i = 0; i < balls.size(); i++)
        {
            balls.get(i).draw();
        }
    }
    
    //-----------------------------------------------------------------------
    
    public void drawSlider()
    {
        slider.draw();
    }
    
    //-----------------------------------------------------------------------

    public void hitsBorder(int width, int height)
    {
        for(int i = 0; i < balls.size(); i++)
        {
            balls.get(i).hitsBorder(width, height);
        }
    } 
    
    //-----------------------------------------------------------------------
    
    public void hitsSlider()
    {
        for(int i = 0; i < balls.size(); i++)
        {
            balls.get(i).hitsSlider(slider);
        }
    }
    
    //-----------------------------------------------------------------------
    
    public void sliderMoveRight(int width)
    {
        slider.moveRight(width);
    }
    
    public void sliderMoveLeft(int width)
    {
        slider.moveLeft(width);
    }
    
    //------------------------------------------------------------
    
    public void organizeBricks()
    {
        double x = .5;
        double y = 599.5;
        x += 12;
        y -= 12;
        bricks.get(0).setX(x);
        bricks.get(0).setY(y);
        bricks.get(0).setHalfWidth(11);
        for(int i = 1; i < bricks.size(); i++)
        {
            bricks.get(i).setHalfWidth(11);
            x += 25;
            bricks.get(i).setX(x);
            bricks.get(i).setY(y);
            
            if((i+1) % 24 == 0)
            {
                x = -12.5;
                y -= 25;
            }
        }
        
        for(int i = 0; i < bricks.size(); i++)
        {
            setBricks.add(new Brick(bricks.get(i).getX(), bricks.get(i).getY(), 11));
            
        }
    }
    
    //------------------------------------------------------------
    
    public void drawBricks()
    {
        for(int i = 0; i < bricks.size(); i++)
        {
             bricks.get(i).draw();
        }
    }
    
    //------------------------------------------------------------
    
    public boolean hitsBall(double x, double y, int index)// one ball given the index;
    {
        return balls.get(index).hits(x,y);
    }
    
    public boolean hitsBall(Point point, int index)// one ball given the index;
    {
        return balls.get(index).hits(point);
    }
    
    public double ballDistance(double x, double y, int index)// one ball given the index;
    {
        return balls.get(index).distance(x,y);
    }
    //------------------------------------------------------------
    
    public int hitsBricks(Ball ball)
    {
        for(int i = 0; i < bricks.size(); i++)
        {
            //if(bricks.get(i).closestSide(ball).equals("top") || bricks.get(i).closestSide(ball).equals("bottom"))
            if(bricks.get(i).withinX(ball.getX()))
            {
                if(ball.hits(ball.getX(), bricks.get(i).getY() + bricks.get(i).getHalfWidth()) || ball.hits(ball.getX(), bricks.get(i).getY() - bricks.get(i).getHalfWidth()))//if ball hits the top or bottom of the brick
                {
                    bricks.get(i).reduce(1);
                    
                    if(bricks.get(i).getStrength() == 0)
                    {
                        bricks.remove(i);
                        setBricks.remove(i);
                        i--;
                    }
                    return 0; // bounce vertically
                }
            }
            //else if(bricks.get(i).closestSide(ball).equals("left") || bricks.get(i).closestSide(ball).equals("right"))
            else if(bricks.get(i).withinY(ball.getY()))
            {
                if(ball.hits(bricks.get(i).getX() + bricks.get(i).getHalfWidth(), ball.getY()) || ball.hits(bricks.get(i).getX() - bricks.get(i).getHalfWidth(), ball.getY()))//if ball hits the right or left of the brick
                {    
                    bricks.get(i).reduce(1);
                    
                    if(bricks.get(i).getStrength() == 0)
                    {
                        bricks.remove(i);
                        setBricks.remove(i);
                        i--;
                    }
                    return 1; // bounce horizontally
                }
            }
        }
        return -1;
    }
    
    //-------------------------------------------------------
    
    public void ballsHitsBricks()
    {
        for(int i = 0; i < balls.size(); i++)
        {
            int bounce = hitsBricks(balls.get(i));
            if(bounce == 0)
                balls.get(i).bounceVertically();
            else if(bounce == 1)
                balls.get(i).bounceHorizontally();
        }
    }
    
    //-------------------------------------------------------
    
    public void setBricks()
    {
        for(int i = 0; i < bricks.size();i++)
        {
            bricks.get(i).setX(setBricks.get(i).getX());
            bricks.get(i).setY(setBricks.get(i).getY());
        }
    }
    
    //-------------------------------------------------------
    
    public boolean lose()
    {
        int i = 0;
        for(Ball ball : balls)
        {
            if(ball.lose())
            {
                return true;
            }
            i++;
        }
        return false;
    }
    
    public boolean win()
    {
        return bricks.size() == 0;
    }
    
}
