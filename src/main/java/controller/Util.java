package controller;

import model.characterModel.PlayerModel;
import model.characterModel.enemy.RectangleModel;
import model.characterModel.enemy.TriangleModel;

import java.awt.geom.Point2D;

import static controller.Constant.BALL_SIZE;
import static controller.Constant.RECT_SIZE;

public class Util {
    public static Point2D addVector(Point2D a, Point2D b) {
        return new Point2D.Double(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Point2D playerCenter(PlayerModel playerModel) {
        return new Point2D.Double(playerModel.getLocation().getX() + BALL_SIZE / 2.0, playerModel.getLocation().getY() + BALL_SIZE / 2.0);
    }

    public static Point2D rectCenter(RectangleModel rectangleModel) {
        return new Point2D.Double(rectangleModel.getLoc().getX() + RECT_SIZE / 2.0, rectangleModel.getLoc().getY() + RECT_SIZE / 2.0);
    }
    public static Point2D triangleCenter(TriangleModel t) {
        return new Point2D.Double((t.getX1() + t.getX2() + t.getX3())/3, (t.getY1() + t.getY2() + t.getY3())/3);
    }
}
