package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Paths;

public class Sound {
    private Clip clip;

    public Sound() throws Exception{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(
                    Paths.get("").toAbsolutePath() + "\\src\\sound\\...").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

    }

    public Clip getClip() {
        return clip;
    }
}
