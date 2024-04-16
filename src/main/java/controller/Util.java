package controller;

import model.characterModel.PlayerModel;

import java.awt.geom.Point2D;

import static controller.Constant.BALL_SIZE;

public class Util {
    public static Point2D addVector(Point2D a, Point2D b){
        return new Point2D.Double(a.getX() + b.getX(), a.getY() + b.getY());
    }
    public static Point2D playerCenter(PlayerModel playerModel){
        return new Point2D.Double(playerModel.getLocation().getX() + BALL_SIZE/2.0, playerModel.getLocation().getY() + BALL_SIZE/2.0);
    }
//    public static Point2D relativeLoction(){
//        return
//    }
}
