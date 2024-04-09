package model.characterModel;

import java.awt.geom.Point2D;
import static controller.Controller.createPlayerView;

public class PlayerModel {
    private Point2D location = new Point2D.Double(300,300);

    public PlayerModel() {
        createPlayerView();
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Point2D getLocation() {
        return location;
    }

    private int xp = 0;
    private int lp = 0;

}
