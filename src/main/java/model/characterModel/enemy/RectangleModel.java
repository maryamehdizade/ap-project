package model.characterModel.enemy;

import model.characterModel.PlayerModel;
import model.movement.Movable;
import view.GamePanel;

import java.awt.geom.Point2D;
import java.util.Random;
import java.util.UUID;

import static controller.Constant.RECT_SIZE;

public class RectangleModel extends java.awt.Rectangle implements Movable {
    private int hp = 10;
    private PlayerModel playerModel;

    private double speedx = 3;
    private double speedy = 3;
    private GamePanel panel;
    private double targetx;
    private double targety;
    private Random random = new Random();
    private int[] xPoints;
    private int[] yPoints;


    private Point2D loc;
    String id;

    public RectangleModel(GamePanel panel) {
        this.panel = panel;

        createRecs();

        this.playerModel = panel.playerModel;
        this.id = UUID.randomUUID().toString();
    }
    public void createRecs(){
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

        loc = new Point2D.Double(x,y);

        xPoints = new int[]{(int) loc.getX(), (int) (loc.getX() + RECT_SIZE),(int) (loc.getX() + RECT_SIZE), (int) loc.getX()};
        yPoints = new int[]{(int) loc.getY(), (int) loc.getY(), (int) (loc.getY() + RECT_SIZE), (int) (loc.getY() + RECT_SIZE)};



    }

    @Override
    public int move() {

        double m = Math.atan2((playerModel.getLocation().getY() - loc.getY()),(playerModel.getLocation().getX() - loc.getX()));
        speedx = (int) (Math.cos(m) * 2);
        speedy = (int) (Math.sin(m) * 2);


        loc = new Point2D.Double(loc.getX() + speedx, loc.getY() + speedy);

        xPoints = new int[]{(int) loc.getX(), (int) (loc.getX() + RECT_SIZE),(int) (loc.getX() + RECT_SIZE), (int) loc.getX()};
        yPoints = new int[]{(int) loc.getY(), (int) loc.getY(), (int) (loc.getY() + RECT_SIZE), (int) (loc.getY() + RECT_SIZE)};


        //collision
        return 0;
    }

    @Override
    public void move(double velocity) {

    }

    public Point2D getLoc() {
        return loc;
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

    public double getSpeedx() {
        return speedx;
    }

    public void setSpeedx(double speedx) {
        this.speedx = speedx;
    }

    public double getSpeedy() {
        return speedy;
    }

    public void setSpeedy(double speedy) {
        this.speedy = speedy;
    }

    public int[] getxPoints() {
        return xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }
}
