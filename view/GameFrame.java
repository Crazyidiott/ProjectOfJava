package view;


import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;

    public GameFrame(int frameSize){
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
        restartBtn.setSize(120, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2-60, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addActionListener(e -> {
            controller.reStart();
            System.out.println("click restart Btn");
        });

        JButton loadGameBtn = new JButton("Load");//创建一个读入按钮
        loadGameBtn.setSize( 120,50);
        loadGameBtn.setLocation(restartBtn.getX()+restartBtn.getWidth()+20, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            System.out.println("clicked Load Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.readFileData(filePath);
        });

        JButton saveGameBtn = new JButton("Save");//创建一个保存按钮
        saveGameBtn.setSize(120, 50);
        saveGameBtn.setLocation(loadGameBtn.getX()+loadGameBtn.getWidth()+20, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            System.out.println("clicked Save Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.writeDataToFile(filePath);
        });

        JButton undoBtn=new JButton("Undo operation");
        undoBtn.setSize(120,50);
        undoBtn.setLocation(saveGameBtn.getX()+saveGameBtn.getWidth()+20,restartBtn.getY());
        add(undoBtn);
        undoBtn.addActionListener(e -> {
            System.out.println("click undo button");
            controller.getUndo();
        });

        JButton changeModeBtn=new JButton("Normal/Cheat");
        changeModeBtn.setSize(120,50);
        changeModeBtn.setLocation(undoBtn.getX()+undoBtn.getWidth()+20,restartBtn.getY());
        add(changeModeBtn);
        changeModeBtn.addActionListener(e ->{
            System.out.println("someone is cheating!");
            controller.changeMode();
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//点击关闭时关闭

    }
}
