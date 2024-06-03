package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
    public double x, y, dx, dy, spd = 1.8;

    public int width, height;

    public void angleRandomizer() {
        int angle = 60 + (new Random().nextInt(100));
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
        System.out.println("Angle: " + angle);
    }

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 4;
        this.height = 4;

        // int angle = new Random().nextInt(359);
        angleRandomizer();
    }

    public void tick() {
        if (x + (dx * spd) + width >= Game.WIDTH || x + (dx * spd) < 0)
            dx *= -1;

        // Ponto para enemy
        if (y >= Game.HEIGHT) {
            System.out.println("Ponto do inimigo!");
            // dy *= -1;
            // y = Game.HEIGHT / 2 - 1;

            new Game();
            return;
        } else if (y < 0) {
            // Ponto para player
            System.out.println("Ponto do jogador!");

            new Game();
            return;
        }

        Rectangle bounds = new Rectangle((int) (x + (dx * spd)), (int) (y + (dy * spd)), width, height);

        Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);

        Rectangle boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width,
                Game.enemy.height);

        if (bounds.intersects(boundsPlayer)) {
            angleRandomizer();
            if (dy > 0)
                dy *= -1;
        } else if (bounds.intersects(boundsEnemy)) {
            angleRandomizer();
            if (dy < 0)
                dy *= -1;
        }

        x += dx * spd;
        y += dy * spd;
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) x, (int) y, width, height);
    }
}
