import model.ImageValue;
import view.GameFrame;

import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        ImageValue.init();
        SwingUtilities.invokeLater(() -> {

            GameFrame mainFrame = new GameFrame(700);
            mainFrame.setVisible(true);
        });
    }
}
