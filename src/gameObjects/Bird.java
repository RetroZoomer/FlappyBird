package gameObjects;

import com.FlappyBird.Game;
import handlers.ObjectHandler;
import loaders.GraphicsLoader;
import supers.Animation;
import supers.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird extends GameObject {

    Animation animation;

    public float gravity;
    public float maxSpeed;

    public Bird(int x, int y, int width, int height){
        super(x, y, width, height);

        gravity = 0.3f;
        maxSpeed = 12f;

        BufferedImage[] images = new BufferedImage[3];

        for(int i = 0; i < images.length; i++){
            images[i] = GraphicsLoader.loadGraphics("bird" + i + ".png");
        }

        animation = new Animation(this, 300, true, images);
        animation.start();

        ObjectHandler.addObject(this );
    }

    @Override
    public void tick() {

        velY += gravity;
        y += velY;

        if (velY > maxSpeed) {
            velY = maxSpeed;
        }

        if(y + height > Game.HEIGHT - 115) {
            y = Game.HEIGHT - 112 - height;
        }

        if (y < 0) {
            y = 0;
        }

        GameObject temp = null;

        for (int i = 0; i < ObjectHandler.list.size(); i++) {
            temp = ObjectHandler.list.get(i);


            if (temp instanceof Pipe) {
                if (this.getBounds().intersects(temp.getBounds())) {
                    Game.gameover = true;
                }
            }


        }

        animation.tick();
    }

    @Override
    public void render(Graphics g) {
        animation.render(g);
    }
}
