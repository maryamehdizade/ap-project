package controller;

import model.characterModel.PlayerModel;
import model.characterModel.enemy.RectangleModel;
import model.characterModel.enemy.TriangleModel;

import java.awt.geom.Point2D;

import static controller.Constant.BALL_SIZE;
import static controller.Constant.RECT_SIZE;

public class Util {
    //rectangle epsilon collision
    public static int doesRecIntersectEpsilon(RectangleModel rectangle, PlayerModel model){
        Point2D rec = rectCenter(rectangle);
        Point2D ep = playerCenter(model);
        double distance = Math.pow((rec.getX() - ep.getX()), 2) + Math.pow((rec.getY() - ep.getY()), 2);
        double a = BALL_SIZE/2.0 + RECT_SIZE/2.0;
        double b = BALL_SIZE/2.0 + RECT_SIZE*0.7;
        if(distance <= b && distance > a)return 1;
            else if(distance <= a)return 2;
        return 0;
    }

    //triangle epsilon collision
    public static boolean doesLineIntersectEpsilon(double x1, double y1, double x2, double y2,PlayerModel model) {
        double closestX = max(playerCenter(model).getX(), x1, x2);
        double closestY = max(playerCenter(model).getY(), y1, y2);
        double distance = Math.sqrt(Math.pow(closestX - playerCenter(model).getX(), 2) + Math.pow(closestY - playerCenter(model).getY(), 2));

        return distance <= BALL_SIZE;
    }
    public static double max(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
    public static int doesCircleIntersectTriangle(PlayerModel model, TriangleModel triangle) {
        if (isPointInsideCircle(triangle.getX1(), triangle.getY1(), model) ||
                isPointInsideCircle(triangle.getX2(), triangle.getY2(), model) ||
                isPointInsideCircle(triangle.getX3(), triangle.getY3(), model)) {

            return 1;
        } else if (doesLineIntersectEpsilon(triangle.getX1(), triangle.getY1(), triangle.getX2(), triangle.getY2(), model) ||
                doesLineIntersectEpsilon(triangle.getX2(), triangle.getY2(), triangle.getX3(), triangle.getY3(), model) ||
                doesLineIntersectEpsilon(triangle.getX3(), triangle.getY3(), triangle.getX1(), triangle.getY1(), model)) {

            return 2;
        }
        return 0;
    }
    public static boolean isPointInsideCircle(double x, double y,PlayerModel model) {
        double dx = x - playerCenter(model).getX();
        double dy = y - playerCenter(model).getY();
        return dx * dx + dy * dy <= BALL_SIZE * BALL_SIZE;
    }
    //triangle rec collision
    public static boolean isCollision(TriangleModel triangle, RectangleModel rectangle) {

        // Get the coordinates of the triangle vertices
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
        // Find the orientations of the points
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
        if (val == 0) return 0;  // Collinear
        return (val > 0) ? 1 : 2; // Clockwise or counterclockwise
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
    public static Point2D triangleCenter(TriangleModel t) {
        return new Point2D.Double((t.getX1() + t.getX2() + t.getX3())/3, (t.getY1() + t.getY2() + t.getY3())/3);
    }
}
