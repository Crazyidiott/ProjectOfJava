package view;


import controller.GameController;
import model.ImageValue;
import music.MusicStuff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame{
    public static final JButton MUSICBTN = new JButton("Music");
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    private MenuFrame menuFrame;
    private int gameframesize;
    private MusicStuff musicStuff;

    public GameFrame(int frameSize,MenuFrame menuFrame,MusicStuff musicStuff){
        this.menuFrame=menuFrame;
        this.gameframesize = frameSize;
        this.musicStuff = musicStuff;
        musicStuff.playMusic();
        this.setTitle("2021F CS102A Project Reversi");//标题
        this.setLayout(null);//设置格式布局（清空布局）

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);//窗口至于屏幕中央

        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        statusPanel = new StatusPanel((int) (this.getWidth() * 0.7), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);

        controller = new GameController(chessBoardPanel, statusPanel);

        this.add(chessBoardPanel);
        this.add(statusPanel);
//todo seticon
        JButton restartBtn = new JButton();//创建一个重新开始按钮
        restartBtn.setOpaque(false);
        restartBtn.setContentAreaFilled(false);
        restartBtn.setFocusPainted(false);
        restartBtn.setBorder(null);
        restartBtn.setSize(this.gameframesize*3/20, this.gameframesize/14);
        setIcon(ImageValue.restartButtonup,restartBtn);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2-60, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
//                System.out.println("233");
                setIcon(ImageValue.restartButtondown,restartBtn);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setIcon(ImageValue.restartButtonup,restartBtn);
            }
        });

        restartBtn.addActionListener(e -> {
            setIcon(ImageValue.restartButtonup,restartBtn);
            controller.reStart();
            System.out.println("click restart Btn");
        });



        JButton loadGameBtn = new JButton();//创建一个读入按钮
        loadGameBtn.setOpaque(false);
        loadGameBtn.setContentAreaFilled(false);
        loadGameBtn.setFocusPainted(false);
        loadGameBtn.setBorder(null);
        loadGameBtn.setSize( restartBtn.getWidth(),restartBtn.getHeight());
        setIcon(ImageValue.loadButtonup,loadGameBtn);
        loadGameBtn.setLocation(restartBtn.getX()+restartBtn.getWidth()+20, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
//                System.out.println("233");
                setIcon(ImageValue.loadButtondown,loadGameBtn);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setIcon(ImageValue.loadButtonup,loadGameBtn);
            }
        });

        loadGameBtn.addActionListener(e -> {
            setIcon(ImageValue.loadButtonup,loadGameBtn);
            int op = JOptionPane.showConfirmDialog(this,"You will lose your chessboard data if you haven't save it.\nAre you sure to load new chessboard?","LOAD",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if(op == 0){
                System.out.println("clicked Load Btn");
                String filePath = JOptionPane.showInputDialog(this, "input the path here");
                controller.readFileData(filePath);}


        });


        JButton saveGameBtn = new JButton();//创建一个保存按钮
        saveGameBtn.setOpaque(false);
        saveGameBtn.setContentAreaFilled(false);
        saveGameBtn.setFocusPainted(false);
        saveGameBtn.setBorder(null);
        saveGameBtn.setSize( restartBtn.getWidth(),restartBtn.getHeight());
        setIcon(ImageValue.saveButtonup,saveGameBtn);
        saveGameBtn.setLocation(loadGameBtn.getX()+loadGameBtn.getWidth()+20, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
//                System.out.println("233");
                setIcon(ImageValue.saveButtondown,saveGameBtn);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setIcon(ImageValue.saveButtonup,saveGameBtn);
            }
        });
        saveGameBtn.addActionListener(e -> {
            setIcon(ImageValue.saveButtonup,saveGameBtn);
            System.out.println("clicked Save Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here","SAVE",JOptionPane.INFORMATION_MESSAGE);
            controller.writeDataToFile(filePath);

        });


        JButton undoBtn=new JButton();
        undoBtn.setOpaque(false);
        undoBtn.setContentAreaFilled(false);
        undoBtn.setFocusPainted(false);
        undoBtn.setBorder(null);
        undoBtn.setSize( restartBtn.getWidth(),restartBtn.getHeight());
        setIcon(ImageValue.undoButtonup,undoBtn);
        undoBtn.setLocation(saveGameBtn.getX()+saveGameBtn.getWidth()+20,restartBtn.getY());
        add(undoBtn);
        undoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
