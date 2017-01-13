import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener
{
    private World game;
    
    public GUI()
    {

        super();
        setLayout (new BorderLayout());

        game = new World(30);
        add(game,BorderLayout.CENTER);
        
        ControlPanel cp = new ControlPanel(game);
        add(cp,BorderLayout.SOUTH);
        setTitle("Game of Life Simulation");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent event){

        this.setBackground(Color.GREEN);
    }
}