package model.characterModel.enemy;

import model.characterModel.PlayerModel;
import model.movement.Movable;

public class TriangleModel implements Movable {

    private int x1, y1, x2, y2, x3, y3;
    private int speed;
    private PlayerModel playerModel;

    public TriangleModel(int x1, int y1, int x2, int y2, int x3, int y3, int speed, PlayerModel playerModel) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.speed = speed;
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
}
