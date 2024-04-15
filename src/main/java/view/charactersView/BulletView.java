package view.charactersView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.UUID;

public class BulletView {
    private Point2D loc;
    private final int diameter = 5;
    private double dx;
    private double dy;
    private String id;

    JPanel panel;

    public BulletView(String id, Point2D loc, double dx, double dy, JPanel panel) {

        this.id = id;
        this.loc = loc;
        this.dx = dx;
        this.dy = dy;

        this.panel = panel;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval((int)loc.getX(), (int)loc.getY(), diameter, diameter);
    }

    public void setLoc(Point2D loc) {
        this.loc = loc;
    }

    public String getId() {
        return id;
    }
}
