package supers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    private String path;
    private Clip clip = null;
    private FloatControl volumeC = null;
    private double wt;

    public Audio(String path, double wt){
        this.path = path;
        this.wt = wt;
    }

    public void sound(){
        File f = new File(this.path);

        AudioInputStream tr = null;
        try {
            tr = AudioSystem.getAudioInputStream(f);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
            clip.open(tr);

            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVolume() {
        if (wt < 0) wt = 0;
        if (wt > 1) wt = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max - min)*(float)wt+min);
    }

}
