package view.charactersView.enemy;

import model.characterModel.PlayerModel;
import model.movement.Movable;

import java.awt.*;

public class TriangleView {
    private int x1, y1, x2, y2, x3, y3;
    private int hp;
    private String id;

    public TriangleView(String id, int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.id = id;
    }

    public void draw(Graphics g) {
        int[] xPoints = {x1, x2, x3};
        int[] yPoints = {y1, y2, y3};
        g.setColor(Color.BLUE);
        g.fillPolygon(xPoints, yPoints, 3);
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getId() {
        return id;
    }
}
