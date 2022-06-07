package gameObjects;

import com.FlappyBird.Game;
import enums.PipeType;
import handlers.ObjectHandler;
import loaders.GraphicsLoader;
import supers.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pipe extends GameObject {

    PipeType type;

    BufferedImage pipeBlock;
    BufferedImage pipe;

    public Pipe(int x, int y, int width, int height, PipeType type){
        super(x, y, width, height);

        this.type = type;
        this.velX = 3;

        pipe = GraphicsLoader.loadGraphics("pipe.png");

        if (type == PipeType.BOTTOM) {
            pipeBlock = GraphicsLoader.loadGraphics("pipebottomdow.png");
        }else if (type == PipeType.TOP) {
            pipeBlock = GraphicsLoader.loadGraphics("pipebottomtop.png");
        }
    }

    @Override
    public void tick() {
        x -= velX;

        if (x + width < 0) {
            ObjectHandler.removeObject(this);

            if (type == PipeType.TOP) {
                Game.score += 1;
                Game.a_point.sound();
                Game.a_point.setVolume();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (type == PipeType.BOTTOM) {
            g.drawImage(pipe, x, y, 50, height, null);
            g.drawImage(pipeBlock, x-1, y, null);
        } else if (type == PipeType.TOP){
            g.drawImage(pipe, x, y, 50, height, null);
            g.drawImage(pipeBlock, x-1, y + height - 23, null);
        }
    }
}
