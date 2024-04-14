package view;

import static controller.Constant.BALL_SIZE;
import static controller.Constant.MIN_SIZE;
import static controller.Controller.createPlayerView;

import controller.Update;
import model.characterModel.MovePlayer;
import model.characterModel.PlayerModel;
import view.charactersView.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public final class GamePanel extends JPanel implements KeyListener {
    private static GamePanel INSTANCE;
    public PlayerModel playerModel;
    public PlayerView playerView;
    public MovePlayer movePlayer;
    private Dimension size = new Dimension(700,700);
    private Point loc = new Point(100,20);
    private Timer timerx;
    private Timer timery;



    public GamePanel() {

        setBackground(new Color(0, 0, 0));
        setFocusable(true);
        setLayout(null);
        setBounds(20,20,size.width, size.height);
        requestFocusInWindow();
        addKeyListener(this);
        new Update(this);
        setSize(size);


        playerModel = PlayerModel.getPlayer();
        playerView = createPlayerView(playerModel.getId());
        movePlayer = new MovePlayer(playerModel, this);

        timerx = new Timer(100, e->{
            xmin();
            ymin();
            setSize(size);
            setLocation(loc);
        });
        timerx.start();

    }
    public void xmin(){
        if(size.width > MIN_SIZE.width) {
            size.width -= 2;
            if(loc.getX() < 300) loc.setLocation(loc.getX() + 1,loc.getY() );
        }
    }
    public void ymin(){
        if(size.height > MIN_SIZE.height) {
            size.height -= 2;
            if(loc.getY() < 300) loc.setLocation(loc.getX(),loc.getY() + 1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.gray);
        g.drawOval((int) playerView.getLocation().getX(), (int) playerView.getLocation().getY(), BALL_SIZE, BALL_SIZE);
        repaint();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            movePlayer.setlForce(true);
        } else if (keyCode == KeyEvent.VK_D) {
            movePlayer.setrForce(true);
        } else if (keyCode == KeyEvent.VK_W) {
            movePlayer.setuForce(true);
        } else if (keyCode == KeyEvent.VK_S) {
            movePlayer.setdForce(true);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_D) {
            movePlayer.setrForce(false);
            movePlayer.setR0Force(true);
        }
        if (keyCode == KeyEvent.VK_A) {
            movePlayer.setlForce(false);
            movePlayer.setL0Force(true);
        }
        if (keyCode == KeyEvent.VK_S) {
            movePlayer.setdForce(false);
            movePlayer.setD0Force(true);
        }
        if (keyCode == KeyEvent.VK_W) {
            movePlayer.setuForce(false);
            movePlayer.setU0Force(true);
        }
    }


    public static GamePanel getINSTANCE() {
        if(INSTANCE == null)INSTANCE = new GamePanel();
        return INSTANCE;
    }
}
