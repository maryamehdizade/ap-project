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
    private boolean r0Force = false;
    private boolean d0Force = false;
    private boolean u0Force = false;
    private boolean l0Force = false;

    public boolean isR0Force() {
        return r0Force;
    }

    public void setR0Force(boolean r0Force) {
        this.r0Force = r0Force;
    }

    public boolean isD0Force() {
        return d0Force;
    }

    public void setD0Force(boolean d0Force) {
        this.d0Force = d0Force;
    }

    public boolean isU0Force() {
        return u0Force;
    }

    public void setU0Force(boolean u0Force) {
        this.u0Force = u0Force;
    }

    public boolean isL0Force() {
        return l0Force;
    }

    public void setL0Force(boolean l0Force) {
        this.l0Force = l0Force;
    }

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
        if(dForce || uForce || u0Force || d0Force){
            playerModel.setLocation(addVector(playerModel.getLocation(),new Point2D.Double(0,velocity)));
        }
        if(rForce || lForce || l0Force || r0Force){
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
