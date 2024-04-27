package model.characterModel.enemy;

import model.characterModel.PlayerModel;
import model.movement.Movable;
import view.pages.GamePanel;

import java.awt.geom.Point2D;
import java.util.Random;
import java.util.UUID;

import static controller.Constant.RECT_SIZE;

public class RectangleModel extends java.awt.Rectangle implements Movable {
    private int hp = 10;
    private PlayerModel playerModel;

    private double speed = 1;
    private GamePanel panel;
    private Random random = new Random();
    private int[] xPoints;
    private int[] yPoints;
    private Point2D loc;
    private double dx;
    private double dy;
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
            x = random.nextDouble(panel.game.getLocation().getX(), panel.getDimension().getWidth());
            if(Math.floor(Math.random()*2) == 0){
                y = panel.getDimension().getHeight() ;
            }else{
                y = panel.game.getLocation().getY();
            }
        }else {
            y = random.nextDouble(panel.game.getLocation().getY(), panel.getDimension().getHeight());
            if(Math.floor(Math.random()*2) == 0){
                x = panel.getDimension().getWidth();
            }else{
                x = panel.game.getLocation().getX();
            }

        }

        loc = new Point2D.Double(x,y);

        xPoints = new int[]{(int) loc.getX(), (int) (loc.getX() + RECT_SIZE),(int) (loc.getX() + RECT_SIZE), (int) loc.getX()};
        yPoints = new int[]{(int) loc.getY(), (int) loc.getY(), (int) (loc.getY() + RECT_SIZE), (int) (loc.getY() + RECT_SIZE)};



    }

    @Override
    public int move() {

        double m = Math.atan2((playerModel.getLocation().getY() - loc.getY()),(playerModel.getLocation().getX() - loc.getX()));
         dx = (Math.cos(m) * 2) * speed;
          dy = (Math.sin(m) * 2) * speed;


        loc = new Point2D.Double(loc.getX() + dx, loc.getY() + dy);

        xPoints = new int[]{(int) loc.getX(), (int) (loc.getX() + RECT_SIZE),(int) (loc.getX() + RECT_SIZE), (int) loc.getX()};
        yPoints = new int[]{(int) loc.getY(), (int) loc.getY(), (int) (loc.getY() + RECT_SIZE), (int) (loc.getY() + RECT_SIZE)};


        //collision
        return 0;
    }

    @Override
    public void findPlayer() {
        double m = Math.atan2((playerModel.getLocation().getY() - loc.getY()),(playerModel.getLocation().getX() - loc.getX()));
        dx = (Math.cos(m) * 2) * speed;
        dy = (Math.sin(m) * 2) * speed;
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

    public int[] getxPoints() {
        return xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }
    public void setXvelocity(double xvelocity) {
        dx = xvelocity;
    }

    @Override
    public void setYvelocity(double yvelocity) {
        dy = yvelocity;
    }

    @Override
    public double getXvelocity() {
        return dx;
    }

    @Override
    public double getYvelocity() {
        return dy;
    }
    @Override
    public void move(double velocity) {

    }

}
