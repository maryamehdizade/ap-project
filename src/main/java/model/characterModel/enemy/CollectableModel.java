package model.characterModel.enemy;

import java.awt.geom.Point2D;
import java.util.UUID;

public class CollectableModel {
    private Point2D loc;
    String id;

    public CollectableModel(Point2D loc) {
        this.loc = loc;
        this.id = UUID.randomUUID().toString();
    }

    public Point2D getLoc() {
        return loc;
    }

    public String getId() {
        return id;
    }
}
