package view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.ImageValue;

public class MenuFrame extends JFrame {

    private int framesize;
    private GameFrame gameFrame;
    public MenuFrame(int frameSize){
        this.framesize = frameSize;
        this.setTitle("Menu");
        this.setLayout(null);

        Insets insets=this.getInsets();
        this.setSize(frameSize+insets.left+insets.right,frameSize+insets.top+insets.bottom);

        this.setLocationRelativeTo(null);

        JButton normalModeBtn=new JButton();
//        normalModeBtn.setText("Normal Mode");
        normalModeBtn.setOpaque(false);
        normalModeBtn.setContentAreaFilled(false);
        normalModeBtn.setFocusPainted(false);
        normalModeBtn.setBorder(null);
        //the order cannot be changed here
        normalModeBtn.setSize(this.getFramesize()*2/5,this.getFramesize()/5);
        setIcon(ImageValue.playButtonup,normalModeBtn);
        normalModeBtn.setLocation((this.getFramesize()-(normalModeBtn.getWidth()))/2,(this.getFramesize()-normalModeBtn.getHeight())/5);
        this.add(normalModeBtn);
        normalModeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
//                System.out.println("233");
                setIcon(ImageValue.playButtondown,normalModeBtn);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setIcon(ImageValue.playButtonup,normalModeBtn);
            }
        });

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

    public void setIcon(ImageIcon imi,JButton com){
        ImageIcon ii = imi;
        Image temp = ii.getImage().getScaledInstance(com.getWidth(), com.getHeight(), ii.getImage().SCALE_DEFAULT);
        ii = new ImageIcon(temp);
        com.setIcon(ii);
    }

    public int getFramesize() {
        return framesize;
    }
}
