/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccergame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author 801621
 */
public class Field extends JPanel {
    private Timer timer;
    private Player1 player1;
    private Player2 player2;


    public Field() {
        super();
        player1 = new Player1(800, 600);
        player2 = new Player2(800, 600);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/12);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        if (player1.isAlive()) player1.draw(g);
        if (player2.isAlive()) player2.draw(g);
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {         
            player1.update();
            player2.update();
            
            repaint();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player1.move("right");
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
           player1.move("left");
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player1.move("up");
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player1.move("down");
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player1.stop();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player1.stop();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player1.stop();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player1.stop();
        }

    }
}