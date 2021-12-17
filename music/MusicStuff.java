package music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class MusicStuff {
    String musiclocation;
    long clipTimePosition;
    long shichang;
    Clip clip;
    public MusicStuff(String musiclocation){
        this.musiclocation = musiclocation;
    }
    public void playMusic(){
        try{
            File musicPath = new File(musiclocation);
            if(musicPath.exists()){
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                shichang = clip.getMicrosecondLength();
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }else{
                System.out.println("Can't find file.");
            }
        }
        catch (Exception e){e.printStackTrace();}
    }

    public void pauseTheMusic(){
        clipTimePosition = clip.getMicrosecondPosition();
        clip.stop();

    }


    public void resumeTheMusic(){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public long getClipTimePosition() {
        return clipTimePosition;
    }
}
