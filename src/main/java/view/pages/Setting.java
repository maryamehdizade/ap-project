package view.pages;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Paths;

public class Setting extends JFrame {

    private Clip clip;
    private JSlider sound;
    private JSlider sensitivity;
    private JSlider level;

    private  GamePanel panel;

    public Setting(GamePanel panel){

        setSize(700,700);
        setLocation(300,20);
        setLayout(null);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        setVisible(true);

        sound = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        sound.setLocation(300,200);
        sound.setSize(200,100);
        sound.addChangeListener(e -> adjustVolume());

        sensitivity = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        sensitivity.setLocation(300,350);
        sensitivity.setSize(200,100);


        level = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        level.setLocation(300,500);
        level.setSize(200,100);

        JLabel sound1 = new JLabel("sound");
        sound1.setLocation(100,200);
        sound1.setSize(200,100);

        JLabel sense = new JLabel("sensitivity");
        sense.setLocation(100,350);
        sense.setSize(200,100);

        JLabel level1 = new JLabel("level");
        level1.setLocation(100,500);
        level1.setSize(200,100);

        add(sense);
        add(sound1);
        add(sensitivity);
        add(sound);
        add(level1);
        add(level);

        this.panel = panel;
        this.clip = panel.sound.getClip();
    }

    private void adjustVolume() {
        if (clip != null) {
            int volume = sound.getValue();
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float gain = (float) volume / sound.getMaximum();
            float dB = gainControl.getMinimum() + gain * (gainControl.getMaximum() - gainControl.getMinimum());
            gainControl.setValue(dB);
        }
    }
}
