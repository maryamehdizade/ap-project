package controller;

import model.characterModel.BulletModel;
import model.characterModel.PlayerModel;
import model.characterModel.enemy.CollectableModel;
import model.characterModel.enemy.RectangleModel;
import model.characterModel.enemy.TriangleModel;
import model.movement.Movable;
import view.pages.GameOver;
import view.pages.GamePanel;
import view.charactersView.BulletView;
import view.charactersView.enemy.RectangleView;
import view.charactersView.enemy.TriangleView;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static controller.Constant.*;
import static controller.Controller.*;
import static controller.Util.*;
import static java.awt.geom.Point2D.distance;

public class Update {
    public   Timer model;

    public Timer time;

    public Timer view;
    public GamePanel panel;
    private final double a = 0.1;
    private double second;
    Point2D collision ;
    Point2D collision2;
    private boolean impact = false;
    public Update(GamePanel panel) {
        this.panel = panel;
        view = new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}};
        view.start();
        model = new Timer((int) MODEL_UPDATE_TIME, e -> {
            try {
                updateModel();
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }){{setCoalesce(true);}};
        model.start();

        time = new Timer(100,e -> second += 0.1);
        time.start();

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
        panel.playerView.size = panel.playerModel.size;
    }

    //model
    public void updateModel(){
        moveEpsilon();
        updateBullets();
        updateRecs();
        updateTriangles();
        updateCollectable();
        victory();
        if(second <= 0.1){
            panel.xmin();
            panel.ymin();
            panel.setSize(panel.getDimension());
            panel.setLocation(panel.getLoc());
        }
        if(second >= 10) {
            panel.xmin();
            panel.ymin();
            panel.setSize(panel.getDimension());
            panel.setLocation(panel.getLoc());
        }
        if(panel.wave == 2 && !panel.wave2){
            panel.bound = 230;
            panel.wave2 = true;
            panel.wave1 = false;
        }
        if(panel.wave == 3 && !panel.wave3){
            panel.bound = 200;
            panel.wave3 = true;
            panel.wave2 = false;
        }
        if (panel.random.nextDouble(0, panel.bound) < 1) {
            if((panel.wave == 1 && panel.enemies <= 10) || (panel.wave == 2 && panel.enemies <= 15) ||
                    (panel.wave == 3 && panel.enemies <= 20)) {
                RectangleModel r1 = new RectangleModel(panel);
                panel.getRectangleModels().add(r1);
                panel.getRectangleView().add(createRectView(r1));
                panel.getMovables().add(r1);
                panel.enemies ++;
            }
            panel.start = true;
        }
        if (panel.random.nextDouble(0, panel.bound) < 1) {
            if((panel.wave == 1 && panel.enemies <= 10) || (panel.wave == 2 && panel.enemies <= 15) || (panel.wave == 3 && panel.enemies <= 25)) {
                TriangleModel t1 = new TriangleModel(panel);
                panel.getTriangleModels().add(t1);
                panel.getTriangleViews().add(createTriangleView(t1));
                panel.getMovables().add(t1);
                panel.enemies ++;
            }
            panel.start = true;
        }
        panel.Wave();
    }
    private void updateCollectable(){
        for (int i = 0; i < panel.getCollectableModels().size();i ++){
            if(panel.getCollectableModels().get(i).getSecond() >= 10){
                panel.getCollectableModels().remove(i);
                panel.getCollectableViews().remove(i);
            }
        }
    }
    private void updateRecs(){
        for (int i = 0; i < panel.getRectangleModels().size(); i++) {
            if(new Random().nextDouble(0,50) <= 1){
                panel.getRectangleModels().get(i).setSpeed(2);
            }if(second % 2 == 0)panel.getRectangleModels().get(i).setSpeed(1);
            panel.getRectangleModels().get(i).move();
            checkCollision(panel.getRectangleModels().get(i));
        }
    }
    private void updateBullets(){
        for (int i = 0; i < panel.getBulletsModel().size(); i++) {
            int a = panel.getBulletsModel().get(i).move();
            if(a == 1)moveLeft();
            else if(a == 2)moveRight();
            else if(a == 3)moveUp();
            else if(a == 4)moveDown();
            checkCollision(panel.getBulletsModel().get(i));
            if (a != 0) {
                removeBullet(i);
            }

        }
    }

    private void updateTriangles() {
        for (int i = 0; i < panel.getTriangleModels().size() ; i++) {
            if (Math.abs(panel.getTriangleModels().get(i).getY3() - panel.playerModel.getLocation().getY()) >= 200) {
                panel.getTriangleModels().get(i).setSpeed(2);
            }else{
                panel.getTriangleModels().get(i).setSpeed(1);
            }
            panel.getTriangleModels().get(i).move();
            checkCollision(panel.getTriangleModels().get(i));
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
        //wall
        if (panel.playerModel.getLocation().getY() + BALL_SIZE> panel.getHeight()) {
            panel.playerModel.setLocation(
                    new Point2D.Double(panel.playerModel.getLocation().getX(), panel.getHeight() - BALL_SIZE - 5));
        }else if(panel.playerModel.getLocation().getY() < 2){
            panel.playerModel.setLocation(
                    new Point2D.Double(panel.playerModel.getLocation().getX(),  10));
        }
        if(panel.playerModel.getLocation().getX() + BALL_SIZE > panel.getWidth()){
            panel.playerModel.setLocation(
                    new Point2D.Double(panel.getWidth() - BALL_SIZE ,panel.playerModel.getLocation().getY()));
        }else if(panel.playerModel.getLocation().getX()  < 2){
            panel.playerModel.setLocation(
                    new Point2D.Double(10,panel.playerModel.getLocation().getY()));

        }
        getC();
    }
    private void getC(){
        for (int i = 0; i < panel.getCollectableModels().size(); i++) {
            CollectableModel c = panel.getCollectableModels().get(i);
            if(Math.abs(c.getLoc().getX() - panel.playerModel.getLocation().getX()) <= 13 &&
                    Math.abs(c.getLoc().getY() - panel.playerModel.getLocation().getY()) <= 13 ){

                panel.getCollectableModels().get(i).timer.stop();

                panel.getCollectableModels().get(i).timer.stop();

                panel.getCollectableModels().remove(i);
                panel.getCollectableViews().remove(i);

                panel.playerModel.setXp(panel.playerModel.getXp() + 5);

            }
        }
    }
    private void checkCollision(Movable movable) {
        if (movable instanceof BulletModel) {
            for (int j = 0; j < panel.getRectangleModels().size(); j++) {
                Polygon rec = new Polygon(panel.getRectangleModels().get(j).getxPoints(), panel.getRectangleModels().get(j).getyPoints(), 4);
//                if(distance(bulletCenter((BulletModel) movable), rectCenter(panel.getRectangleModels().get(j))) <=
//                        BULLET_SIZE/2.0 + RECT_SIZE/2.0) {
                if (rec.contains(bulletCenter((BulletModel) movable))) {
                    removeBullet((BulletModel) movable);
                    panel.getRectangleModels().get(j).setHp(panel.getRectangleModels().get(j).getHp() - 5);
                    if (panel.getRectangleModels().get(j).getHp() <= 0) {
                        removeFromMovables(panel.getRectangleModels().get(j));
                        death(panel.getRectangleModels().get(j));
                        removeRect(j);
                    }
                    //impact
                }
            }
            //tria
            for (int p = 0; p < panel.getTriangleModels().size(); p++) {
//                Polygon rec = new Polygon(panel.getTriangleModels().get(p).getxPoints(),
//                        panel.getTriangleModels().get(p).getyPoints(), 3);
                if (distance(bulletCenter((BulletModel) movable).getX(), bulletCenter((BulletModel) movable).getY(),
                        triangleCenter(panel.getTriangleModels().get(p)).getX(), triangleCenter(panel.getTriangleModels().get(p)).getY())
                        <= (double) TRI_SIZE / 2 + (double) BULLET_SIZE / 2 + 20) {
                    removeBullet((BulletModel) movable);
                    panel.getTriangleModels().get(p).setHp(panel.getTriangleModels().get(p).getHp() - 5);
                    if (panel.getTriangleModels().get(p).getHp() <= 0) {
                        removeFromMovables(panel.getTriangleModels().get(p));
                        death(panel.getTriangleModels().get(p));
                        removeTriangle(p);
                    }
                    //impact
                }
            }

        } else if (movable instanceof RectangleModel) {
            //epsilon
             collision = ert(panel.playerModel, movable);
             collision2 = er(panel.playerModel, (RectangleModel) movable);
            if(collision != null){
//                reduceHp(movable);
                //impact
            }else if(collision2 != null){
                //impact
            }

            //rect
            for (int i = 0; i < panel.getRectangleModels().size(); i++) {
                Polygon rec = new Polygon(movable.getxPoints(),
                        movable.getyPoints(), 4);
                for (int j = 0; j < 4; j++) {
                    if (rec.contains(new Point2D.Double(panel.getRectangleModels().get(i).getxPoints()[j],
                            panel.getRectangleModels().get(i).getyPoints()[j]))) {
                        //impact
                    }
                }
            }
            //tria
            for (int i = 0; i < panel.getTriangleModels().size(); i++) {
                Polygon rec = new Polygon(movable.getxPoints(),
                        movable.getyPoints(), 4);
                for (int j = 0; j < 3; j++) {
                    if (rec.contains(new Point2D.Double(panel.getTriangleModels().get(i).getxPoints()[j],
                            panel.getTriangleModels().get(i).getyPoints()[j]))) {
                        //impact
                    }
                }
            }
        } else if (movable instanceof TriangleModel) {
            //epsilon
            collision = ert(panel.playerModel, movable);
            collision2 = et(panel.playerModel, (TriangleModel) movable);
            if (collision != null) {
//                reduceHp(movable);
                //impact
            }else if(collision2 != null){
                //impact
            }
            //tria
            for (int i = 0; i < panel.getTriangleModels().size(); i++) {
                Polygon tri = new Polygon(movable.getxPoints(), movable.getyPoints(), 3);
                for (int j = 0; j < 3; j++) {
                    if (tri.contains(new Point2D.Double(panel.getTriangleModels().get(i).getxPoints()[j],
                            panel.getTriangleModels().get(i).getyPoints()[j]))) {
                        //impact
                    }
                }


                //tria
            }
        }
    }

    Timer t;
    private void victory(){
        if(panel.isVictory()){
            model.stop();
            view.stop();
            time.stop();
            t = new Timer(10,e -> {
                v();updatePlayerView();
            });
            t.start();
        }
    }
    private void v(){
        if(panel.getDimension().getWidth()  + 200 >= panel.playerModel.size) {
            panel.playerModel.size += 2;
            if(panel.playerModel.getLocation().getX() < panel.getDimension().getWidth()) panel.playerModel.setLocation(new Point2D.Double
                    (panel.playerModel.getLocation().getX() - 1, panel.playerModel.getLocation().getY() - 1));
        }

        if(panel.playerModel.size > panel.getDimension().getWidth()) v1();
    }
    private void v1(){
        if(panel.getDimension().getWidth() >= 1 && panel.getDimension().getHeight() >= 1){
            panel.setDimension(new Dimension((int) (panel.getDimension().getWidth() - 0.1),
                    (int) (panel.getDimension().getHeight()  - 0.1)));
            panel.setSize(panel.getDimension());
        }
        if(panel.getDimension().getHeight() <= 1 || panel.getDimension().getWidth() <= 1){
            t.stop();
            gameOver();
            panel.game.dispose();
        }
    }

    private void death(Movable movable){
        int n = 1;
        if(movable instanceof TriangleModel)n = 2;
        for (int i = 0; i < n; i++) {
            CollectableModel c = new CollectableModel(addVector(movable.getLoc(), new Point2D.Double(i*10, i *10)));
            panel.getCollectableModels().add(c);
            panel.getCollectableViews().add(createCollectableView(c));
        }
    }
    private void reduceHp(Movable movable){
        int w = 10;
        if(movable instanceof RectangleModel)w = 6;
        panel.playerModel.setHp(panel.playerModel.getHp() - w);
        if(panel.playerModel.getHp() <= 0){
            gameOver();
        }
    }
    private void gameOver(){
        model.stop();
        view.stop();
        time.stop();
        PlayerModel.setPlayer( null);

        new GameOver(this);
    }
    void increase(Movable movable){
        if(movable.getSpeed() < 1){
            movable.setSpeed(movable.getSpeed() + a);
        }if(movable.getSpeed() > 1){
            movable.setSpeed(movable.getSpeed() - a);
        }
    }

    //remove
    private void removeFromMovables(Movable movable){
        for (int i = 0; i < panel.getMovables().size(); i ++) {
            if(movable == panel.getMovables().get(i)){
                removeFromMovables(i);
            }
        }
    }
    private void removeFromMovables(int i) {
     panel.getMovables().remove(i);
    }

    private void removeTriangle(int i){
        panel.getTriangleViews().remove(i);
        panel.getTriangleModels().remove(i);
    }
    private void removeTriangle(TriangleModel t){
        for (int i = 0; i < panel.getTriangleModels().size(); i++) {
            if(panel.getTriangleModels().get(i) == t){
                removeTriangle(i);
            }
        }
    }

    private void removeRect(int i){
        panel.getRectangleModels().remove(i);
        panel.getRectangleView().remove(i);
    }
    private void removeRect(RectangleModel i){
        for (int j = 0; j < panel.getRectangleModels().size(); j++) {
            if(panel.getRectangleModels().get(j) == i){
                removeRect(j);
            }
        }
    }
    private void removeBullet(int i) {
        if(!panel.getBulletsModel().isEmpty()) {
            panel.getBullets().remove(i);
            for (int j = 0; j < panel.getBullets().size(); j++) {
                if (panel.getBullets().get(j).getId().equals(panel.getBulletsModel().get(i).getId())) {
                    panel.getBullets().remove(j);
                    break;
                }
            }
            panel.getBulletsModel().remove(i);
        }
    }
    private void removeBullet(BulletModel bulletModel){
        for (int i = 0; i < panel.getBulletsModel().size(); i++) {
            if(panel.getBulletsModel().get(i).getId().equals(bulletModel.getId()))removeBullet(i);
        }
    }
    private final int n = 20;
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

    public double getSecond() {
        return second;
    }
}
