package controller;

import model.ChessPiece;
import view.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


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

    public void reStart(){
        gamePanel.initialGame();
        gamePanel.repaint();

        statusPanel.init();
        statusPanel.repaint();

        currentPlayer=ChessPiece.BLACK;

        blackScore=gamePanel.getblack();
        whiteScore=gamePanel.getwhite();

        process=new ArrayList<String>();

        isCheat=false;

        PossibleMoves=ChessPiece.DARK_GRAY;
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
            e.printStackTrace();
        }

        gamePanel.initialGame();
        gamePanel.repaint();
        currentPlayer=ChessPiece.BLACK;
        PossibleMoves=ChessPiece.DARK_GRAY;

        int n=fileData.get(0).charAt(0)-33;
        if(n!=fileData.size()-1){
            wrong_File();
            return;
        }
        char mode;
        int x,y;
        for(int i=1;i<=n;i++){
            if(fileData.get(i).length()!=3||(fileData.get(i).charAt(0)!='C'&&fileData.get(i).charAt(0)!='N')||fileData.get(i).charAt(1)<'0'||fileData.get(i).charAt(1)>'7'||fileData.get(i).charAt(2)<'0'||fileData.get(i).charAt(2)>'7'){
                wrong_File();
                return;
            }
            mode=fileData.get(i).charAt(0);
            x=fileData.get(i).charAt(1)-'0';
            y=fileData.get(i).charAt(2)-'0';
            if(mode=='N')
            {
                if(!canClick(x, y))
                {
                    wrong_File();
                    return;
                }
                change(x,y);
                swapPlayer();
            }
            else{
                cheatOn(x,y);
            }
        }
    }

    private void wrong_File(){
        System.out.println("Wrong");
        reStart();
        JFrame wrongFileFrame = new JFrame();
        wrongFileFrame.setTitle("The file is incorrect");
        wrongFileFrame.setLayout(null);
        wrongFileFrame.setSize(200,100);
        wrongFileFrame.setLocationRelativeTo(null);
        JButton OK=new JButton("OK");
        OK.setSize(100,50);
        OK.setLocation(50,10);
        wrongFileFrame.add(OK);
        OK.addActionListener(e->{
            wrongFileFrame.dispose();
        });
        wrongFileFrame.setVisible(true);

    }//*会刷新当前棋盘

    public void writeDataToFile(String fileName) {
        try
        {
            FileWriter fileWriter=new FileWriter(fileName);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
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
    }

    public void cheatOn(int row,int col){
        gamePanel.cheatOn(row,col);
        process.add("C"+row+col+"\n");
    }

}
