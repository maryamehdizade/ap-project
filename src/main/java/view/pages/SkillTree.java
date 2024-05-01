package view.pages;

import javax.swing.*;
import java.awt.*;

public class SkillTree extends JFrame {
    Menu menu;
    Dimension size = new Dimension(400,100);
    Color color = new Color(86, 10, 10);
    JButton ares = new JButton("Writ of Ares  " + "\n" + "750 xp");
    JButton aceso = new JButton("Writ of Aceso  " + "\n" + "500 xp");
    JButton proteus = new JButton("Writ of Proteus  " + "\n" + "1000 xp");
    JPanel panel;
    public SkillTree(Menu menu){
        this.menu = menu;

        setSize(700,700);
        setLocation(300,20);
        setBackground(Color.black);
        setVisible(true);
        requestFocus();
        setFocusable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.black);

        add();

    }
    void add(){
        ares.setSize(size);
        ares.setLocation(150,50);
        ares.setBackground(color);
        ares.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        ares.addActionListener(e ->{
            if(menu.getXp() >= 750) {
                menu.ares = true;
                menu.setXp(menu.getXp() - 750);
            }
        });

        aceso.setSize(size);
        aceso.setLocation(150,170);
        aceso.setBackground(color);
        aceso.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        aceso.addActionListener(e -> {
            if(menu.getXp() >= 500){
                menu.aceso = true;
                menu.setXp(menu.getXp() - 500);
                //todo
            }
        });

        proteus.setSize(size);
        proteus.setLocation(150,290);
        proteus.setBackground(color);
        proteus.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        proteus.addActionListener(e -> {
            if(menu.getXp() >= 1000){
                menu.proteus = true;
                menu.setXp(menu.getXp() - 1000);
                //todo
            }
        });

        JButton men = new JButton("menu");
        men.setSize(400,50);
        men.setLocation(150,500);
        men.setBackground(color);
        men.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        men.addActionListener(e -> {
            dispose();
            menu.setVisible(true);
        });
        JLabel xp = new JLabel("xp :   " + menu.getXp());
        xp.setSize(300,70);
        xp.setLocation(250, 410);
        xp.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        xp.setLayout(null);
        xp.setBackground(Color.black);

        panel.add(aceso);
        panel.add(ares);
        panel.add(proteus);
        panel.add(men);
        panel.add(xp);
        this.add(panel);
    }
}
