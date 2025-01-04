
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    
    public boolean Up, Down, Left, Right;
    
    @Override
    public void keyTyped(KeyEvent e) {
        //no implementation needed 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code= e.getKeyCode();

        if(code == KeyEvent.VK_W){

            Up= true;

        }

        if(code == KeyEvent.VK_S){

            Down= true;

        }

        if(code == KeyEvent.VK_A){

            Left= true;

        }

        if(code == KeyEvent.VK_D){

            Right= true;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code= e.getKeyCode();

        if(code == KeyEvent.VK_W){

            Up= false;

        }

        if(code == KeyEvent.VK_S){

            Down= false;

        }

        if(code == KeyEvent.VK_A){

            Left= false;

        }

        if(code == KeyEvent.VK_D){

            Right= false;

        }


    }

    



}
