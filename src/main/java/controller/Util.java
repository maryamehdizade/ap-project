package controller;

import model.characterModel.PlayerModel;
import model.characterModel.enemy.RectangleModel;
import model.characterModel.enemy.TriangleModel;

import java.awt.geom.Point2D;

import static controller.Constant.BALL_SIZE;
import static controller.Constant.RECT_SIZE;

public class Util {
    private boolean doesLineIntersectCircle(int x1, int y1, int x2, int y2, int circleX, int circleY, int radius) {
        double distance = Math.abs((x1 - x2) * (circleY - y1) + (y2 - y1) * (circleX - x1)) / Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

        return distance <= radius;
    }
    public static boolean doIntersect(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        // Find the orientations of the points
        int o1 = orientation(x1, y1, x2, y2, x3, y3);
        int o2 = orientation(x1, y1, x2, y2, x4, y4);
        int o3 = orientation(x3, y3, x4, y4, x1, y1);
        int o4 = orientation(x3, y3, x4, y4, x2, y2);

        // Check if the orientations are different
        if (o1 != o2 && o3 != o4)
            return true;

        // Check for special cases when the points are collinear
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

    // Method to find the orientation of three points (p, q, r)
    public static int orientation(double x1, double y1, double x2, double y2, double x3, double y3) {
        double val = (y2 - y1) * (x3 - x2) - (x2 - x1) * (y3 - y2);
        if (val == 0) return 0;  // Collinear
        return (val > 0) ? 1 : 2; // Clockwise or counterclockwise
    }

    // Method to check if a point (p) lies on a line segment (q, r)
    public static boolean onSegment(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (x2 <= Math.max(x1, x3) && x2 >= Math.min(x1, x3) &&
                y2 <= Math.max(y1, y3) && y2 >= Math.min(y1, y3))
            return true;

        return false;
    }

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
