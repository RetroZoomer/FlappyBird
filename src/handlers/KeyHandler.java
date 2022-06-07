package handlers;

import com.FlappyBird.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Game.bird.setVelY(-6.3f);
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            PipeHandler.spawnPipe();
        }
        if (!Game.gameover) {
            Game.a_wing.sound();
            Game.a_wing.setVolume();
        }
        Game.startScreen = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
