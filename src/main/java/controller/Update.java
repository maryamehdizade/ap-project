package controller;

import model.characterModel.BulletModel;
import model.characterModel.enemy.RectangleModel;
import view.GamePanel;
import view.charactersView.BulletView;
import view.charactersView.enemy.RectangleView;

import javax.swing.*;

import java.awt.*;

import static controller.Constant.MODEL_UPDATE_TIME;
import static controller.Constant.FRAME_UPDATE_TIME;
import static controller.Controller.*;

public class Update {
    GamePanel panel;
    int second;
    public Update(GamePanel panel) {
        this.panel = panel;
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}}.start();
        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}}.start();


    }
    public void updateView(){
        updatePlayerView();
        updateBulletsView();
        updateRectangleView();

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
    }
    private void updateRecs(){
        for (int i = panel.getRectangleModels().size() - 1; i >= 0; i--) {
            panel.getRectangleModels().get(i).move();
        }
    }
    private void updateBullets() throws ArrayIndexOutOfBoundsException{
        for (int i = panel.getBulletsModel().size() - 1; i >= 0; i--) {
            int a = panel.getBulletsModel().get(i).move();
            if(a == 1)moveLeft();
            else if(a == 2)moveRight();
            else if(a == 3)moveUp();
            else if(a == 4)moveDown();
            if (a != 0) {
                panel.getBullets().remove(i);
                for(int j = 0; j < panel.getBullets().size(); j++) {
                    if(panel.getBullets().get(j).getId().equals(panel.getBulletsModel().get(i).getId())){
                        panel.getBullets().remove(j);
                        break;
                    }
                }
                panel.getBulletsModel().remove(i);
            }
        }
    }
    private void moveLeft(){
        panel.setLoc(new Point((int) (panel.getLoc().getX() - 5), (int) panel.getLoc().getY()));
        panel.setDimension(new Dimension((int) (panel.getDimension().getWidth() + 5), (int) panel.getDimension().getHeight()));

    }
    private void moveRight(){
        panel.setLoc(new Point((int) (panel.getLoc().getX() + 5), (int) panel.getLoc().getY()));
        panel.setDimension(new Dimension((int) (panel.getDimension().getWidth() + 5), (int) panel.getDimension().getHeight()));
    }
    private void moveUp(){
        panel.setLoc(new Point((int) (panel.getLoc().getX() ), (int) panel.getLoc().getY()- 5));
        panel.setDimension(new Dimension((int)(panel.getDimension().getWidth()), (int)panel.getDimension().getHeight()+ 5));

    }
    private void moveDown(){
        panel.setLoc(new Point((int) (panel.getLoc().getX() ), (int) panel.getLoc().getY() +  5));
        panel.setDimension(new Dimension((int)(panel.getDimension().getWidth()), (int)panel.getDimension().getHeight()+ 5));
    }
    private void moveEpsilon(){
        if (panel.movePlayer.isdForce()) {
            panel.movePlayer.setYvelocity(panel.movePlayer.getYvelocity() + 0.2);
            panel.movePlayer.move(panel.movePlayer.getYvelocity());
        }
        if (panel.movePlayer.isuForce()) {
            panel.movePlayer.setYvelocity(panel.movePlayer.getYvelocity()+ 0.2);
            panel.movePlayer.move(-panel.movePlayer.getYvelocity());
        }
        if (panel.movePlayer.isrForce()) {
            panel.movePlayer.setXvelocity(panel.movePlayer.getXvelocity()+ 0.2);
            panel.movePlayer.move(panel.movePlayer.getXvelocity());
        }
        if (panel.movePlayer.islForce()) {
            panel.movePlayer.setXvelocity(panel.movePlayer.getXvelocity()+ 0.2);
            panel.movePlayer.move(-panel.movePlayer.getXvelocity());
        }
        if (panel.movePlayer.isR0Force()){
            panel.movePlayer.setXvelocity(panel.movePlayer.getXvelocity()- 0.2);
            if(panel.movePlayer.getXvelocity() <= 0){
                panel.movePlayer.setR0Force(false);
                panel.movePlayer.setXvelocity(0);
            }
            panel.movePlayer.move(panel.movePlayer.getXvelocity());
        }
        if (panel.movePlayer.isL0Force()) {
            panel.movePlayer.setXvelocity(panel.movePlayer.getXvelocity()- 0.2);
            if(panel.movePlayer.getXvelocity() <= 0){
                panel.movePlayer.setL0Force(false);
                panel.movePlayer.setXvelocity(0);
            }
            panel.movePlayer.move(-panel.movePlayer.getXvelocity());
        }
        if (panel.movePlayer.isU0Force()) {
            panel.movePlayer.setYvelocity(panel.movePlayer.getYvelocity()- 0.2);
            if(panel.movePlayer.getYvelocity() <= 0){
                panel.movePlayer.setU0Force(false);
                panel.movePlayer.setYvelocity(0);
            }
            panel.movePlayer.move(-panel.movePlayer.getYvelocity());
        }
        if (panel.movePlayer.isD0Force()) {
            panel.movePlayer.setYvelocity(panel.movePlayer.getYvelocity()- 0.2);
            if(panel.movePlayer.getYvelocity() <= 0){
                panel.movePlayer.setD0Force(false);
                panel.movePlayer.setYvelocity(0);
            }
            panel.movePlayer.move(panel.movePlayer.getYvelocity());
        }
    }
}
