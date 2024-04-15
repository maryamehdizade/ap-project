package model.characterModel.enemy;

import model.movement.Movable;
import view.GamePanel;

import java.awt.geom.Point2D;

public class RectangleModel extends java.awt.Rectangle implements Movable {
    private int hp = 10;

    private int speedx = 3;
    private int speedy = 3;
    private GamePanel panel;
    private Point2D loc ;

    public RectangleModel(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void move(double velocity) {

    }
}
