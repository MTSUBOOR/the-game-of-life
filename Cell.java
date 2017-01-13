import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Cell extends JButton 
{
    private boolean alive;
    
    public Cell()
    {
        alive = false;
        this.setBackground(Color.BLACK);
        update();
    }
    
    public boolean isAlive(){
        return alive;        
    }
    
    public void setState(boolean alive){
        this.alive = alive;
        update();
    }
    
    public void changeState(){
        alive = !alive;
        update();
    }
    private void update(){
        if(alive){
            this.setBackground(Color.GREEN);
        }else{
            this.setBackground(Color.BLACK);
        }
    }
    
}
