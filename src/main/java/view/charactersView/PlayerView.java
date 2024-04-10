package view.charactersView;

import java.awt.geom.Point2D;

public  class PlayerView {
    private int xp = 0;
    private int lp = 0;
    private String id;
    private  Point2D location;

    public PlayerView(String id, Point2D location) {
        this.location = location;
        this.id = id;
    }

    public  Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }
}
