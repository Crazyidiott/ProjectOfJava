package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuFrame extends JFrame
{
    private GameFrame gameFrame;
    public MenuFrame(int frameSize){
        this.setTitle("Menu");
        this.setLayout(null);

        Insets insets=this.getInsets();
        this.setSize(frameSize+insets.left+insets.right,frameSize+insets.top+insets.bottom);

        this.setLocationRelativeTo(null);

        JButton normalModeBtn=new JButton();
        normalModeBtn.setText("Normal Mode");
        normalModeBtn.setSize(120,50);
        normalModeBtn.setLocation(340,300);
        add(normalModeBtn);
        normalModeBtn.addActionListener(e -> {
            GameFrame.controller.reStart();
            gameFrame.setVisible(true);
            this.setVisible(false);
        });

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(
                new WindowAdapter()
                {
                    public void windowClosing(WindowEvent e)
                    {
                        setVisible(false);
                        gameFrame.close();
                    }
                });
    }

    public void close(){
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkExit(0);
        }
    }

    public void setGameFrame(GameFrame gameFrame)
    {
        this.gameFrame = gameFrame;
    }
}
