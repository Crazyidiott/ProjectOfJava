package view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import jdk.nashorn.internal.runtime.UnwarrantedOptimismException;
import model.ImageValue;
import music.MusicStuff;

public class MenuFrame extends JFrame {

    private int framesize;
    private GameFrame gameFrame;
    private MusicStuff musicStuff;
    private JButton musicplaybtn;
    private JButton musicstopbtn;
    public MenuFrame(int frameSize,MusicStuff musicStuff){
        this.framesize = frameSize;
        this.musicStuff = musicStuff;
        this.setTitle("Menu");
        this.setLayout(null);

        Insets insets=this.getInsets();
        this.setSize(frameSize+insets.left+insets.right,frameSize+insets.top+insets.bottom);

        this.setLocationRelativeTo(null);

        JButton playButton=new JButton();
//        normalModeBtn.setText("Normal Mode");
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setBorder(null);
        //the order cannot be changed here
        playButton.setSize(this.getFramesize()*4/15,this.getFramesize()*2/15);
        setIcon(ImageValue.playButtonup,playButton);
        playButton.setLocation((this.getFramesize()-(playButton.getWidth()))/2,(this.getFramesize()-playButton.getHeight())*2/5);
        this.add(playButton);
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
//                System.out.println("233");
                setIcon(ImageValue.playButtondown,playButton);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setIcon(ImageValue.playButtonup,playButton);
            }
        });

        playButton.addActionListener(e -> {
            GameFrame.controller.reStart();
            gameFrame.setVisible(true);
            this.setVisible(false);
        });

        //todo playwithmachine button
        JButton playAIbutton = new JButton("AI");
        playAIbutton.setLocation((this.getFramesize()-(playButton.getWidth()))/2,(this.getFramesize()-playButton.getHeight())*3/5);
        playAIbutton.setSize(playButton.getSize());
//        playAIbutton.setOpaque(false);
//        playAIbutton.setContentAreaFilled(false);
//        playAIbutton.setFocusPainted(false);
//        playAIbutton.setBorder(null);
//        setIcon(,);
        this.add(playAIbutton);
        playAIbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
//                setIcon();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
//                setIcon();
            }
        });
        playAIbutton.addActionListener(e -> {
            //todo for connecting to ai playing
        });


        //todo instruction button
        JButton instructionbtn = new JButton("instruction");
        instructionbtn.setLocation((this.getFramesize()-(playButton.getWidth()))/2,(this.getFramesize()-playButton.getHeight())*4/5);
        instructionbtn.setSize(playButton.getSize());
//        instructionbtn.setOpaque(false);
//        instructionbtn.setContentAreaFilled(false);
//        instructionbtn.setFocusPainted(false);
//        instructionbtn.setBorder(null);
//        setIcon(,);
        this.add(instructionbtn);
        instructionbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
//                setIcon();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
//                setIcon();
            }
        });

        instructionbtn.addActionListener(e -> {
            //todo for a panel showing instructions.
        });





        musicstopbtn = new JButton();
        musicstopbtn.setOpaque(false);
        musicstopbtn.setContentAreaFilled(false);
        musicstopbtn.setFocusPainted(false);
        musicstopbtn.setBorder(null);
        musicstopbtn.setSize(50,50);
        setIcon(ImageValue.musicstopbutton,musicstopbtn);
        musicstopbtn.setLocation((int)((this.getFramesize()-musicstopbtn.getWidth())*0.95),this.getFramesize()/100);
        this.add(musicstopbtn);

        musicplaybtn = new JButton();
        musicplaybtn.setOpaque(false);
        musicplaybtn.setContentAreaFilled(false);
        musicplaybtn.setFocusPainted(false);
        musicplaybtn.setBorder(null);
        musicplaybtn.setSize(50,50);
        setIcon(ImageValue.musicplaybutton,musicplaybtn);
        musicplaybtn.setLocation(musicstopbtn.getLocation());
        musicplaybtn.setVisible(false);
        this.add(musicplaybtn);

        musicplaybtn.addActionListener(e -> {
            this.musicStuff.resumeTheMusic();
            musicplaybtn.setVisible(false);
            musicstopbtn.setVisible(true);
            gameFrame.getMusicplaybtn().setVisible(false);
            gameFrame.getMusicstopbtn().setVisible(true);

        });
        musicstopbtn.addActionListener(e -> {
            this.musicStuff.pauseTheMusic();
            musicstopbtn.setVisible(false);
            musicplaybtn.setVisible(true);
            gameFrame.getMusicplaybtn().setVisible(true);
            gameFrame.getMusicstopbtn().setVisible(false);
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

    public void setIcon(ImageIcon imi,JButton button){
        ImageIcon ii = imi;
        Image temp = ii.getImage().getScaledInstance(button.getWidth(), button.getHeight(), ii.getImage().SCALE_DEFAULT);
        ii = new ImageIcon(temp);
        button.setIcon(ii);
    }

    public int getFramesize() {
        return framesize;
    }

    public JButton getMusicplaybtn() {
        return musicplaybtn;
    }

    public JButton getMusicstopbtn() {
        return musicstopbtn;
    }
}
