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
import java.util.Objects;
import java.util.Random;

import static controller.Constant.*;
import static controller.Util.doTrianglesIntersect;
import static controller.Controller.*;
import static controller.Util.*;

public class Update {
    public   Timer model;

    public Timer time;

    public Timer view;
    public GamePanel panel;
    private final double a = 0.1;
    private int second;
    private boolean impact = false;
    public Update(GamePanel panel) {
        this.panel = panel;
        view = new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}};
        view.start();
        model = new Timer((int) MODEL_UPDATE_TIME, e -> {
            try {
                updateModel();
            } catch (Exception ignored) {
            }
        }){{setCoalesce(true);}};
        model.start();

        time = new Timer(1000,e -> second++);
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
    public void updateModel() throws Exception {
        moveEpsilon();
        updateBullets();
        updateRecs();
        updateTriangles();
        updateCollectable();
        victory();
    }
    private void updateCollectable(){
        for (int i = 0; i < panel.getCollectableModels().size();i ++){
            if(panel.getCollectableModels().get(i).getSecond() >= 10){
                panel.getCollectableModels().remove(i);
                panel.getCollectableViews().remove(i);
            }
        }
    }
    private void updateRecs() throws Exception {
        for (int i = 0; i < panel.getRectangleModels().size(); i++) {
            increase(panel.getRectangleModels().get(i));
            if(new Random().nextDouble(0,50) <= 1){
                panel.getRectangleModels().get(i).setSpeed(2);
            }if(second % 2 == 0)panel.getRectangleModels().get(i).setSpeed(1);
            panel.getRectangleModels().get(i).move();
            checkCollision(panel.getRectangleModels().get(i));
        }
    }
    private void updateBullets() throws Exception{
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

    private void updateTriangles() throws Exception {
        for (int i = 0; i < panel.getTriangleModels().size() ; i++) {
            increase(panel.getTriangleModels().get(i));
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

                panel.getCollectableModels().remove(i);
                panel.getCollectableViews().remove(i);

                panel.playerModel.setXp(panel.playerModel.getXp() + 5);

            }
        }
    }
    private void checkCollision(Movable movable) throws Exception {
        if(movable instanceof BulletModel) {
            //rect
            for (int j = 0; j < panel.getRectangleModels().size(); j++) {
                Polygon rec = new Polygon(panel.getRectangleModels().get(j).getxPoints(), panel.getRectangleModels().get(j).getyPoints(), 4);
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
                Polygon tri = new Polygon(panel.getTriangleModels().get(p).getxPoints(),
                        panel.getTriangleModels().get(p).getyPoints(), 3);
                if (tri.contains(bulletCenter((BulletModel) movable))) {
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
        }
        else if(movable instanceof RectangleModel){
            //epsilon

            //rect
            for (int i = 0; i < panel.getRectangleModels().size(); i++) {
                if(((RectangleModel) movable).intersects(panel.getRectangleModels().get(i))
                        && !Objects.equals(panel.getRectangleModels().get(i).getId(), ((RectangleModel) movable).getId())){
                    //impact
                    //correction
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
//                reduceHp(movable);
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
    private void victory(){
        if(panel.isVictory()){
            model.stop();
            view.stop();
            time.stop();
            panel.timerx.stop();
            new Timer(10,e -> {
                v();
                updatePlayerView();
            });
        }
    }
    private void v(){
        if(panel.getDimension().getWidth()  + 200 >= panel.playerModel.size) panel.playerModel.size += 1;
        if(panel.playerModel.size > panel.getDimension().getWidth() + 200)v1();
    }
    private void v1(){
        if(panel.getDimension().getWidth() >= 1 && panel.getDimension().getHeight() >= 1){
            panel.setDimension(new Dimension((int) (panel.getDimension().getWidth() - 0.1),
                    (int) (panel.getDimension().getHeight()  - 0.1)));
        }
        if(panel.getDimension().getHeight() <= 1 || panel.getDimension().getWidth() <= 1){
            gameOver();
            panel.game.dispose();
        }
    }

    private void death(Movable movable){
        if(movable instanceof TriangleModel){
            CollectableModel c = new CollectableModel(new Point2D.Double(((TriangleModel) movable).getX1(), ((TriangleModel) movable).getY1()));
            CollectableModel c1 = new CollectableModel(addVector(new Point2D.Double(((TriangleModel) movable).getX1(),
                    ((TriangleModel) movable).getY1()), new Point2D.Double(10,10)));

            panel.getCollectableModels().add(c);
            panel.getCollectableViews().add(createCollectableView(c));

            panel.getCollectableModels().add(c1);
            panel.getCollectableViews().add(createCollectableView(c1));
        }else if(movable instanceof RectangleModel){

            CollectableModel c = new CollectableModel(movable.getLoc());

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
        panel.timerx.stop();
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

    private void removeRect(int i){
        panel.getRectangleModels().remove(i);
        panel.getRectangleView().remove(i);
    }
    private void removeBullet(int i) {
        panel.getBullets().remove(i);
        for(int j = 0; j < panel.getBullets().size(); j++) {
            if(panel.getBullets().get(j).getId().equals(panel.getBulletsModel().get(i).getId())){
                panel.getBullets().remove(j);
                break;
            }
        }
        panel.getBulletsModel().remove(i);
    }
    private void removeBullet(BulletModel bulletModel) throws Exception {
        for (int i = 0; i < panel.getBulletsModel().size(); i++) {
            if(panel.getBulletsModel().get(i).getId().equals(bulletModel.getId()))removeBullet(i);
        }
    }
    private final int n = 10;
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

    public int getSecond() {
        return second;
    }
}
