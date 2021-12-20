package controller;

public class MachineMode
{
    private static final int[][] boardVal={
            {100,-50,7,5,5,7,-50,100},
            {-50,-50,6,4,4,6,-50,-50},
            {7,6,5,3,3,5,6,7},
            {5,4,3,2,2,3,4,5},
            {5,4,3,2,2,3,4,5},
            {7,6,5,3,3,5,6,7},
            {-50,-50,6,4,4,6,-50,-50},
            {100,-50,7,5,5,7,-50,100}};
    //black:1 white:-1
    private static int toSearch;

    public static void setToSearch(int toSearch){
        MachineMode.toSearch = toSearch;
    }

    private static boolean outOfBoard(int row, int col){
        if(row<0||7<row||col<0||7<col)
            return true;
        return false;
    }
    private static double val(int[][] chessBoard){
        double chessBoardVal=0;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                chessBoardVal+=chessBoard[i][j]*boardVal[i][j];
        int k,blackSpace=0,whiteSpace=0;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(chessBoard[i][j]!=0)
                    continue;
                for(int ii = -1; ii <= 1; ii++)
                    for(int jj = -1; jj <= 1; jj++)
                    {
                        if(ii == 0 && jj == 0)
                            continue;
                        if(outOfBoard(i + ii, j + jj))
                            continue;
                        if(chessBoard[i + ii][j + jj] != -1)
                            continue;
                        k = 2;
                        while(!outOfBoard(i + k * ii, j + k * jj))
                        {
                            if(chessBoard[i+k*ii][j+k*jj]==1)
                            {
                                blackSpace++;
                                break;
                            }
                            if(chessBoard[i+k*ii][j+k*jj]==0)
                                break;
                            k++;
                        }
                    }
            }
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(chessBoard[i][j]!=0)
                    continue;
                for(int ii = -1; ii <= 1; ii++)
                    for(int jj = -1; jj <= 1; jj++)
                    {
                        if(ii == 0 && jj == 0)
                            continue;
                        if(outOfBoard(i + ii, j + jj))
                            continue;
                        if(chessBoard[i + ii][j + jj] != 1)
                            continue;
                        k = 2;
                        while(!outOfBoard(i + k * ii, j + k * jj))
                        {
                            if(chessBoard[i+k*ii][j+k*jj]==-1)
                            {
                                whiteSpace++;
                                break;
                            }
                            if(chessBoard[i+k*ii][j+k*jj]==0)
                                break;
                            k++;
                        }
                    }
            }
        return (double)(blackSpace/(whiteSpace+0.001))*70+chessBoardVal;
    }
    private static double tryOn(int[][] chessBoard,int row,int col,int currentPlayer,int steps){
        if(steps==0)
        {
            return val(chessBoard);
        }
        int k;
        double ans=-10000*currentPlayer;
        boolean flag=false;
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++)
            {
                if(i==0&&j==0)
                    continue;
                if(outOfBoard(row+i,col+j))
                    continue;
                if((currentPlayer+chessBoard[row+i][col+j])!=0)
                    continue;
                k=2;
                while(!outOfBoard(row+k*i,col+k*j))
                {
                    if(currentPlayer==chessBoard[row+k*i][col+k*j])
                    {
                        for(int t=1;t<k;t++)
                            chessBoard[row+t*i][col+t*j]=currentPlayer;
                        flag=true;
                        break;
                    }
                    if(chessBoard[row+k*i][col+k*j]==0)
                        break;
                    k++;
                }
            }
        if(!flag)
            return ans;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(chessBoard[i][j]==0)
                {
                    if(currentPlayer==1)
                        ans=Math.max(ans,tryOn(chessBoard,i,j,-currentPlayer,steps-1));
                    if(currentPlayer==-1)
                        ans=Math.min(ans,tryOn(chessBoard,i,j,-currentPlayer,steps-1));
                }
        return ans;
    }
    public static int workOn(int[][] chessBoard,int currentPlayer){
        int row=0,col=0;
        double an=-10000*currentPlayer;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(chessBoard[i][j]==0)
                {
                    double ret=tryOn(chessBoard,i,j,currentPlayer,toSearch);
                    if(currentPlayer==1&&ret>=an)
                    {
                        an=ret;
                        row=i;
                        col=j;
                    }
                    if(currentPlayer==-1&&ret<=an)
                    {
                        an=ret;
                        row=i;
                        col=j;
                    }
                }
        return row*10+col;
        /*int k;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(chessBoard[i][j]!=0)
                    continue;
                System.out.println(i+","+j);
                for(int ii = -1; ii <= 1; ii++)
                    for(int jj = -1; jj <= 1; jj++)
                    {
                        if(ii == 0 && jj == 0)
                            continue;
                        if(outOfBoard(i + ii, j + jj))
                            continue;
                        if(chessBoard[i + ii][j + jj]+currentPlayer != 0)
                            continue;
                        k = 2;
                        while(!outOfBoard(i + k * ii, j + k * jj))
                        {
                            if(chessBoard[i+k*ii][j+k*jj]==currentPlayer)
                            {
                                System.out.println("ret"+i+j);
                                return i*10+j;
                            }
                            if(chessBoard[i+k*ii][j+k*jj]==0)
                                break;
                            k++;
                        }
                    }
            }
        System.out.println("gaga");
        return 0;*/
    }
}
