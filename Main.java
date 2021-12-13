import view.GameFrame;
import view.MenuFrame;
import model.ImageValue;

import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        //TODO 结束时记得删掉所有可下子位置
        ImageValue.init();
        SwingUtilities.invokeLater(() -> {
            MenuFrame menuFrame = new MenuFrame(500);
            menuFrame.setVisible(true);
            GameFrame gameFrame=new GameFrame(700,menuFrame);
            gameFrame.setVisible(false);
            menuFrame.setGameFrame(gameFrame);
        });
    }
}
