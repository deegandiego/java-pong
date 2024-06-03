package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 240, HEIGHT = 120;

    public static int SCALE = 3;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public Player player;

    public Enemy enemy;

    public static Ball ball;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        this.addKeyListener(this);

        player = new Player(100, HEIGHT - 10);
        enemy = new Enemy(100, 0);
        ball = new Ball(100, HEIGHT/2 - 1);
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame jFrame = new JFrame("Pong");
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(game);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        new Thread(game).start();
    }

    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = layer.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        player.render(g);
        enemy.render(g);
        ball.render(g);

        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

        bs.show();
    }

    @Override
    public void run() {
        while (true) {
            tick();
            render();

            try {
                Thread.sleep(1000 / 60);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        }

        else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        }

        else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        }
    }

}
