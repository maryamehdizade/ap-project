package view.charactersView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.UUID;

import static controller.Constant.BULLET_SIZE;

public class BulletView {
    private Point2D loc;
    private double dx;
    private double dy;
    private String id;

    JPanel panel;

    public BulletView(String id, Point2D loc, double dx, double dy) {

        this.id = id;
        this.loc = loc;
        this.dx = dx;
        this.dy = dy;

    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval((int)loc.getX(), (int)loc.getY(), BULLET_SIZE, BULLET_SIZE);
    }

    public void setLoc(Point2D loc) {
        this.loc = loc;
    }

    public String getId() {
        return id;
    }
}
