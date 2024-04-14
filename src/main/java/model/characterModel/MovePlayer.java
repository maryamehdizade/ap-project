package model.characterModel;

import model.movement.Movable;

import javax.swing.*;
import java.awt.geom.Point2D;

import static controller.Util.addVector;

public class MovePlayer implements Movable {
    PlayerModel playerModel;
    private double xvelocity = 0;
    private double yvelocity = 0;
    private boolean dForce = false;
    private boolean uForce = false;
    private boolean lForce = false;
    private boolean rForce = false;
    private boolean r0Force = false;
    private boolean d0Force = false;
    private boolean u0Force = false;
    private boolean l0Force = false;
    private JPanel panel;

    public MovePlayer(PlayerModel playerModel, JPanel panel) {
        this.playerModel = playerModel;
        this.panel = panel;
    }

    @Override
    public void move(double velocity) {
        if(dForce || uForce || u0Force || d0Force){
            if(playerModel.getLocation().getY() > 0 && playerModel.getLocation().getY() <= panel.getHeight())
                playerModel.setLocation(addVector(playerModel.getLocation(),new Point2D.Double(0,velocity)));
        }
        if(rForce || lForce || l0Force || r0Force){
            if(playerModel.getLocation().getX() > 0 && playerModel.getLocation().getX() <= panel.getWidth())
                playerModel.setLocation(addVector(playerModel.getLocation(),new Point2D.Double(velocity, 0)));
        }
    }

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
    public void setXvelocity(double xvelocity) {
        this.xvelocity = xvelocity;
    }

    public void setYvelocity(double yvelocity) {
        this.yvelocity = yvelocity;
    }


    public double getXvelocity() {
        return xvelocity;
    }

    public double getYvelocity() {
        return yvelocity;
    }
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

}
