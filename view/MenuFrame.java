package view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.ChessPiece;
import model.ImageValue;
import music.MusicStuff;
import controller.MachineMode;

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



        JLabel bg = new JLabel();
        bg.setSize(this.getWidth(),this.getHeight());
        setIcon(ImageValue.background,bg);
        JPanel pan = (JPanel) this.getContentPane();
        this.getLayeredPane().add(bg,new Integer(Integer.MIN_VALUE));
        pan.setOpaque(false);
        pan.setLayout(null);

        JLabel gametitle = new JLabel();
        gametitle.setBorder(null);
        gametitle.setOpaque(false);
        gametitle.setSize(this.getFramesize()*5/15,this.getFramesize()*5/15);
        gametitle.setLocation((this.getFramesize()-(gametitle.getWidth()))/2,(this.getFramesize()-gametitle.getHeight())/50);
        setIcon(ImageValue.gametitle,gametitle);
        gametitle.setVisible(true);
        this.add(gametitle);


        JButton playButton=new JButton();
//        normalModeBtn.setText("Normal Mode");
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setBorder(null);
        //the order cannot be changed here
        playButton.setSize(this.getFramesize()*4/15,this.getFramesize()*2/15);
        setIcon(ImageValue.playButtonup,playButton);
        playButton.setLocation((this.getFramesize()-(playButton.getWidth()))/2,(this.getFramesize()-playButton.getHeight())*7/15);
        pan.add(playButton);
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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
            GameFrame.controller.playNormalMode();
        });

        //todo playwithmachine button
        JButton playAIbutton = new JButton();
        playAIbutton.setLocation(playButton.getX(),(this.getFramesize()-playButton.getHeight())*10/15);
        playAIbutton.setSize(playButton.getSize());
        playAIbutton.setOpaque(false);
        playAIbutton.setContentAreaFilled(false);
        playAIbutton.setFocusPainted(false);
        playAIbutton.setBorder(null);
        setIcon(ImageValue.VScomup,playAIbutton);
        pan.add(playAIbutton);
        playAIbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setIcon(ImageValue.VScomdown,playAIbutton);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setIcon(ImageValue.VScomup,playAIbutton);
            }
        });
        playAIbutton.addActionListener(e -> {
            GameFrame.controller.reStart();
            gameFrame.setVisible(true);
            this.setVisible(false);
            JFrame chframe=new JFrame("Choose you color");
            chframe.setSize(300,200);
            chframe.setLayout(null);
            chframe.setLocationRelativeTo(null);
            chframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            chframe.setVisible(true);

            JLabel bgg = new JLabel();
            bgg.setSize(chframe.getWidth(),chframe.getHeight());
            setIcon(ImageValue.backgroundtest,bgg);
            JPanel pann = (JPanel)chframe.getContentPane();
//        this.setContentPane(pan);
           chframe.getLayeredPane().add(bgg,new Integer(Integer.MIN_VALUE));
            pann.setOpaque(false);
            pann.setLayout(null);

            JButton blackBtn=new JButton();
            blackBtn.setOpaque(false);
            blackBtn.setContentAreaFilled(false);
            blackBtn.setBorder(null);
            blackBtn.setSize(100,100);
            setIcon(ImageValue.blackchess,blackBtn);
            blackBtn.setLocation((chframe.getWidth()-2*blackBtn.getWidth())/3,(chframe.getHeight()-blackBtn.getHeight())/3);
            blackBtn.addActionListener(ee->{
                GameFrame.controller.setMechineColor(ChessPiece.WHITE);
                GameFrame.controller.playMechineMode();
                MachineMode.setToSearch(3);
                chframe.dispose();
            });
            chframe.add(blackBtn);

            JButton whiteBtn=new JButton();
            whiteBtn.setOpaque(false);
            whiteBtn.setBorder(null);
            whiteBtn.setContentAreaFilled(false);
            whiteBtn.setSize(100,100);
            setIcon(ImageValue.whitechess,whiteBtn);
            whiteBtn.setLocation((chframe.getWidth()-(chframe.getWidth()-2*blackBtn.getWidth()))*3/4,blackBtn.getY());
            whiteBtn.addActionListener(ee->{
                GameFrame.controller.setMechineColor(ChessPiece.BLACK);
                GameFrame.controller.playMechineMode();
                GameFrame.controller.change(2,3);
                GameFrame.controller.swapPlayer();
                MachineMode.setToSearch(3);
                chframe.dispose();
            });
            chframe.add(whiteBtn);
        });


        //todo instruction button
        JButton instructionbtn = new JButton();
        instructionbtn.setLocation(playButton.getX(),(this.getFramesize()-playButton.getHeight())*13/15);
        instructionbtn.setSize(playButton.getSize());
        instructionbtn.setOpaque(false);
        instructionbtn.setContentAreaFilled(false);
        instructionbtn.setFocusPainted(false);
        instructionbtn.setBorder(null);
        setIcon(ImageValue.instructionbtnup,instructionbtn);
        pan.add(instructionbtn);
        instructionbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setIcon(ImageValue.instructionbtndown,instructionbtn);

            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setIcon(ImageValue.instructionbtnup,instructionbtn);
            }
        });

        instructionbtn.addActionListener(e -> {
            //todo for a panel showing instructions.
            List<String> fileData = new ArrayList<>();
            try {
                FileReader fileReader = new FileReader("Rules.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    fileData.add(line);
                }
                bufferedReader.close();
                fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String kk = "";
            for(String str:fileData){
                kk = kk + str + "\n";
            }

            JOptionPane.showMessageDialog(null,kk,"rules",JOptionPane.INFORMATION_MESSAGE);
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

    public void setIcon(ImageIcon imi,JLabel label){
        ImageIcon ii = imi;
        Image temp = ii.getImage().getScaledInstance(label.getWidth(),label.getHeight(), ii.getImage().SCALE_DEFAULT);
        ii = new ImageIcon(temp);
        label.setIcon(ii);
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