import view.GameFrame;
import view.MenuFrame;
import model.ImageValue;

import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        ImageValue.init();
        SwingUtilities.invokeLater(() -> {
            MenuFrame menuFrame = new MenuFrame(800);
            menuFrame.setVisible(true);
            GameFrame gameFrame=new GameFrame(800,menuFrame);
            gameFrame.setVisible(false);
            menuFrame.setGameFrame(gameFrame);
        });
    }
}
