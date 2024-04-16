package model.characterModel.enemy;

import model.characterModel.PlayerModel;
import model.movement.Movable;
import view.GamePanel;

import java.awt.geom.Point2D;
import java.util.UUID;

public class RectangleModel extends java.awt.Rectangle implements Movable {
    private int hp = 10;
    private PlayerModel playerModel;

    private double speedx = 3;
    private double speedy = 3;
    private GamePanel panel;
    private double targetx;
    private double targety;


    private Point2D loc;
    String id;

    public RectangleModel(Point2D loc, GamePanel panel) {
        this.panel = panel;
        this.loc = loc;
        this.playerModel = panel.playerModel;
        this.id = UUID.randomUUID().toString();

        double m = Math.atan2((playerModel.getLocation().getY() - loc.getY()),(playerModel.getLocation().getX() - loc.getX()));
        speedx = (int) (Math.cos(m) * 5);
        speedy = (int) (Math.sin(m) * 5);

    }

    @Override
    public int move() {
        loc = new Point2D.Double(loc.getX() + speedx, loc.getY() + speedy);


        return 0;
    }

    @Override
    public void move(double velocity) {

    }

}
