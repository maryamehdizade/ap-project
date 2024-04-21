package controller;

import model.characterModel.BulletModel;
import model.characterModel.enemy.RectangleModel;
import model.characterModel.enemy.TriangleModel;
import model.movement.Movable;
import view.pages.Game;
import view.pages.GameOver;
import view.pages.GamePanel;
import view.charactersView.BulletView;
import view.charactersView.enemy.RectangleView;
import view.charactersView.enemy.TriangleView;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

import static controller.Constant.*;
import static controller.Util.doTrianglesIntersect;
import static controller.Controller.*;
import static controller.Util.*;

public class Update {
    public   Timer model;
    public Timer view;
    public GamePanel panel;
    private double a = 0.1;
    int second;
    public Update(GamePanel panel) {
        this.panel = panel;
        view = new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}};
        view.start();
        model = new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}};
        model.start();


    }
    public void updateView(){
        updatePlayerView();
        updateBulletsView();
        updateRectangleView();
        updateTrianglesView();
    }
    private void updateRectangleView(){
        for (RectangleView r : panel.getRectangleView()) {
            for (RectangleModel model: panel.getRectangleModels()) {
                if(model.getId().equals(r.getId())){
                    r.setLoc(model.getLoc());
                    r.setHp(model.getHp());
                }
            }
        }
    }
    private void updateTrianglesView(){
        for (TriangleView t : panel.getTriangleViews()) {
            for (TriangleModel model: panel.getTriangleModels()) {
                if(model.getId().equals(t.getId())){
                    t.setX1(model.getX1());
                    t.setX2(model.getX2());
                    t.setX3(model.getX3());
                    t.setY1(model.getY1());
                    t.setY2(model.getY2());
                    t.setY3(model.getY3());
                    t.setHp(model.getHp());

                }
            }
        }
    }
    private void updateBulletsView(){
        for (BulletView b : panel.getBullets()) {
            for (BulletModel m : panel.getBulletsModel()) {
                if(m.getId().equals(b.getId())){
                    b.setLoc(m.getLoc());
                }
            }
        }
    }
    private void updatePlayerView(){
        panel.playerView.setLocation(playerViewLocation(panel.playerModel));
        panel.playerView.setXp(playerViewXp(panel.playerModel));
        panel.playerView.setHp(playerViewHp(panel.playerModel));
    }
    public void updateModel() {
        moveEpsilon();
        updateBullets();
        updateRecs();
        updateTriangles();
    }
    private void updateRecs(){
        for (int i = 0; i < panel.getRectangleModels().size(); i++) {
            panel.getRectangleModels().get(i).move();
            checkCollisioin(panel.getRectangleModels().get(i));
        }
    }
    private void updateBullets() throws ArrayIndexOutOfBoundsException{
        for (int i = 0; i < panel.getBulletsModel().size(); i++) {
            int a = panel.getBulletsModel().get(i).move();
            if(a == 1)moveLeft();
            else if(a == 2)moveRight();
            else if(a == 3)moveUp();
            else if(a == 4)moveDown();
            checkCollisioin(panel.getBulletsModel().get(i));
            if (a != 0) {
                removeBullet(i);
            }

        }
    }

    private void updateTriangles(){
        for (int i = 0; i < panel.getTriangleModels().size() ; i++){
            panel.getTriangleModels().get(i).move();
            checkCollisioin(panel.getTriangleModels().get(i));
        }
    }

    private void moveEpsilon(){
        if (panel.movePlayer.isdForce()) {
            panel.movePlayer.setYvelocity(panel.movePlayer.getYvelocity() + a);
            panel.movePlayer.move(panel.movePlayer.getYvelocity());
        }
        if (panel.movePlayer.isuForce()) {
            panel.movePlayer.setYvelocity(panel.movePlayer.getYvelocity()+ a);
            panel.movePlayer.move(-panel.movePlayer.getYvelocity());
        }
        if (panel.movePlayer.isrForce()) {
            panel.movePlayer.setXvelocity(panel.movePlayer.getXvelocity()+ a);
            panel.movePlayer.move(panel.movePlayer.getXvelocity());
        }
        if (panel.movePlayer.islForce()) {
            panel.movePlayer.setXvelocity(panel.movePlayer.getXvelocity()+ a);
            panel.movePlayer.move(-panel.movePlayer.getXvelocity());
        }
        if (panel.movePlayer.isR0Force()){
            panel.movePlayer.setXvelocity(panel.movePlayer.getXvelocity()- a);
            if(panel.movePlayer.getXvelocity() <= 0){
                panel.movePlayer.setR0Force(false);
                panel.movePlayer.setXvelocity(0);
            }
            panel.movePlayer.move(panel.movePlayer.getXvelocity());
        }
        if (panel.movePlayer.isL0Force()) {
            panel.movePlayer.setXvelocity(panel.movePlayer.getXvelocity()- a);
            if(panel.movePlayer.getXvelocity() <= 0){
                panel.movePlayer.setL0Force(false);
                panel.movePlayer.setXvelocity(0);
            }
            panel.movePlayer.move(-panel.movePlayer.getXvelocity());
        }
        if (panel.movePlayer.isU0Force()) {
            panel.movePlayer.setYvelocity(panel.movePlayer.getYvelocity()- a);
            if(panel.movePlayer.getYvelocity() <= 0){
                panel.movePlayer.setU0Force(false);
                panel.movePlayer.setYvelocity(0);
            }
            panel.movePlayer.move(-panel.movePlayer.getYvelocity());
        }
        if (panel.movePlayer.isD0Force()) {
            panel.movePlayer.setYvelocity(panel.movePlayer.getYvelocity()- a);
            if(panel.movePlayer.getYvelocity() <= 0){
                panel.movePlayer.setD0Force(false);
                panel.movePlayer.setYvelocity(0);
            }
            panel.movePlayer.move(panel.movePlayer.getYvelocity());
        }
        if (panel.playerModel.getLocation().getY() + BALL_SIZE> panel.getHeight()) {
            panel.playerModel.setLocation(
                    new Point2D.Double(panel.playerModel.getLocation().getX(), panel.getHeight() - BALL_SIZE - 5));
        }else if(panel.playerModel.getLocation().getY() < 2){
            panel.playerModel.setLocation(
                    new Point2D.Double(panel.playerModel.getLocation().getX(),  5));
        }
        if(panel.playerModel.getLocation().getX() + BALL_SIZE > panel.getWidth()){
            panel.playerModel.setLocation(
                    new Point2D.Double(panel.getWidth() - BALL_SIZE ,panel.playerModel.getLocation().getY()));
        }else if(panel.playerModel.getLocation().getX()  < 2){
            panel.playerModel.setLocation(
                    new Point2D.Double(5,panel.playerModel.getLocation().getY()));

        }
    }
    private void checkCollisioin(Movable movable){
        if(movable instanceof BulletModel){
            //rect
            for (int j = 0; j < panel.getRectangleModels().size(); j++) {
                if(doesRecIntersectEpsilon(panel.getRectangleModels().get(j), ((BulletModel) movable).getLoc(), BULLET_SIZE) != 0){
                    panel.getRectangleModels().get(j).setHp(panel.getRectangleModels().get(j).getHp() - 5);
                    if(panel.getRectangleModels().get(j).getHp() <= 0)removeRect(j);
                    removeBullet((BulletModel) movable);
                    //correction
                    //impact
                }
            }
            //tria
            for (int p = 0; p < panel.getTriangleModels().size(); p++) {
                if(doesCircleIntersectTriangle(bulletCenter((BulletModel) movable).getX(),
                        bulletCenter((BulletModel) movable).getY(), panel.getTriangleModels().get(p)) != 0){
                    panel.getTriangleModels().get(p).setHp(panel.getTriangleModels().get(p).getHp() - 5);
                    if(panel.getTriangleModels().get(p).getHp() <= 0)removeTriangle(p);
                    removeBullet((BulletModel) movable);
                    //impact
                }
            }
        }
        else if(movable instanceof RectangleModel){
            //epsilon
            int r = doesRecIntersectEpsilon((RectangleModel) movable, playerCenter(panel.playerModel), BALL_SIZE);
            if(r == 1){
                reduceHp(movable);
                //impact
            }else if(r == 2){
                //impact
            }

            //rect
            for (int i = 0; i < panel.getRectangleModels().size(); i++) {
                if(((RectangleModel) movable).intersects(panel.getRectangleModels().get(i))
                        && !Objects.equals(panel.getRectangleModels().get(i).getId(), ((RectangleModel) movable).getId())){
                    //impact
                    //correction
                    System.out.println("hi");
                }
            }
            //tria
            for (int i = 0; i < panel.getTriangleModels().size(); i++) {
                if(isCollision(panel.getTriangleModels().get(i), (RectangleModel) movable)){
                    //impact
                }
            }
        }
        else if(movable instanceof TriangleModel){
            //epsilon
            int c = doesCircleIntersectTriangle(playerCenter(panel.playerModel).getX(), playerCenter(panel.playerModel).getY() , (TriangleModel) movable);
            if(c == 1){
                reduceHp(movable);
                //impact
            }else if(c == 2){
                //impact
            }
            //tria
            for (int i = 0; i < panel.getTriangleModels().size(); i++) {
                if(doTrianglesIntersect (panel.getTriangleModels().get(i), (TriangleModel) movable)
                        && !Objects.equals(panel.getTriangleModels().get(i).getId(), ((TriangleModel) movable).getId())){
                    //impact
                }
            }
        }
    }
    private void reduceHp(Movable movable){
        int w = 10;
        if(movable instanceof RectangleModel)w = 6;
        panel.playerModel.setHp(panel.playerModel.getHp() - w);
        if(panel.playerModel.getHp() <= 0){
            model.stop();
            view.stop();
            panel.timerx.stop();

            new GameOver(this);

        }
    }


    private void impact(){
        //todo
    }

    //remove

    private void removeTriangle(int i){
        panel.getTriangleViews().remove(i);
        panel.getTriangleModels().remove(i);
    }

    private void removeRect(int i){
        panel.getRectangleModels().remove(i);
        panel.getRectangleView().remove(i);
    }
    private void removeBullet(int i){
        panel.getBullets().remove(i);
        for(int j = 0; j < panel.getBullets().size(); j++) {
            if(panel.getBullets().get(j).getId().equals(panel.getBulletsModel().get(i).getId())){
                panel.getBullets().remove(j);
                break;
            }
        }
        panel.getBulletsModel().remove(i);
    }
    private void removeBullet(BulletModel bulletModel){
        for (int i = 0; i < panel.getBulletsModel().size(); i++) {
            if(panel.getBulletsModel().get(i).getId().equals(bulletModel.getId()))removeBullet(i);
        }
    }
    private int n = 10;
    private void moveLeft(){
        panel.setLoc(new Point((int) (panel.getLoc().getX() - n/2), (int) panel.getLoc().getY()));
        panel.setDimension(new Dimension((int) (panel.getDimension().getWidth() + n), (int) panel.getDimension().getHeight()));

    }
    private void moveRight(){
        panel.setLoc(new Point((int) (panel.getLoc().getX() + n/2), (int) panel.getLoc().getY()));
        panel.setDimension(new Dimension((int) (panel.getDimension().getWidth() + n), (int) panel.getDimension().getHeight()));
    }
    private void moveUp(){
        panel.setLoc(new Point((int) (panel.getLoc().getX() ), (int) panel.getLoc().getY()- n/2));
        panel.setDimension(new Dimension((int)(panel.getDimension().getWidth()), (int)panel.getDimension().getHeight()+ n));

    }
    private void moveDown(){
        panel.setLoc(new Point((int) (panel.getLoc().getX() ), (int) panel.getLoc().getY() +  n/2));
        panel.setDimension(new Dimension((int)(panel.getDimension().getWidth()), (int)panel.getDimension().getHeight()+ n));
    }
}
