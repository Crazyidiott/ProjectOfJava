package view;
//设置上方初始状态
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel{
    private JLabel playerLabel;
    private JLabel scoreLabel;
    private JLabel modeLabel;

    public StatusPanel(int width, int height){//初始化
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(true);

        this.playerLabel = new JLabel();
        this.playerLabel.setLocation(0, 10);
        this.playerLabel.setSize((int) (width * 0.4), height);
        this.playerLabel.setFont(new Font("Calibri", Font.BOLD, 30));//字体，风格，字号

        this.scoreLabel = new JLabel();
        this.scoreLabel.setLocation((int) (width * 0.4), 10);
        this.scoreLabel.setSize((int) (width * 0.5), height);
        this.scoreLabel.setFont(new Font("Calibri", Font.ITALIC, 25));

        this.modeLabel = new JLabel();
        this.modeLabel.setLocation((int)(width*0.9),10);
        this.modeLabel.setSize((int) (width*0.1),height);
        this.modeLabel.setFont(new Font("Calibri",Font.BOLD,15));

        add(playerLabel);
        add(scoreLabel);
        add(modeLabel);
    }

    public void init(){
        this.setPlayerText(ChessPiece.BLACK.name());
        this.setScoreText(2,2);
        this.setModeText("Normal");
    }

    public void setScoreText(int black, int white){//更改分数
        this.scoreLabel.setText(String.format("BLACK: %d\tWHITE: %d", black, white));
    }
    //更改至此人下棋
    public void setPlayerText(String playerText){
        this.playerLabel.setText(playerText + "'s turn");
    }

    public void setModeText(String modeText){
        this.modeLabel.setText(modeText);
    }

    public void isCheating(){
        this.playerLabel.setText("Cheat Mode");
        this.scoreLabel.setText("Cheat Mode");
    }
}
