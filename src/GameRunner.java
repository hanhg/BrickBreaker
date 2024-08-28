
import java.awt.*;
import java.util.*;
public class GameRunner 
{
    final private static int width = 600;
    final private static int height = 600;
    
    public static void main(String[] args)
    {
        
        
        while(true)
        {
            StdDraw.setCanvasSize(width, height);
            StdDraw.setXscale(0, width);
            StdDraw.setYscale(0, height);
            
            Game run = new Game(width, height, 144);
            run.organizeBricks();
            StdDraw.picture(width/2, height/2, "Background.png");
            run.drawSlider();
            run.drawBalls();
            run.drawBricks();
            StdDraw.text(300, 100, "Press Enter to Start");
            StdDraw.show();
            
            while(true)
            {
                if(StdDraw.isKeyPressed(10))
                {
                    break;
                }
            }

            StdDraw.pause(1000);
            StdDraw.enableDoubleBuffering();
            while(true)
            {
                //StdDraw.pause(1);//part of animate()
                
                if(run.lose() || run.win())
                    break;
                
                run.drawSlider();
                run.drawBalls();
                run.drawBricks();

                run.hitsBorder(width, height);
                run.hitsSlider();

                run.setBricks();
                run.ballsHitsBricks();
                run.updateBalls();
                run.setBricks();


                if(StdDraw.isKeyPressed('d') || StdDraw.isKeyPressed('D'))
                    run.sliderMoveRight(width);
                if(StdDraw.isKeyPressed('a') || StdDraw.isKeyPressed('A'))
                    run.sliderMoveLeft(width);

                
                animate();
            }
            StdDraw.pause(500);
            if(run.lose())
            {
                StdDraw.setPenColor(13, 77, 94);
                StdDraw.filledRectangle(width/2, height/2, width/2, height/2);
                double x = width/2+11;
                double y = width + 40;
                while(y > width/2)
                {
                    StdDraw.picture(x,y, "Lose Screen.png");
                    StdDraw.show();
                    StdDraw.filledRectangle(width/2, height/2, width/2, height/2);
                    StdDraw.pause(10);
                    y-=4;
                }
                
                StdDraw.setPenColor(Color.BLACK);
            }
            else
            {
                StdDraw.setPenColor(5,5,5);
                StdDraw.filledRectangle(width/2, height/2, width/2, height/2);
                StdDraw.setFont();
            }
            StdDraw.pause(1000);
        }
    }
    
    public static void animate()// needs to pause
    {
        
        StdDraw.show();
        StdDraw.setPenColor(13, 77, 94);//55, 170, 219
        StdDraw.picture(width/2, height/2, "Background.png");
        StdDraw.setPenColor(Color.BLACK);
    }
    public static void drawing(Game run)
    {
        run.drawSlider();
        run.drawBalls();
    }
}