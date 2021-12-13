package model;//棋子颜色

import java.awt.*;

public enum ChessPiece{//枚举类


    BLACK(Color.BLACK), DARK_GRAY(Color.DARK_GRAY),WHITE(Color.WHITE),LIGHT_GRAY(Color.LIGHT_GRAY);

    private final Color color;

    ChessPiece(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public ChessPiece next(){
        if(color==color.BLACK)
            return ChessPiece.WHITE;
        else if(color==Color.WHITE)
            return ChessPiece.BLACK;
        else if(color==Color.DARK_GRAY)
            return ChessPiece.LIGHT_GRAY;
        else if(color==Color.LIGHT_GRAY)
            return ChessPiece.DARK_GRAY;
        else
            return null;
    }
}
