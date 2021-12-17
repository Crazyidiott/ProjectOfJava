package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageValue {
    public static BufferedImage blackImage = null;
    public static BufferedImage whiteImage = null;
    public static BufferedImage playButton1 = null;
    public static ImageIcon playButtonup = null;
    public static ImageIcon playButtondown = null;

    public static ImageIcon restartButtonup = null;
    public static ImageIcon restartButtondown = null;
    public static ImageIcon saveButtonup = null;
    public static ImageIcon saveButtondown = null;
    public static ImageIcon loadButtonup = null;
    public static ImageIcon loadButtondown = null;
    public static ImageIcon undoButtonup = null;
    public static ImageIcon undoButtondown = null;
    public static ImageIcon normalButtonup = null;
    public static ImageIcon normalButtondown = null;
    public static ImageIcon cheatButtonup = null;
    public static ImageIcon cheatButtondown = null;
    public static ImageIcon backButtonup = null;
    public static ImageIcon backButtondown = null;
    public static ImageIcon musicplaybutton = null;
    public static ImageIcon musicstopbutton = null;



    private static String Path = "/ImageChess/";

    public static void init() {
        try {
            whiteImage = ImageIO.read(ImageValue.class.getResource(Path + "whiteee.png"));
            blackImage = ImageIO.read(ImageValue.class.getResource(Path + "blackkk.png"));
            playButton1 = ImageIO.read(ImageValue.class.getResource(Path + "savebuttondown.png"));
            playButtonup = new ImageIcon("src/" + Path + "playbuttonup.png");
            playButtondown = new ImageIcon("src/" + Path + "playbuttondown.png");

            restartButtonup = new ImageIcon("src/" + Path + "restartbuttonup.png");
            restartButtondown = new ImageIcon("src/" + Path + "restartbuttondown.png");
            saveButtonup = new ImageIcon("src/" + Path + "savebuttonup.png");
            saveButtondown = new ImageIcon("src/" + Path + "savebuttondown.png");
            loadButtonup = new ImageIcon("src/" + Path + "loadbuttonup.png");
            loadButtondown = new ImageIcon("src/" + Path + "loadbuttondown.png");
            undoButtonup = new ImageIcon("src/" + Path + "undobuttonup.png");
            undoButtondown = new ImageIcon("src/" + Path + "undobuttondown.png");
            normalButtonup = new ImageIcon("src/" + Path + "normalbuttonup.png");
            normalButtondown = new ImageIcon("src/" + Path + "normalbuttondown.png");
            cheatButtonup = new ImageIcon("src/" + Path + "cheatbuttonup.png");
            cheatButtondown = new ImageIcon("src/" + Path + "cheatbuttondown.png");

            backButtonup = new ImageIcon("src/" + Path + "backbuttonup.png");
            backButtondown = new ImageIcon("src/" + Path + "backbuttondown.png");
            musicplaybutton = new ImageIcon("src/" + Path + "musicunplay.png");
            musicstopbutton = new ImageIcon("src/" + Path + "musiconplay.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
