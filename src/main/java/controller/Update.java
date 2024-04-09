package controller;

import javax.swing.*;

import static controller.Constant.MODEL_UPDATE_TIME;
import static controller.Constant.FRAME_UPDATE_TIME;

public class Update {
    public Update() {
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}}.start();
        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}}.start();

    }
    public void updateView(){

    }
    public void updateModel(){

    }
}
