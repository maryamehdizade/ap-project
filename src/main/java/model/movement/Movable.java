package model.movement;

public interface Movable {
    public int move() ;
    void move(double velocity);

    double getSpeed();
    void setSpeed(double speed);
}
