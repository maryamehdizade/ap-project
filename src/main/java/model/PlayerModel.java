package model;

import java.awt.geom.Point2D;

public class PlayerModel {
    private Point2D location = new Point2D.Double(300,300);

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Point2D getLocation() {
        return location;
    }

    private int xp = 0;
    private int lp = 0;

}
