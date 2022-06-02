package gameObjects;

import com.FlappyBird.Game;
import loaders.GraphicsLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ground {

    private BufferedImage image;
    private int x1, x2;

    private float velX;

    public Ground() {
        x1 = 0;
        x2 = Game.WIDTH;

        velX = 3;

        image = GraphicsLoader.loadGraphics("ground.png");
    }

    public void tick() {

        x1 -= velX;
        x2 -= velX;

        if (x1 + Game.WIDTH < 0) {
            x1 = Game.WIDTH;
        }

        if (x2 + Game.WIDTH < 0) {
            x2 = Game.WIDTH;
        }

    }

    public void render(Graphics g) {
        g.drawImage(image, x1, Game.HEIGHT - 112, null);
        g.drawImage(image, x2, Game.HEIGHT - 112, null);
    }
}
