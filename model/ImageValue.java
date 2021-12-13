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
    private static  String Path = "/ImageChess/";
    public static void init(){
        try{
            whiteImage = ImageIO.read(ImageValue.class.getResource(Path+"whiteee.png"));
            blackImage = ImageIO.read(ImageValue.class.getResource(Path+"blackkk.png"));
            playButton1 = ImageIO.read(ImageValue.class.getResource(Path+"playbutton.png"));
            playButtonup = new ImageIcon("src/"+Path+"playbuttonup.png");
            playButtondown = new ImageIcon("src/"+Path+"playbuttondown.png");


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
