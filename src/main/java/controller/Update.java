package controller;

import model.characterModel.PlayerModel;
import view.GamePanel;

import javax.swing.*;

import static controller.Constant.MODEL_UPDATE_TIME;
import static controller.Constant.FRAME_UPDATE_TIME;
import static controller.Controller.playerViewLocation;

public class Update {
    GamePanel panel;
    public Update(GamePanel panel) {
        this.panel = panel;
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}}.start();
        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}}.start();

    }
    public void updateView(){
        panel.playerView.setLocation(playerViewLocation(panel.playerModel));
    }
    public void updateModel(){

    }
}
