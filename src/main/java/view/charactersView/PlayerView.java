package view.charactersView;

import java.awt.geom.Point2D;

public  class PlayerView {

    private String id;

    public PlayerView(String id) {
        this.id = id;
    }

    private static Point2D location = new Point2D.Double(300,300);

    public static Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }
}
