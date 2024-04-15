package model.characterModel;

import view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.UUID;

public class BulletModel {
    private Point2D loc;
    private final int diameter = 5;
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

    public boolean move() {
        loc = new Point2D.Double(loc.getX() + dx, loc.getY() + dy);

        return loc.getX() < 0 || loc.getX() > panel.getSize().getWidth() + panel.getLoc().getX()
                || loc.getY() < 0 || loc.getY() > panel.getSize().getHeight()+ panel.getLoc().getY();
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
