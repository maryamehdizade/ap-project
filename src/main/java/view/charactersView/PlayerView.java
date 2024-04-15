package view.charactersView;

import java.awt.geom.Point2D;

public  class PlayerView {
    private int xp ;
    private int hp ;
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

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getId() {
        return id;
    }
}
