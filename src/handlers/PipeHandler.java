package handlers;

import com.FlappyBird.Game;
import enums.PipeType;
import gameObjects.Pipe;

import java.util.Random;

public class PipeHandler {

    private static Random random = new Random();

    public static int groundSize = 112;
    public static int area = Game.HEIGHT - groundSize;
    public static int spacing = 120;
    public static int minSize = 20;
    public static int maxSize = area + spacing + minSize - 300;
    public static int delay = 1;
    public static int now;

    public static void spawnPipe() {
        int heightTop = random.nextInt(maxSize) + 1;

        while (heightTop < minSize) {
            heightTop = random.nextInt(maxSize) + 1;
        }

        int heightBottom = area - spacing - heightTop;

        Pipe pipeTOP = new Pipe(500, -5, 78, heightTop, PipeType.TOP);
        Pipe pipeBottom = new Pipe(500, heightTop + spacing, 78, heightBottom, PipeType.BOTTOM);

        ObjectHandler.addObject(pipeTOP);
        ObjectHandler.addObject(pipeBottom);
    }

    public static void tick() {
        if (now < delay) {
            now++;
        } else {
            spawnPipe();
            now = 0;
        }
    }
}
