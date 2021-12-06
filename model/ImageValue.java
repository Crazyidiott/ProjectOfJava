package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageValue {
    public static BufferedImage blackImage = null;
    public static BufferedImage whiteImage = null;
    private static  String Path = "/ImageChess/";
    public static void init(){
        try{
            whiteImage = ImageIO.read(ImageValue.class.getResource(Path+"whiteee.png"));
            blackImage = ImageIO.read(ImageValue.class.getResource(Path+"blackkk.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
