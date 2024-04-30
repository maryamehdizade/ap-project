package view.pages;

import javax.swing.*;
import java.awt.*;

public class SkillTree extends JFrame {
    Menu menu;
    Dimension size = new Dimension(200,200);
    Color color = new Color(86, 10, 10);
    JButton ares = new JButton("Writ of Ares" + "\n" + "750 xp");
    JButton aceso = new JButton("Writ of Aceso" + "\n" + "500 xp");
    JButton proteus = new JButton("Writ of Proteus" + "\n" + "1000 xp");
    public SkillTree(Menu menu){
        this.menu = menu;

        setSize(700,700);
        setLocation(300,20);
        setLayout(null);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    void add(){
        ares.setSize(size);
        ares.setLocation(50,100);
        ares.setBackground(color);
        ares.addActionListener(e ->{
            if(menu.getXp() >= 750) menu.ares = true;
        });

        aceso.setSize(size);
        aceso.setLocation(250,100);
        aceso.setBackground(color);
        aceso.addActionListener(e -> {
            if(menu.getXp() >= 500)menu.aceso = true;
        });

        proteus.setSize(size);
        proteus.setLocation(250,100);
        proteus.setBackground(color);
        proteus.addActionListener(e -> {
            if(menu.getXp() >= 1000)menu.proteus = true;
        });
    }
}
