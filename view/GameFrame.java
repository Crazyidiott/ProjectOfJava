package view;


import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame{
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    private MenuFrame menuFrame;
    private int gameframesize;

    public GameFrame(int frameSize,MenuFrame menuFrame){
        this.menuFrame=menuFrame;
        this.gameframesize = frameSize;

        this.setTitle("2021F CS102A Project Reversi");//标题
        this.setLayout(null);//设置格式布局（清空布局）

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);//窗口至于屏幕中央

        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);

        controller = new GameController(chessBoardPanel, statusPanel);

        this.add(chessBoardPanel);
        this.add(statusPanel);

        JButton restartBtn = new JButton("Restart");//创建一个重新开始按钮
        restartBtn.setSize(this.gameframesize*3/20, this.gameframesize/16);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2-60, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addActionListener(e -> {
            controller.reStart();
            System.out.println("click restart Btn");
        });

        JButton loadGameBtn = new JButton("Load");//创建一个读入按钮
        loadGameBtn.setSize( restartBtn.getWidth(),restartBtn.getHeight());
        loadGameBtn.setLocation(restartBtn.getX()+restartBtn.getWidth()+20, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            System.out.println("clicked Load Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.readFileData(filePath);
        });

        JButton saveGameBtn = new JButton("Save");//创建一个保存按钮
        saveGameBtn.setSize( restartBtn.getWidth(),restartBtn.getHeight());
        saveGameBtn.setLocation(loadGameBtn.getX()+loadGameBtn.getWidth()+20, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            System.out.println("clicked Save Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here","SAVE",JOptionPane.INFORMATION_MESSAGE);
            controller.writeDataToFile(filePath);
        });

        JButton undoBtn=new JButton("Undo operation");
        undoBtn.setSize( restartBtn.getWidth(),restartBtn.getHeight());
        undoBtn.setLocation(saveGameBtn.getX()+saveGameBtn.getWidth()+20,restartBtn.getY());
        add(undoBtn);
        undoBtn.addActionListener(e -> {
            System.out.println("click undo button");
            controller.getUndo();
        });

        JButton changeModeBtn=new JButton("Normal/Cheat");
        changeModeBtn.setSize( restartBtn.getWidth(),restartBtn.getHeight());
        changeModeBtn.setLocation(undoBtn.getX()+undoBtn.getWidth()+20,restartBtn.getY());
        add(changeModeBtn);
        changeModeBtn.addActionListener(e ->{
            System.out.println("someone is cheating!");
            controller.changeMode();
        });

        JButton menuBtn=new JButton("<- Back to Menu");
        menuBtn.setSize(70,50);
        menuBtn.setLocation(0,20);
        add(menuBtn);
        menuBtn.addActionListener(e -> {
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
}