//                System.out.println("233");
                setIcon(ImageValue.undoButtondown,undoBtn);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setIcon(ImageValue.undoButtonup,undoBtn);
            }
        });

        undoBtn.addActionListener(e -> {
            setIcon(ImageValue.undoButtonup,undoBtn);
            System.out.println("click undo button");
            controller.getUndo();
        });

        JButton changeModeBtn=new JButton();
        changeModeBtn.setOpaque(false);
        changeModeBtn.setContentAreaFilled(false);
        changeModeBtn.setFocusPainted(false);
        changeModeBtn.setBorder(null);
        changeModeBtn.setSize( restartBtn.getWidth(),restartBtn.getHeight());
        setIcon(ImageValue.normalButtonup,changeModeBtn);
        changeModeBtn.setLocation(undoBtn.getX()+undoBtn.getWidth()+20,restartBtn.getY());
        add(changeModeBtn);
        changeModeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
//                System.out.println("233");
                if(controller.isCheating()){
                    setIcon(ImageValue.cheatButtondown,changeModeBtn);
                }
                if(!controller.isCheating()){
                    setIcon(ImageValue.normalButtondown,changeModeBtn);
                }

            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(controller.isCheating()){
                    setIcon(ImageValue.cheatButtonup,changeModeBtn);
                }
                if(!controller.isCheating()){
                    setIcon(ImageValue.normalButtonup,changeModeBtn);
                }
            }
        });

        changeModeBtn.addActionListener(e ->{
            //todo set different pic
            controller.changeMode();
            if(controller.isCheating()){System.out.println("someone is cheating!");}
            if(controller.isCheating()){
                setIcon(ImageValue.cheatButtonup,changeModeBtn);
            }
            if(!controller.isCheating()){
                setIcon(ImageValue.normalButtonup,changeModeBtn);
            }
        });


        JButton backBtn=new JButton();
        backBtn.setOpaque(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        backBtn.setBorder(null);
        backBtn.setSize((gameframesize-chessBoardPanel.getWidth())/2*3/4,(gameframesize-chessBoardPanel.getHeight())/2*3/4);
        setIcon(ImageValue.backButtonup,backBtn);
        backBtn.setLocation(0,0);
        add(backBtn);
        backBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setIcon(ImageValue.backButtonup,backBtn);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setIcon(ImageValue.backButtondown,backBtn);
            }
        });

        backBtn.addActionListener(e -> {
            //TODO 设置一个提醒保存弹窗？
            Object[] options = {"Yes","Save","Cancel"};
            int op = JOptionPane.showOptionDialog(this, "You will lose your current chessboard if you haven't save your data.\nAre you sure to back to menu?","Warning",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
//          int op = JOptionPane.showConfirmDialog(this,"You will lose your current chessboard if you haven't save your data.\nAre you sure to back to menu?","Warning",JOptionPane.OK_CANCEL_OPTION);
            if(op == 0){System.out.println("Click Menu Button");
            this.menuFrame.setVisible(true);
            this.setVisible(false);}
            if(op ==1){
                System.out.println("clicked Save Btn");
                String filePath = JOptionPane.showInputDialog(this, "input the path here","SAVE", JOptionPane.INFORMATION_MESSAGE);
                controller.writeDataToFile(filePath);}
        });


        JButton musicstopbtn = new JButton();
        musicstopbtn.setOpaque(false);
        musicstopbtn.setContentAreaFilled(false);
        musicstopbtn.setFocusPainted(false);
        musicstopbtn.setBorder(null);
        musicstopbtn.setSize(50,50);
        setIcon(ImageValue.musicstopbutton,musicstopbtn);
        musicstopbtn.setLocation(this.getGameframesize()*9/10,this.getGameframesize()/100);
        this.add(musicstopbtn);

        JButton musicplaybtn = new JButton();
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

        });
        musicstopbtn.addActionListener(e -> {
            this.musicStuff.pauseTheMusic();
            musicstopbtn.setVisible(false);
            musicplaybtn.setVisible(true);
        });

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//点击关闭时关闭

        this.addWindowListener(
                new WindowAdapter()
                {
                    public void windowClosing(WindowEvent e)
                    {
                        setVisible(false);
                        menuFrame.close();
                    }
                });
    }

    public void close(){
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkExit(0);
        }
    }

    public int getGameframesize() {
        return gameframesize;
    }

    public void setIcon(ImageIcon imi,JButton button){
        ImageIcon ii = imi;
        Image temp = ii.getImage().getScaledInstance(button.getWidth(), button.getHeight(), ii.getImage().SCALE_DEFAULT);
        ii = new ImageIcon(temp);
        button.setIcon(ii);
    }
}
