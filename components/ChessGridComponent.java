package components;
//一个方格
import controller.GameController;
import model.*;
import view.GameFrame;

import javax.swing.*;
import java.awt.*;

public class ChessGridComponent extends BasicComponent{
    public static int chessSize;//棋子大小
    public static int gridSize;//格子大小
    public static Color gridColor = new Color(243, 163, 66);
    private ChessPiece chessPiece;
    private int row;//位置
    private int col;//位置

    public ChessGridComponent(int row, int col){
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;
    }

    @Override
    public void onMouseClicked(){//被点击
        System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
        if(getChessPiece()!=null)
            return;
        if(GameFrame.controller.isCheating())
        {
            GameFrame.controller.cheatOn(row,col);
            GameFrame.controller.swapPlayer();
        }
        else if(GameFrame.controller.canClick(row, col))
        {
            GameFrame.controller.change(row, col);
            GameFrame.controller.swapPlayer();
        }
    }

    public void onMousePressed(){}

    public ChessPiece getChessPiece(){
        if(chessPiece==ChessPiece.BLACK||chessPiece==ChessPiece.WHITE)
            return chessPiece;
        return null;
    }

    public void setChessPiece(ChessPiece chessPiece){
        this.chessPiece = chessPiece;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void drawPiece(Graphics g){//填充
        g.setColor(gridColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (getChessPiece() != null) {
//            g.setColor(chessPiece.getColor());
//            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);//圆形棋子
              if(chessPiece.getColor() == Color.BLACK){
                  long starttime = System.currentTimeMillis();
                  long end  = starttime + 30;
                  //todo test the time
//                  while(System.currentTimeMillis()<end){
//                      System.out.println("kk");
//                  }
                   g.drawImage(ImageValue.blackImage,(gridSize - chessSize) / 2,(gridSize - chessSize) / 2,chessSize,chessSize,null);
              }
              if(chessPiece.getColor()==Color.WHITE){

                  g.drawImage(ImageValue.whiteImage,(gridSize - chessSize) / 2,(gridSize - chessSize) / 2,chessSize,chessSize,null);
              }

        }
        else if(this.chessPiece!=null){
            g.setColor(chessPiece.getColor());
            for (int i = 0; i < 5; i++) {
                g.drawArc((gridSize - chessSize) / 2 + i, (gridSize - chessSize) / 2 + i, chessSize - i * 2, chessSize - i * 2, -30, 60);
                g.drawArc((gridSize - chessSize) / 2 + i, (gridSize - chessSize) / 2 + i, chessSize - i * 2, chessSize - i * 2, 60, 60);
                g.drawArc((gridSize - chessSize) / 2 + i, (gridSize - chessSize) / 2 + i, chessSize - i * 2, chessSize - i * 2, 150, 60);
                g.drawArc((gridSize - chessSize) / 2 + i, (gridSize - chessSize) / 2 + i, chessSize - i * 2, chessSize - i * 2, 240, 60);
            }
//            for(int i=0;i<5;i++) {
//                g.drawOval((gridSize - chessSize) / 2 + i, (gridSize - chessSize) / 2 + i, chessSize - i * 2, chessSize - i * 2);
//            }
            }
    }

    @Override//重构
    public void paintComponent(Graphics g){
        super.printComponents(g);
        drawPiece(g);
    }
}
