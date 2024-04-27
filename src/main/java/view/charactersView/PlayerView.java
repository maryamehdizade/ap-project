package view.charactersView;

import java.awt.*;
import java.awt.geom.Point2D;

import static controller.Constant.BALL_SIZE;

public  class PlayerView {
    private int xp ;
    private int hp ;
    private final String id;
    public double size;
    private  Point2D location;

    public PlayerView(String id, Point2D location) {
        this.location = location;
        this.id = id;
    }
    public void draw(Graphics g){
        g.setColor(Color.gray);
        g.drawOval((int) location.getX(), (int) location.getY(), (int) size, (int) size);
    }

    public  Point2D getLocation() {
        return new Point2D.Double(location.getX() + BALL_SIZE/2.0,location.getY() + BALL_SIZE/2.0);
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
