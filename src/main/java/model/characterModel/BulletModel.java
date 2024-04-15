package model.characterModel;

import view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.UUID;

public class BulletModel {
    private Point2D loc;
    private double dx;
    private double dy;
    private String id;
    GamePanel panel;

    public BulletModel(Point2D loc, int targetX, int targetY, GamePanel panel) {

        this.panel = panel;

        id = UUID.randomUUID().toString();
        this.loc = loc;
        double angle = Math.atan2(targetY - loc.getX(), targetX - loc.getY());
        dx = (int) (Math.cos(angle) * 5);
        dy = (int) (Math.sin(angle) * 5);

    }

    public int move() {
        loc = new Point2D.Double(loc.getX() + dx, loc.getY() + dy);

        if(loc.getX() < 0)return 1;
        else if(loc.getX() > panel.getDimension().getWidth() + panel.getLoc().getX() - 200)return 2;
        else if(loc.getY() < 0)return 3;
        else if(loc.getY() > panel.getDimension().getHeight()+ panel.getLoc().getY() - 150)return 4;
        return 0;
    }

    public JPanel getPanel() {
        return panel;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public Point2D getLoc() {
        return loc;
    }

    public String getId() {
        return id;
    }
}
