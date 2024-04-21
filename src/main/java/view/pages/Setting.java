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

    private JSlider sound;
    private JSlider sensitivity;
    private JSlider level;


    public Setting(){

        setSize(700,700);
        setLocation(300,20);
        setLayout(null);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        setVisible(true);

        sound = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        sound.setLocation(300,100);
        sound.setSize(200,100);
        sound.addChangeListener(e -> adjustVolume());

        sensitivity = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        sensitivity.setLocation(300,250);
        sensitivity.setSize(200,100);


        level = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        level.setLocation(300,400);
        level.setSize(200,100);

        JLabel sound1 = new JLabel("sound");
        sound1.setLocation(100,100);
        sound1.setSize(200,100);

        JLabel sense = new JLabel("sensitivity");
        sense.setLocation(100,250);
        sense.setSize(200,100);

        JLabel level1 = new JLabel("level");
        level1.setLocation(100,400);
        level1.setSize(200,100);

        JButton menu = new JButton("menu");
        menu.setSize(400,50);
        menu.setLocation(100,500);
        menu.addActionListener(e -> {
            try {
                new Menu();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        add(menu);
        add(sense);
        add(sound1);
        add(sensitivity);
        add(sound);
        add(level1);
        add(level);

    }

    private void adjustVolume() {
//        if (clip != null) {
//            int volume = sound.getValue();
//            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//            float gain = (float) volume / sound.getMaximum();
//            float dB = gainControl.getMinimum() + gain * (gainControl.getMaximum() - gainControl.getMinimum());
//            gainControl.setValue(dB);
//        }

    }
}
