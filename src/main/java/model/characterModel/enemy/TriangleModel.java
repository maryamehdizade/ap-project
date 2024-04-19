package model.characterModel.enemy;

import model.characterModel.PlayerModel;
import model.movement.Movable;
import view.GamePanel;

import java.util.Random;
import java.util.UUID;

import static controller.Constant.TRI_SIZE;

public class TriangleModel implements Movable {

    private double x1, y1, x2, y2, x3, y3;
    private double speed = 1;
    private int hp = 15;
    private PlayerModel playerModel;
    private String id;
    private Random random = new Random();
    private GamePanel panel;

    public TriangleModel(GamePanel panel) {
        this.panel = panel;
        this.playerModel = panel.playerModel;
        this.id = UUID.randomUUID().toString();

        createTriangle();
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
    void createTriangle(){
        double x;
        double y;
        double r = Math.floor(Math.random()*2);
        if(r == 0) {
            x = random.nextDouble(panel.getLoc().getX(), panel.getDimension().getWidth());
            if(Math.floor(Math.random()*2) == 0){
                y = panel.getDimension().getHeight();
            }else{
                y = panel.getLoc().getY();
            }
        }else {
            y = random.nextDouble(panel.getLoc().getY(), panel.getDimension().getHeight());
            if(Math.floor(Math.random()*2) == 0){
                x = panel.getDimension().getWidth();
            }else{
                x = panel.getLoc().getX();
            }

        }
        this.x1 = x;
        this.y1 = y;
        this.x2 = x + TRI_SIZE;
        this.y2 = y;
        this.x3 = x + TRI_SIZE/2;
        this.y3 = y + TRI_SIZE;

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

    public void setHp(int hp) {
        this.hp = hp;
    }
}
