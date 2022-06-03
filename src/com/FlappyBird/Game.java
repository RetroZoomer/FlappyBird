package com.FlappyBird;

import gameObjects.Bird;
import gameObjects.Ground;
import handlers.KeyHandler;
import handlers.MouseHandler;
import handlers.ObjectHandler;
import handlers.PipeHandler;
import loaders.GraphicsLoader;
import supers.Button_;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.ServerSocket;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 288;
    public static final int HEIGHT = 512;

    public boolean running;
    public static boolean gameover;

    public static BufferedImage img_gameover;
    public static BufferedImage background;
    public static Ground ground;
    public static Bird bird;

    public static Button_ button;


    Thread thread;
    ServerSocket serverSocket;

    public static void main(String[] args) {
        new Window(WIDTH, HEIGHT, "FlappyBird", new Game());
    }

    public synchronized void start(){
        running = true;
        thread = new Thread();
        thread.start();
        run();

    }

    public void init(){
        addKeyListener(new KeyHandler());
        addMouseListener(new MouseHandler());

        img_gameover = GraphicsLoader.loadGraphics("gameover.png");
        background = GraphicsLoader.loadGraphics("background-day.png");
        ground = new Ground();
        bird = new Bird(30, 30, 32,24 );

        button = new Button_(Game.WIDTH / 2 - 80, 130, 156, 87, GraphicsLoader.loadGraphics("Button.png"));
    }

    public void tick(){
        if (!gameover) {
            ObjectHandler.tick();
            ground.tick();
        }
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(background, 0, 0, null);
        ground.render(g);

        ObjectHandler.render(g);

        if (gameover){
            g.drawImage(img_gameover, 50, 50, null);
            Game.button.render(g);
        }

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        init();
        this.requestFocus();

        long pastTime = System.nanoTime();
        double amountOfTickets = 60.0;
        double ns = 1000000000 / amountOfTickets;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - pastTime) / ns;
            pastTime = now;

            while (delta > 0) {
                tick();
                updates++;

                render();
                frames++;

                delta--;
            }
            if (System.currentTimeMillis() - timer > 1000){
                timer+= 1000;
                System.out.println("FPS: " + frames + " | TICKS: " + updates);
                PipeHandler.tick();
                updates = 0;
                frames = 0;
            }
        }
    }
}
