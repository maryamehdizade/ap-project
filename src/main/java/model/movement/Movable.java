package model.movement;

import java.awt.geom.Point2D;

public interface Movable {
    int move() ;
    void move(double velocity);

    double getSpeed();
    void setSpeed(double speed);
    Point2D getLoc();
}
