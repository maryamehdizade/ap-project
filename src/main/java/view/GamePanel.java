package view;

import static controller.Constant.BALL_SIZE;
import static controller.Constant.MIN_SIZE;
import static controller.Controller.createBulletView;
import static controller.Controller.createPlayerView;

import controller.Update;
import model.characterModel.BulletModel;
import model.characterModel.MovePlayer;
import model.characterModel.PlayerModel;
import view.charactersView.BulletView;
import view.charactersView.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public  class GamePanel extends JPanel implements KeyListener, MouseListener {
    public PlayerModel playerModel;
    public PlayerView playerView;
    public MovePlayer movePlayer;
    private Dimension dimension = new Dimension(700,700);
    private Point loc = new Point(100,20);
    private Timer timerx;
    private Timer timery;
    private ArrayList<BulletView> bullets = new ArrayList<>();
    private ArrayList<BulletModel> bulletsModel = new ArrayList<>();


    private JFrame frame;

    public GamePanel(JFrame frame) {
        this.frame = frame;

        setBackground(new Color(0, 0, 0));
        setFocusable(true);
        setLayout(null);
//        setBounds(20,20,size.width, size.height);
//        requestFocusInWindow();
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
        new Update(this);
        setSize(dimension);


        playerModel = PlayerModel.getPlayer();
        playerView = createPlayerView(playerModel.getId());
        movePlayer = new MovePlayer(playerModel, this);

        timerx = new Timer(100, e->{
            xmin();
            ymin();
            setSize(dimension);
            setLocation(loc);
        });
        timerx.start();

    }
    public void xmin(){
        if(dimension.width > MIN_SIZE.width) {
            dimension.width -= 2;
            if(loc.getX() < 200) loc.setLocation(loc.getX() + 1,loc.getY() );
        }
    }
    public void ymin(){
        if(dimension.height > MIN_SIZE.height) {
            dimension.height -= 2;
            if(loc.getY() < 200) loc.setLocation(loc.getX(),loc.getY() + 1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.gray);
        g.drawOval((int) playerView.getLocation().getX() - BALL_SIZE/2,
                (int) playerView.getLocation().getY() - BALL_SIZE/2, BALL_SIZE, BALL_SIZE);

        for (BulletView b : bullets) {
            b.draw(g);
        }
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

    @Override
    public void mouseClicked(MouseEvent e) {
        int targetX = e.getX();
        int targetY = e.getY();

        BulletModel model = new BulletModel(playerModel.getLocation(), targetX, targetY, this);

        bulletsModel.add(model);
        bullets.add(createBulletView(model));


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


//    public static GamePanel getINSTANCE() {
//        if(INSTANCE == null)INSTANCE = new GamePanel();
//        return INSTANCE;
//    }


    public Dimension getDimension() {
        return dimension;
    }

    public ArrayList<BulletModel> getBulletsModel() {
        return bulletsModel;
    }

    public ArrayList<BulletView> getBullets() {
        return bullets;
    }

    public Point getLoc() {
        return loc;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setLoc(Point loc) {
        this.loc = loc;
    }

}
