package model.characterModel;

import model.movement.Movable;

import javax.swing.*;
import java.awt.geom.Point2D;

import static controller.Util.addVector;

public class MovePlayer implements Movable {
    PlayerModel playerModel = PlayerModel.getPlayer();
    private boolean dForce = false;
    private boolean uForce = false;
    private boolean lForce = false;
    private boolean rForce = false;
    private Timer move;

    public void setXvelocity(double xvelocity) {
        this.xvelocity = xvelocity;
    }

    public void setYvelocity(double yvelocity) {
        this.yvelocity = yvelocity;
    }

    private Timer move1;
    int delay = 1;
    private double xvelocity = 0;
    private double yvelocity = 0;

    public double getXvelocity() {
        return xvelocity;
    }

    public double getYvelocity() {
        return yvelocity;
    }

    private Timer checkForMovement;

    public MovePlayer(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    @Override
    public void move(double velocity) {
        if(dForce || uForce){
            playerModel.setLocation(addVector(playerModel.getLocation(),new Point2D.Double(0,velocity)));
        }
        if(rForce || lForce){
            playerModel.setLocation(addVector(playerModel.getLocation(),new Point2D.Double(velocity, 0)));
        }
    }
    double x = 0.5;
    double x1 = 0.5;
    Timer t;
    Timer t1;

    public boolean isdForce() {
        return dForce;
    }

    public void setdForce(boolean dForce) {
        this.dForce = dForce;
    }

    public boolean isuForce() {
        return uForce;
    }

    public void setuForce(boolean uForce) {
        this.uForce = uForce;
    }

    public boolean islForce() {
        return lForce;
    }

    public void setlForce(boolean lForce) {
        this.lForce = lForce;
    }

    public boolean isrForce() {
        return rForce;
    }

    public void setrForce(boolean rForce) {
        this.rForce = rForce;
    }
}
