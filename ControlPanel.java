import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel
{
    private World world;
    private JButton btnStep, btnStart, btnReset, btnSpecial;
    private Timer t;

    public ControlPanel(World w)
    {
        world = w;

        t = new Timer(200/*half a second*/, 
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e){
                    world.step();
                }
            }
        );
        t.setRepeats(true);
        JPanel pnlSouth = new JPanel();

        btnStep = new JButton("Step");
        pnlSouth.add(btnStep);
        btnStep.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    world.step();
                }
            }
        );

        btnStart = new JButton ("Start");
        pnlSouth.add(btnStart);
        btnStart.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    JButton btn = (JButton)e.getSource();
                    if(btn.getText().equals("Start")){
                        t.start();
                        btn.setText("Stop");
                    }else{
                        t.stop();
                        btn.setText("Start");
                    }
                }
            }
        );

        btnReset = new JButton ("Reset");
        pnlSouth.add(btnReset);
        btnReset.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    world.reset();
                }
            }
        );
        btnSpecial = new JButton ("Pattern");
        pnlSouth.add(btnSpecial);
        
        btnSpecial.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    world.pattern();
                }
            }
        );
        add(pnlSouth , BorderLayout.SOUTH);
    }

}
