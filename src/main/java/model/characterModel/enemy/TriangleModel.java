package model.characterModel.enemy;

import model.characterModel.PlayerModel;
import model.movement.Movable;

import java.util.UUID;

public class TriangleModel implements Movable {

    private int x1, y1, x2, y2, x3, y3;
    private int speed;
    private int hp;
    private PlayerModel playerModel;
    private String id;

    public TriangleModel(int x1, int y1, int x2, int y2, int x3, int y3, int speed, PlayerModel playerModel) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.speed = speed;
        this.id = UUID.randomUUID().toString();
        this.playerModel = playerModel;
    }
    @Override
    public int move() {
        double angle = Math.atan2(playerModel.getLocation().getY() - y1, playerModel.getLocation().getX() - x1);
        int dx = (int) (Math.cos(angle) * speed);
        int dy = (int) (Math.sin(angle) * speed);

        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
        x3 += dx;
        y3 += dy;
        return 0;
    }

    @Override
    public void move(double velocity) {

    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getX3() {
        return x3;
    }

    public int getY3() {
        return y3;
    }

    public int getHp() {
        return hp;
    }

    public String getId() {
        return id;
    }
}
