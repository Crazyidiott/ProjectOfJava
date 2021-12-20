package view;
//设置棋盘
import components.ChessGridComponent;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class ChessBoardPanel extends JPanel{//继承
    private final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;
    private int black;
    private int white;

    public ChessBoardPanel(int width, int height){//初始化
        this.setVisible(true);
        this.setFocusable(true);//可以被操作，指点击？
        this.setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n",
                width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);

        initialChessGrids();//return empty chessboard

        repaint();//输出
    }

    public void initialChessGrids(){
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];

        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
    }

    public void initialGame(){
        for(int i=0;i<CHESS_COUNT;i++)
            for(int j=0;j<CHESS_COUNT;j++)
                chessGrids[i][j].setChessPiece(null);
        chessGrids[3][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[3][4].setChessPiece(ChessPiece.BLACK);
        chessGrids[4][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[4][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[2][3].setChessPiece(ChessPiece.DARK_GRAY);
        chessGrids[3][2].setChessPiece(ChessPiece.DARK_GRAY);
        chessGrids[4][5].setChessPiece(ChessPiece.DARK_GRAY);
        chessGrids[5][4].setChessPiece(ChessPiece.DARK_GRAY);
        black=2;
        white=2;
    }

    @Override
    protected void paintComponent(Graphics g){//重构画棋
        super. paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public int getblack(){
        return black;
    }

    public int getwhite(){
        return white;
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer){//可以下棋
        if(chessGrids[row][col].getChessPiece()!=null)
            return false;
        int k;
        boolean flag=false;
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++) {
                if(i==0&&j==0)
                    continue;
                if(row+i<0||row+i>7||col+j<0||col+j>7)
                    continue;
                if(chessGrids[row+i][col+j].getChessPiece()==null)
                    continue;
                if(chessGrids[row+i][col+j].getChessPiece()==currentPlayer)
                    continue;
                k=2;
                while(row+k*i>=0&&row+k*i<=7&&col+k*j>=0&&col+k*j<=7) {
                    if(chessGrids[row+k*i][col+k*j].getChessPiece()==null)
                        break;
                    if(chessGrids[row+k*i][col+k*j].getChessPiece()==currentPlayer) {
                        flag=true;
                        break;
                    }
                    k++;
                }
            }
        return flag;
    }

    public void change(int row,int col,ChessPiece currentPlayer){
        System.out.println(1);
        int k;
        boolean flag;
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++)
            {
                if(i == 0 && j == 0)
                    continue;
                if(row + i < 0 || row + i > 7 || col + j < 0 || col + j > 7)
                    continue;
                if(chessGrids[row + i][col + j].getChessPiece() == null)
                    continue;
                if(chessGrids[row + i][col + j].getChessPiece() == currentPlayer)
                    continue;
                k = 2;
                flag = false;
                while(row + k * i >= 0 && row + k * i <= 7 && col + k * j >= 0 && col + k * j <= 7)
                {
                    if(chessGrids[row + k * i][col + k * j].getChessPiece() == null)
                        break;
                    if(chessGrids[row + k * i][col + k * j].getChessPiece() == currentPlayer)
                    {
                        flag = true;
                        break;
                    }
                    k++;
                }
                if(flag)
                {
                    black += currentPlayer == ChessPiece.BLACK ? k - 1 : 1 - k;
                    white += currentPlayer == ChessPiece.WHITE ? k - 1 : 1 - k;
                }
                while(flag&&(--k)>0){
                    chessGrids[row+k*i][col+k*j].setChessPiece(currentPlayer);
                    chessGrids[row+k*i][col+k*j].repaint();
                }
            }
        chessGrids[row][col].setChessPiece(currentPlayer);
        chessGrids[row][col].repaint();

        if(currentPlayer==ChessPiece.BLACK)
            black++;
        if(currentPlayer==ChessPiece.WHITE)
            white++;
    }

    public boolean haveSpace(ChessPiece currentPlayer,ChessPiece Need){
        boolean flag=false;
        for(int i=0;i<CHESS_COUNT;i++)
            for(int j=0;j<CHESS_COUNT;j++)
            {
                chessGrids[i][j].setChessPiece(chessGrids[i][j].getChessPiece());
                if(canClickGrid(i, j, currentPlayer))
                {
                    flag=true;
                    if(Need!=null)
                        chessGrids[i][j].setChessPiece(Need);
                }
            }
        return flag;
    }

    public void cheatOn(int row,int col,ChessPiece currentPlayer){
        if(canClickGrid(row,col,currentPlayer))
            change(row,col,currentPlayer);
        else
        {
            System.out.println(2);
            chessGrids[row][col].setChessPiece(currentPlayer);
            if(currentPlayer==ChessPiece.WHITE)
                white++;
            else
                black++;
            chessGrids[row][col].repaint();
        }
    }

/*    public void cheatOnfalse(int row,int col){
        if(chessGrids[row][col].getChessPiece()==ChessPiece.BLACK){
            chessGrids[row][col].setChessPiece(ChessPiece.WHITE);
            black--;
            white++;
        }
        else if(chessGrids[row][col].getChessPiece()==ChessPiece.WHITE){
            chessGrids[row][col].setChessPiece(null);
            white--;
        }
        else{
            chessGrids[row][col].setChessPiece(ChessPiece.BLACK);
            black++;
        }
        chessGrids[row][col].repaint();
    }*/

    //TODO 这个是干嘛的？
    public void clear(){
        for(int i=0;i<CHESS_COUNT;i++)
            for(int j=0;j<CHESS_COUNT;j++)
                chessGrids[i][j].setChessPiece(chessGrids[i][j].getChessPiece());
        repaint();
    }

    @Override
    //以1，-1, 0 表示棋盘
    public String toString(){
        String ret="";
        for(int i = 0; i < CHESS_COUNT; i++)
        {
            for(int j = 0; j < CHESS_COUNT; j++)
            {
                if(chessGrids[i][j].getChessPiece()==ChessPiece.BLACK)
                    ret=ret+"  1";
                else if(chessGrids[i][j].getChessPiece()==ChessPiece.WHITE)
                    ret=ret+" -1";
                else
                    ret=ret+"  0";
            }
            ret=ret+"\n";
        }
        return ret;
    }

    public int[][] toInt(){
        int[][] ret=new int[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(chessGrids[i][j].getChessPiece()==ChessPiece.BLACK)
                    ret[i][j]=1;
                else if(chessGrids[i][j].getChessPiece()==ChessPiece.WHITE)
                    ret[i][j]=-1;
                else
                    ret[i][j]=0;
            }
        return ret;
    }
}
