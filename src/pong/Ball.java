package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
    public boolean right, left;

    public double x, y, dx, dy, spd = 1.6;

    public int width, height;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 4;
        this.height = 4;

        dx = new Random().nextGaussian();
        dy = new Random().nextGaussian();
    }

    public void tick() {
        x += dx * spd;
        y += dy * spd;
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) x, (int) y, width, height);
    }
}
