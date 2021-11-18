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
    public static Color gridColor = new Color(255, 150, 50);
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
        if(GameFrame.controller.isCheating())
        {
            GameFrame.controller.cheatOn(row,col);
        }
        else{
            if(GameFrame.controller.canClick(row, col))
            {
                GameFrame.controller.change(row, col);
                GameFrame.controller.swapPlayer();
            }
        }
    }

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
        if (this.chessPiece != null) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);//圆形棋子
        }
    }

    @Override//重构
    public void paintComponent(Graphics g){
        super.printComponents(g);
        drawPiece(g);
    }
}
