package model.characterModel.enemy;

import model.characterModel.PlayerModel;
import model.movement.Movable;

import java.util.UUID;

public class TriangleModel implements Movable {

    private double x1, y1, x2, y2, x3, y3;
    private double speed;
    private int hp = 15;
    private PlayerModel playerModel;
    private String id;

    public TriangleModel(double x1, double y1, double x2, double y2, double x3, double y3, double speed, PlayerModel playerModel) {
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
        double dx = Math.cos(angle) * speed;
        double dy = Math.sin(angle) * speed;

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

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    public int getHp() {
        return hp;
    }

    public String getId() {
        return id;
    }
}
