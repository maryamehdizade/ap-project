package controller;

import model.characterModel.BulletModel;
import model.characterModel.PlayerModel;
import model.characterModel.enemy.RectangleModel;
import model.characterModel.enemy.TriangleModel;

import java.awt.geom.Point2D;

import static controller.Constant.*;

public class Util {
    //triangle triangle collision
    private static boolean isPointInsideTriangle(double x, double y, TriangleModel triangle) {

        double alpha = ((triangle.getY2() - triangle.getY3()) * (x - triangle.getX3()) + (triangle.getX3() - triangle.getX2()) * (y - triangle.getY3())) /
                ((triangle.getY2() - triangle.getY3()) * (triangle.getX1() - triangle.getX3()) + (triangle.getX3() - triangle.getX2()) * (triangle.getY1() - triangle.getY3()));
        double beta = ((triangle.getY3() - triangle.getY1()) * (x - triangle.getX3()) + (triangle.getX1() - triangle.getX3()) * (y - triangle.getY3())) /
                ((triangle.getY2() - triangle.getY3()) * (triangle.getX1() - triangle.getX3()) + (triangle.getX3() - triangle.getX2()) * (triangle.getY1() - triangle.getY3()));
        double gamma = 1.0 - alpha - beta;


        return alpha >= 0 && beta >= 0 && gamma >= 0 && beta <= 1 && alpha <= 1;
    }

    public static boolean doTrianglesIntersect(TriangleModel triangle1, TriangleModel triangle2) {
        if (isPointInsideTriangle(triangle1.getX1(), triangle1.getY1(), triangle2) ||
                isPointInsideTriangle(triangle1.getX2(), triangle1.getY2(), triangle2) ||
                isPointInsideTriangle(triangle1.getX3(), triangle1.getY3(), triangle2)) {
            return true;
        }


        if (isPointInsideTriangle(triangle2.getX1(), triangle2.getY1(), triangle1) ||
                isPointInsideTriangle(triangle2.getX2(), triangle2.getY2(), triangle1) ||
                isPointInsideTriangle(triangle2.getX3(), triangle2.getY3(), triangle1)) {
            return true;
        }

        return false;

    }
    //rectangle epsilon collision
    public static int doesRecIntersectEpsilon(RectangleModel rectangle,Point2D ep, int size){
        Point2D rec = rectCenter(rectangle);
        double distance = Math.pow((rec.getX() - ep.getX()), 2) + Math.pow((rec.getY() - ep.getY()), 2);
        double a = size/2.0 + RECT_SIZE/2.0;
        double b = size/2.0 + RECT_SIZE*0.7;
        if(distance <= b && distance > a)return 1;
            else if(distance <= a)return 2;
        return 0;
    }

    //triangle epsilon collision
    public static boolean doesLineIntersectEpsilon(double x1, double y1, double x2, double y2, double x3, double y3) {
        double closestX = max(x3, x1, x2);
        double closestY = max(y3, y1, y2);
        double distance = Math.sqrt(Math.pow(closestX - x3, 2) + Math.pow(closestY - y3, 2));

        return distance <= BALL_SIZE;
    }
    public static double max(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
    public static int doesCircleIntersectTriangle(double x, double y, TriangleModel triangle) {
        if (isPointInsideCircle(triangle.getX1(), triangle.getY1(), x, y) ||
                isPointInsideCircle(triangle.getX2(), triangle.getY2(), x, y) ||
                isPointInsideCircle(triangle.getX3(), triangle.getY3(), x, y)) {

            return 1;
        } else if (doesLineIntersectEpsilon(triangle.getX1(), triangle.getY1(), triangle.getX2(), triangle.getY2(), x, y) ||
                doesLineIntersectEpsilon(triangle.getX2(), triangle.getY2(), triangle.getX3(), triangle.getY3(), x, y) ||
                doesLineIntersectEpsilon(triangle.getX3(), triangle.getY3(), triangle.getX1(), triangle.getY1(), x, y)) {

            return 2;
        }
        return 0;
    }
    public static boolean isPointInsideCircle(double x, double y,double x1, double y1) {
        double dx = x - x1;
        double dy = y - y1;
        return dx * dx + dy * dy <= BALL_SIZE * BALL_SIZE;
    }
    //triangle rec collision
    public static boolean isCollision(TriangleModel triangle, RectangleModel rectangle) {

        double[] xPoints = {triangle.getX1(), triangle.getX2(), triangle.getX3()};
        double[] yPoints = {triangle.getY1(), triangle.getY2(), triangle.getY3()};

        for (int i = 0; i < 3; i++) {
            double x1 = xPoints[i];
            double y1 = yPoints[i];
            double x2 = xPoints[(i + 1) % 3];
            double y2 = yPoints[(i + 1) % 3];

            for (int j = 0; j < 4; j++) {
                int x3 = rectangle.getxPoints()[j];
                int y3 = rectangle.getyPoints()[j];
                int x4 = rectangle.getxPoints()[(j + 1) % 4];
                int y4 = rectangle.getyPoints()[(j + 1) % 4];


                if (doIntersect(x1, y1, x2, y2, x3, y3, x4, y4))
                    return true;
            }
        }
        return false;
    }
    public static boolean doIntersect(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {

        int o1 = orientation(x1, y1, x2, y2, x3, y3);
        int o2 = orientation(x1, y1, x2, y2, x4, y4);
        int o3 = orientation(x3, y3, x4, y4, x1, y1);
        int o4 = orientation(x3, y3, x4, y4, x2, y2);


        if (o1 != o2 && o3 != o4)
            return true;

        if (o1 == 0 && onSegment(x1, y1, x2, y2, x3, y3))
            return true;
        if (o2 == 0 && onSegment(x1, y1, x2, y2, x4, y4))
            return true;
        if (o3 == 0 && onSegment(x3, y3, x4, y4, x1, y1))
            return true;
        if (o4 == 0 && onSegment(x3, y3, x4, y4, x2, y2))
            return true;

        return false;
    }

    public static int orientation(double x1, double y1, double x2, double y2, double x3, double y3) {
        double val = (y2 - y1) * (x3 - x2) - (x2 - x1) * (y3 - y2);
        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    public static boolean onSegment(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (x2 <= Math.max(x1, x3) && x2 >= Math.min(x1, x3) &&
                y2 <= Math.max(y1, y3) && y2 >= Math.min(y1, y3))
            return true;

        return false;
    }

    // extra
    public static Point2D addVector(Point2D a, Point2D b) {
        return new Point2D.Double(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Point2D playerCenter(PlayerModel playerModel) {
        return new Point2D.Double(playerModel.getLocation().getX() + BALL_SIZE / 2.0, playerModel.getLocation().getY() + BALL_SIZE / 2.0);
    }

    public static Point2D rectCenter(RectangleModel rectangleModel) {
        return new Point2D.Double(rectangleModel.getLoc().getX() + RECT_SIZE / 2.0, rectangleModel.getLoc().getY() + RECT_SIZE / 2.0);
    }
    public static Point2D bulletCenter(BulletModel t) {
        return new Point2D.Double(t.getLoc().getX() + BULLET_SIZE/2.0, t.getLoc().getY() + BULLET_SIZE/2.0);
    }
}
