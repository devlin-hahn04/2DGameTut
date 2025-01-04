import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

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
        direction= "down";

    }

    public void GetPlayerImage(){

        try {

            up1= ImageIO.read(getClass().getResourceAsStream("/res/boy_up_1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/res/boy_up_2.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/res/boy_down_1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/res/boy_down_2.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/res/boy_left_1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/res/boy_left_2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/res/boy_right_1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/res/boy_right_2.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update(){

        if(keyH.Up == true){

            direction= "up";

            y -= speed;

        }

        else if(keyH.Down == true){

            direction= "down";

            y += speed;

        }

        else if(keyH.Left == true){

            direction= "left";

            x -= speed;

        }

        else if(keyH.Right == true){

            direction= "right";

            x += speed;

        }

        SpriteCounter++;

        if(SpriteCounter > 10){

            if(SpriteNum == 1){
                SpriteNum= 2;
            }

            else if(SpriteNum == 2){
                SpriteNum= 1;
            }

            SpriteCounter= 0; 
            
        }

    }

    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);      this was for white rectangle 
        // g2.fillRect(x, y, gp.TileSize, gp.TileSize);

        BufferedImage image= null;

        switch(direction){

            case "up":

                if(SpriteNum == 1){image= up1;}

                if(SpriteNum == 2){image= up2;}

                break;

            case "down":
                
                if(SpriteNum == 1){image= down1;}

                if(SpriteNum == 2){image= down2;}

                break;

            case "left":
                
                if(SpriteNum == 1){image= left1;}

                if(SpriteNum == 2){image= left2;}

                break;

            case "right":

                if(SpriteNum == 1){image= right1;}

                if(SpriteNum == 2){image= right2;}

                break;

        }

        g2.drawImage(image, x, y, gp.TileSize, gp.TileSize, null);

    }


}
