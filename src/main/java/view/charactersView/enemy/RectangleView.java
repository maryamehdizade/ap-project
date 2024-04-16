package view.charactersView.enemy;

import java.awt.*;
import java.awt.geom.Point2D;

import static controller.Constant.RECT_SIZE;

public class RectangleView {
    private int xp;
    private Point2D loc;
    String id;

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) loc.getX(), (int) loc.getY(), RECT_SIZE, RECT_SIZE);
    }


}
