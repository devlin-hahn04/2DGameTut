import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

    // Animation frames
    BufferedImage[] walkingRightFrames;
    BufferedImage[] walkingLeftFrames;
    BufferedImage[] walkingUpFrames;
    BufferedImage[] walkingDownFrames;

    // Animation control variables
    int currentFrame = 0;
    int frameDelay = 10; // Number of updates before switching to the next frame
    int frameCounter = 0; // To track when to switch frames


    public Player(GamePanel gp, KeyHandler keyH){

        this.gp= gp;
        this.keyH= keyH;

        setDefaultValues();
        GetPlayerImage();

    }

    public void setDefaultValues(){

        x= 100;
        y= 100;
        speed= 4;
        direction= "down";  // initial direction

    }

    public void GetPlayerImage(){

        try {

            walkingUpFrames = new BufferedImage[2];
            walkingUpFrames[0]= ImageIO.read(getClass().getResourceAsStream("/res/boy_up_1.png"));
            walkingUpFrames[1]= ImageIO.read(getClass().getResourceAsStream("/res/boy_up_2.png"));

            walkingDownFrames= new BufferedImage[2];
            walkingDownFrames[0]= ImageIO.read(getClass().getResourceAsStream("/res/boy_down_1.png"));
            walkingDownFrames[1]= ImageIO.read(getClass().getResourceAsStream("/res/boy_down_2.png"));

            walkingLeftFrames= new BufferedImage[2];
            walkingLeftFrames[0]= ImageIO.read(getClass().getResourceAsStream("/res/boy_left_1.png"));
            walkingLeftFrames[1]= ImageIO.read(getClass().getResourceAsStream("/res/boy_left_2.png"));


            walkingRightFrames= new BufferedImage[2];
            walkingRightFrames[0]= ImageIO.read(getClass().getResourceAsStream("/res/boy_right_1.png"));
            walkingRightFrames[1]= ImageIO.read(getClass().getResourceAsStream("/res/boy_right_2.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {
        // Check for movement
        if (keyH.Up) {
            direction = "up";
            y -= speed;
        } else if (keyH.Down) {
            direction = "down";
            y += speed;
        } else if (keyH.Left) {
            direction = "left";
            x -= speed;
        } else if (keyH.Right) {
            direction = "right";
            x += speed;
        } else {
            // No movement
            frameCounter = 0;  // Reset frame counter
            return;            // Exit update
        }
    
        // Animation switching logic
        frameCounter++;
        
        if (frameCounter >= frameDelay) {
            // Update current frame based on direction
            switch (direction) {
                case "up":
                    currentFrame = (currentFrame + 1) % walkingUpFrames.length;
                    break;
                case "down":
                    currentFrame = (currentFrame + 1) % walkingDownFrames.length;
                    break;
                case "left":
                    currentFrame = (currentFrame + 1) % walkingLeftFrames.length;
                    break;
                case "right":
                    currentFrame = (currentFrame + 1) % walkingRightFrames.length;
                    break;
            }
            
            frameCounter = 0;  // Reset counter after switching frames
        }
    }
    

    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);      this was for white rectangle 
        // g2.fillRect(x, y, gp.TileSize, gp.TileSize);

        BufferedImage currentImage= null;

        switch(direction){

            case "up":

                currentImage= walkingUpFrames[currentFrame];

                break;

            case "down":
                
                currentImage= walkingDownFrames[currentFrame];

                break;

            case "left":
                
                currentImage= walkingLeftFrames[currentFrame]; 

                break;

            case "right":

                currentImage= walkingRightFrames[currentFrame];

                break;

        }

        g2.drawImage(currentImage, x, y, gp.TileSize, gp.TileSize, null);

    }


}
