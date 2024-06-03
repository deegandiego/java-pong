package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {

    public boolean right, left;

    public double x, y;

    public int width, height;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 5;
    }

    public void tick() {
        x += (Game.ball.x - x - 6) * 0.07;

        if (x + width > Game.WIDTH) {
            x = Game.WIDTH - width;
        }

        if (x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, width, height);
    }
}
