package controller;

import model.ChessPiece;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class GameController{


    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    private int blackScore;
    private int whiteScore;
    private List<String> process;
    private boolean isCheat;
    private ChessPiece PossibleMoves;

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {//初始化
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;

        reStart();

    }

    public void restartBoard(){
        gamePanel.initialGame();
        gamePanel.repaint();

        statusPanel.init();
        statusPanel.repaint();

        currentPlayer=ChessPiece.BLACK;

        blackScore=gamePanel.getblack();
        whiteScore=gamePanel.getwhite();

        isCheat=false;

        PossibleMoves=ChessPiece.DARK_GRAY;
    }

    public void reStart(){
        restartBoard();
        process=new ArrayList<String>();
    }

    public void swapPlayer(){//交换操作
        countScore();
        statusPanel.setScoreText(blackScore, whiteScore);
        if(gamePanel.haveSpace(currentPlayer.next(),PossibleMoves.next()))
        {
            currentPlayer = currentPlayer.next();
            PossibleMoves=PossibleMoves.next();
            statusPanel.setPlayerText(currentPlayer.name());
        }
        else if(!gamePanel.haveSpace(currentPlayer,PossibleMoves))
        {
            gameFinish();
            return;
        }
        gamePanel.repaint();
    }

    public void gameFinish(){
        JFrame endFrame=new JFrame();
        countScore();
        if(blackScore>whiteScore)
            endFrame.setTitle("BLACK WIN!");
        else if(blackScore<whiteScore)
            endFrame.setTitle("WHITE WIN!");
        else
            endFrame.setTitle("There is no winner.");
        endFrame.setLayout(null);
        endFrame.setSize(200,100);
        endFrame.setLocationRelativeTo(null);
        JButton OK=new JButton("OK");
        OK.setSize(100,50);
        OK.setLocation(50,10);
        endFrame.add(OK);
        OK.addActionListener(e->{
            endFrame.dispose();
        });
        endFrame.setVisible(true);
    }

    public void countScore(){
        blackScore=gamePanel.getblack();
        whiteScore=gamePanel.getwhite();
    }

    public boolean isCheating(){
        return isCheat;
    }

    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }

    public void readFileData(String fileName) {
        if(fileName.length()<5||!fileName.substring(fileName.length() - 4).equals(".txt"))
        {
            wrong_File(104);
            return;
        }
        List<String> fileData = new ArrayList<>();
        process = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
            bufferedReader.close();
            fileReader.close();
            fileData.forEach(System.out::println);
        } catch (IOException e) {
            wrong_File(106);
            e.printStackTrace();
        }

        gamePanel.initialGame();
        gamePanel.repaint();
        currentPlayer=ChessPiece.BLACK;
        PossibleMoves=ChessPiece.DARK_GRAY;

        String str;
        for(int i=0;i<8;i++)
        {
            if(fileData.get(i).length()!=24)
            {
                wrong_File(101);
                return;
            }
            for(int j=0;j<8;j++)
            {
                str=fileData.get(i).substring(j*3,j*3+3);
                if(str.equals("   "))
                {
                    wrong_File(101);
                    return;
                }
                if(!str.equals("  0") && !str.equals("  1") && !str.equals(" -1"))
                {
                    wrong_File(102);
                    return;
                }
            }
        }

        str=fileData.get(8);
        if(!str.equals("1") && !str.equals("-1"))
        {
            wrong_File(103);
            return;
        }

        int n=fileData.get(9).charAt(0)-33;
        if(n!=fileData.size()-10){
            wrong_File(106);
            return;
        }
        if(n==60)
        {
            wrong_File(102);
            return;
        }
        char mode;
        int x,y;
        for(int i=10;i<=n+9;i++){
            if(fileData.get(i).length()!=3||(fileData.get(i).charAt(0)!='C'&&fileData.get(i).charAt(0)!='N')||fileData.get(i).charAt(1)<'0'||fileData.get(i).charAt(1)>'7'||fileData.get(i).charAt(2)<'0'||fileData.get(i).charAt(2)>'7'){
                wrong_File(105);
                return;
            }
            mode=fileData.get(i).charAt(0);
            x=fileData.get(i).charAt(1)-'0';
            y=fileData.get(i).charAt(2)-'0';
            if(mode=='N')
            {
                if(!canClick(x, y))
                {
                    wrong_File(105);
                    return;
                }
                change(x,y);
                swapPlayer();
            }
            else if(mode=='C'){
                cheatOn(x,y);
                swapPlayer();
            }
            else{
                wrong_File(105);
                return;
            }
        }

        if((currentPlayer==ChessPiece.BLACK&&!str.equals("1"))||(currentPlayer==ChessPiece.WHITE&&!str.equals("-1")))
        {
            wrong_File(107);
            return;
        }
        str="";
        for(int i=0;i<8;i++)
            str=str+fileData.get(i)+"\n";
        if(!str.equals(gamePanel.toString()))
        {
            wrong_File(107);
            return;
        }
    }

    private void wrong_File(int wrongNum){
        System.out.println("Wrong"+wrongNum);
        reStart();
//        JFrame wrongFileFrame = new JFrame();
//        wrongFileFrame.setTitle("错误编码："+wrongNum);
//        wrongFileFrame.setLayout(null);
//        wrongFileFrame.setSize(400,200);
//        wrongFileFrame.setLocationRelativeTo(null);
//
//        JLabel wrongLabel=new JLabel();
//        wrongLabel.setLocation(50,10);
//        wrongLabel.setSize(300,80);
//        wrongLabel.setFont(new Font("Calibri",Font.ITALIC,15));

        switch (wrongNum){
            case 101:
                JOptionPane.showMessageDialog(this.gamePanel,"棋盘并非8*8","错误编码: "+wrongNum,JOptionPane.WARNING_MESSAGE);
                break;
            case 102:
                JOptionPane.showMessageDialog(this.gamePanel,"棋子错误","错误编码: "+wrongNum,JOptionPane.WARNING_MESSAGE);
                break;
            case 103:
                JOptionPane.showMessageDialog(this.gamePanel,"缺少行棋方或行棋方棋子错误","错误编码: "+wrongNum,JOptionPane.WARNING_MESSAGE);
                break;
            case 104:
                JOptionPane.showMessageDialog(this.gamePanel,"文件格式错误","错误编码: "+wrongNum,JOptionPane.WARNING_MESSAGE);
                break;
            case 105:
                JOptionPane.showMessageDialog(this.gamePanel,"非法落子，存在不合法的步骤","错误编码: "+wrongNum,JOptionPane.WARNING_MESSAGE);
                break;
            case 106:
                JOptionPane.showMessageDialog(this.gamePanel,"其他错误","错误编码: "+wrongNum,JOptionPane.WARNING_MESSAGE);
                break;
            case 107:
                JOptionPane.showMessageDialog(this.gamePanel,"先前步骤与棋盘不匹配","错误编码: "+wrongNum,JOptionPane.WARNING_MESSAGE);
                break;
        }

//        switch (wrongNum){
//            case 101:
//                wrongLabel.setText("棋盘并非8*8");
//                break;
//            case 102:
//                wrongLabel.setText("棋子错误");
//                break;
//            case 103:
//                wrongLabel.setText("缺少行棋方");
//                break;
//            case 104:
//                wrongLabel.setText("文件格式错误");
//                break;
//            case 105:
//                wrongLabel.setText("非法落子，存在不合法的步骤");
//                break;
//            case 106:
//                wrongLabel.setText("其他错误");
//                break;
//            case 107:
//                wrongLabel.setText("先前步骤与棋盘不匹配");
//                break;
//        }
//        wrongFileFrame.add(wrongLabel);
//
//        JButton OK=new JButton("OK");
//        OK.setSize(100,50);
//        OK.setLocation(150,100);
//        wrongFileFrame.add(OK);
//        OK.addActionListener(e->{
//            wrongFileFrame.dispose();
//        });
//        wrongFileFrame.setVisible(true);

    }//*会刷新当前棋盘

    public void writeDataToFile(String fileName) {
        try
        {
            FileWriter fileWriter=new FileWriter(fileName);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(gamePanel.toString());
            if(currentPlayer==ChessPiece.BLACK)
                bufferedWriter.write("1\n");
            else
                bufferedWriter.write("-1\n");
            bufferedWriter.write((char)(process.size()+33)+"\n");
            for(String toWrite : process){
                bufferedWriter.write(toWrite);
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }

    public void change(int row,int col){
        gamePanel.change(row,col,currentPlayer);
        process.add("N"+row+col+"\n");
    }

    public void changeMode(){
        isCheat=!isCheat;
//        if(isCheat){
//            statusPanel.setModeText("Cheat");
//        }
//        else{
//            statusPanel.setModeText("Normal");
//        }
    }

    /*public void changeMode(){
        isCheat=!isCheat;
        if(isCheat){
            statusPanel.setModeText("Cheat");
            statusPanel.isCheating();
            PossibleMoves=null;
            gamePanel.clear();
        }
        else{
            statusPanel.setModeText("Normal");
            if(!gamePanel.haveSpace(ChessPiece.BLACK,PossibleMoves)&&!gamePanel.haveSpace(ChessPiece.WHITE,PossibleMoves))
                gameFinish();
            else{
                JFrame choosePlayerFrame = new JFrame();
                choosePlayerFrame.setTitle("Choose the next player");
                choosePlayerFrame.setLayout(null);
                choosePlayerFrame.setSize(400,200);
                choosePlayerFrame.setLocationRelativeTo(null);
                JButton blackBtn=new JButton("BLACK");
                blackBtn.setSize(100,50);
                blackBtn.setLocation(50,50);
                blackBtn.addActionListener(e->{
                    currentPlayer=ChessPiece.BLACK;
                    statusPanel.setPlayerText(currentPlayer.name());
                    PossibleMoves=ChessPiece.DARK_GRAY;
                    choosePlayerFrame.dispose();
                    if(!gamePanel.haveSpace(currentPlayer,PossibleMoves)){
                        currentPlayer=currentPlayer.next();
                        PossibleMoves=PossibleMoves.next();
                        statusPanel.setPlayerText(currentPlayer.name());
                    }
                    gamePanel.repaint();
                });
                choosePlayerFrame.add(blackBtn);
                JButton whiteBtn=new JButton("WHITE");
                whiteBtn.setSize(100,50);
                whiteBtn.setLocation(250,50);
                whiteBtn.addActionListener(e -> {
                    currentPlayer=ChessPiece.WHITE;
                    statusPanel.setPlayerText(currentPlayer.name());
                    PossibleMoves=ChessPiece.LIGHT_GRAY;
                    choosePlayerFrame.dispose();
                    if(!gamePanel.haveSpace(currentPlayer,PossibleMoves)){
                        currentPlayer=currentPlayer.next();
                        PossibleMoves=PossibleMoves.next();
                        statusPanel.setPlayerText(currentPlayer.name());
                    }
                    gamePanel.repaint();
                });
                choosePlayerFrame.add(whiteBtn);
                choosePlayerFrame.setVisible(true);
                countScore();
                statusPanel.setScoreText(blackScore,whiteScore);
            }
        }
    }*/

    public void cheatOn(int row,int col){
        gamePanel.cheatOn(row,col,currentPlayer);
        process.add("C"+row+col+"\n");
    }

    public void getUndo(){
        restartBoard();
        process.remove(process.size()-1);
        char mode;
        int x,y;
        for(int i=0;i<process.size();i++){
            mode=process.get(i).charAt(0);
            x=process.get(i).charAt(1)-'0';
            y=process.get(i).charAt(2)-'0';
            if(mode=='N')
            {
                gamePanel.change(x,y,currentPlayer);
                swapPlayer();
            }
            else if(mode=='C'){
                gamePanel.cheatOn(x,y,currentPlayer);
                swapPlayer();
            }
        }
    }

}



