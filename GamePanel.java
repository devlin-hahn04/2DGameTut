
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    
    //Screen settings
    final int OriginalTileSize= 16; //16x16 tile, defaulty size of characters and etc
    final int Scale= 3; //scaling to fit in modern computer window size 

    public int TileSize= OriginalTileSize * Scale; //48x48 tile 
    
    //Total amount of tiles to be displayed in a screen
    final int MaxScreenColumn= 16;
    final int MaxScreenRow= 12;
    final int ScreenWidth= TileSize * MaxScreenColumn;  //768 pixels 
    final int ScreenHeight= TileSize * MaxScreenRow;    //576 pixels

    //FPS
    int FPS= 60;

    KeyHandler KeyH= new KeyHandler();
    Thread GameThread;
    Player player= new Player(this, KeyH);

    // //set player defualt position   this was for white rectangle 
    // int PlayerX= 100;
    // int PlayerY= 100;
    // int PlayerSpeed= 4;

    public GamePanel(){

        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
        this.requestFocusInWindow();

    }

    public void StartGameThread(){

        GameThread= new Thread(this);   
        GameThread.start();

    }

    @Override
    public void run(){

        double DrawInterval= 1000000000/FPS;  //one billion nanoseconds = 1 second
        double NextDrawTime= System.nanoTime() + DrawInterval;

        while(GameThread != null){

            //Update information like character positions 
            update();


            //Draw the screen with updated info
            repaint();

            try {

                double RemainingTime= NextDrawTime - System.nanoTime();
                RemainingTime= RemainingTime/1000000;  //converts nano to milli

                if(RemainingTime < 0){

                    RemainingTime= 0;

                }
    
                Thread.sleep((long)RemainingTime);  //only takes milliseconds

                NextDrawTime += DrawInterval;
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }

    }

    public void update(){

        player.update();


    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2= (Graphics2D)g;

        player.draw(g2);

        g2.dispose();

    }

}
