package view.pages;

import static controller.Constant.BALL_SIZE;
import static controller.Constant.MIN_SIZE;
import static controller.Controller.*;
import static controller.Util.playerCenter;

import controller.Update;
import model.characterModel.BulletModel;
import model.characterModel.CollectableModel;
import model.characterModel.MovePlayer;
import model.characterModel.PlayerModel;
import model.characterModel.enemy.RectangleModel;
import model.characterModel.enemy.TriangleModel;
import sound.Sound;
import view.charactersView.BulletView;
import view.charactersView.CollectableView;
import view.charactersView.PlayerView;
import view.charactersView.enemy.RectangleView;
import view.charactersView.enemy.TriangleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public  class GamePanel extends JPanel implements KeyListener, MouseListener {
    public PlayerModel playerModel;
    public PlayerView playerView;
    public MovePlayer movePlayer;
    private Dimension dimension = new Dimension(700,700);
    private Point loc = new Point(100,20);
    public Timer timerx;
    private ArrayList<BulletView> bullets = new ArrayList<>();
    private ArrayList<RectangleModel> rectangleModels = new ArrayList<>();
    private ArrayList<RectangleView> rectangleView = new ArrayList<>();
    private ArrayList<BulletModel> bulletsModel = new ArrayList<>();
    private ArrayList<TriangleModel> triangleModels = new ArrayList<>();
    private ArrayList<TriangleView> triangleViews = new ArrayList<>();
    private ArrayList<CollectableView> collectableViews = new ArrayList<>();
    private ArrayList<CollectableModel> collectableModels = new ArrayList<>();
    protected Sound sound;
    Update update;

    private Random random = new Random();
    public int bound = 60;
    public Game game;


    public GamePanel(Game game){
//        sound = new Sound();
        this.game = game;

        setBackground(new Color(0, 0, 0));
        setFocusable(true);
        setLayout(null);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
        setSize(dimension);


        playerModel = PlayerModel.getPlayer();
        playerView = createPlayerView(playerModel.getId());
        movePlayer = new MovePlayer(playerModel, this);


        RectangleModel r = new RectangleModel(this);
        rectangleModels.add(r);
        rectangleView.add(createRectView(r));

        TriangleModel t = new TriangleModel(this);
        triangleModels.add(t);
        triangleViews.add(createTriangleView(t));

        timerx = new Timer(100, e -> {
            xmin();
            ymin();
            setSize(dimension);
            setLocation(loc);
            if (random.nextDouble(0, bound) < 1) {
                RectangleModel r1 = new RectangleModel(this);
                rectangleModels.add(r1);
                rectangleView.add(createRectView(r1));
            }
            if (random.nextDouble(0, bound) < 1) {
                TriangleModel t1 = new TriangleModel(this);
                triangleModels.add(t1);
                triangleViews.add(createTriangleView(t1));
            }
        });
        timerx.start();

        update = new Update(this);

    }
    public void xmin(){
        if(dimension.width > MIN_SIZE.width) {
            dimension.width -= 2;
            if(loc.getX() < 200) loc.setLocation(loc.getX() + 1,loc.getY() );
        }
        if(playerModel.getLocation().getX() + BALL_SIZE > dimension.getWidth()){
            playerModel.setLocation(
                    new Point2D.Double(dimension.getWidth() - BALL_SIZE ,playerModel.getLocation().getY()));
        }else if(playerModel.getLocation().getX()  < 2){
            playerModel.setLocation(
                    new Point2D.Double(5,playerModel.getLocation().getY()));

        }

    }
    public void ymin(){
        if(dimension.height > MIN_SIZE.height) {
            dimension.height -= 2;
            if(loc.getY() < 200) loc.setLocation(loc.getX(),loc.getY() + 1);
        }
        if (playerModel.getLocation().getY() + BALL_SIZE> dimension.getHeight()) {
            playerModel.setLocation(
                    new Point2D.Double(playerModel.getLocation().getX(), dimension.getHeight() - BALL_SIZE - 5));
        }else if(playerModel.getLocation().getY() < 2){
            playerModel.setLocation(
                    new Point2D.Double(playerModel.getLocation().getX(),  5));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.gray);
        g.drawOval((int) playerView.getLocation().getX() - BALL_SIZE / 2,
                (int) playerView.getLocation().getY() - BALL_SIZE / 2, BALL_SIZE, BALL_SIZE);

        for (BulletView b : bullets) {
            b.draw(g);
        }
        for (RectangleView r : rectangleView) {
            r.draw(g);
        }
        for (TriangleView t : triangleViews) {
            t.draw(g);
        }

        g.setColor(new Color(133, 186, 83));
        g.drawString("xp:" + playerView.getXp() + "          " + "hp:" + playerView.getHp()
                + "             " + update.getSecond(), game.getLocation().x - 273, game.getBounds().y + 20);


        repaint();
        // TODO
        revalidate();
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
        if(keyCode == KeyEvent.VK_SPACE){
            update.model.stop();
            update.view.stop();
            update.time.stop();
            timerx.stop();
            new Store(this);
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

        BulletModel model = new BulletModel(playerCenter(playerModel), targetX, targetY, this);

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

    public ArrayList<RectangleModel> getRectangleModels() {
        return rectangleModels;
    }

    public ArrayList<RectangleView> getRectangleView() {
        return rectangleView;
    }

    public ArrayList<TriangleModel> getTriangleModels() {
        return triangleModels;
    }

    public ArrayList<TriangleView> getTriangleViews() {
        return triangleViews;
    }

}
