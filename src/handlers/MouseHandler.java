package handlers;

import com.FlappyBird.Game;
import supers.Button_;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (Button_.checkCollision(e.getX(), e.getY(), Game.button)){
            if (Game.gameover) {
                Game.button.pressed = true;
                ObjectHandler.list.clear();
                ObjectHandler.addObject(Game.bird);
                Game.gameover = false;
                Game.button.pressed = false;
                Game.bird.setX(30);
                Game.bird.setY(30);
                Game.score = 0;
            }
        }
        Game.bird.setVelY(-5);

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
}
