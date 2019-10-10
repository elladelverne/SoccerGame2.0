/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccergame;

import java.awt.Color;
import java.awt.Graphics;
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
    private Ball ball;
    private Goal goal1;
    private Goal goal2;

    public Field() {
        super();
        player1 = new Player1(800, 600);
        player2 = new Player2(800, 600);
        ball = new Ball(800, 600);
        goal1 = new Goal(800,300);
        goal2 = new Goal(0, 300);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/12);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        if (player1.isAlive()) player1.draw(g);
        if (player2.isAlive()) player2.draw(g);
        if (ball.isGoal()) ball.draw(g);
        goal1.draw(g);
        goal2.draw(g);
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {         
            player1.update();
            player2.update();
            ball.update();
            goal1.update();
            goal2.update();
            checkCollisions();
            checkBoundaries();
            chaseBall();
            repaint();
        }
    }
    private void checkBoundaries(){
        if(ball.getX()<=0){
            ball.setX(0);
        }
        if(ball.getX()>=800){
            ball.setX(800);
        }
        if(ball.getY()<=0){
            ball.setY(0);
        }
        if(ball.getY()>=600){
            ball.setY(600);
        }
        if(player1.getX()<=0){
            player1.setX(0);
        }
        if(player1.getX()>=800){
            player1.setX(800);
        }
        if(player1.getY()<=0){
            player1.setY(0);
        }
        if(player1.getY()>=600){
            player1.setY(600);
        }
         if(player2.getX()<=0){
            player2.setX(0);
        }
        if(player2.getX()>=800){
            player2.setX(800);
        }
        if(player2.getY()<=0){
            player2.setY(0);
        }
        if(player2.getY()>=600){
            player2.setY(600);
        }
    }
    private void checkCollisions(){
        if(ball.isGoal()&& player1.getBounds().intersects(ball.getBounds())){
            ball.setVx(player1.getVx());
            ball.setVy(player1.getVy());
            if(player1.getVx()==0 && player1.getVy()==0){
                ball.stop();
            }
        }
        if(ball.isGoal()&& player2.getBounds().intersects(ball.getBounds())){
            if(goal2.getX()> player2.getX()){
                if (goal2.getX()< player2.getX()){
                    player2.setX(player2.getX()-3);
                }
                if (goal2.getY()> player2.getY()){
                    player2.setY(player2.getY()+3);
                }
                if (goal2.getY()< player2.getY()){
                    player2.setY(player2.getY()-3);
                }
            }
            player2.setVx(-3);
            ball.setVx(player2.getVx());
            player2.setVy(-3);
            ball.setVy(player2.getVy());
        }
        if(ball.getBounds().intersects(goal1.getBounds())){
            player1.setScore(player1.getScore()+1);
            System.out.println("player:" + player1.getScore());
        }
        if(ball.getBounds().intersects(goal2.getBounds())){
            player2.setScore(player2.getScore()+1);
            System.out.println("computer:" + player2.getScore());
        }
    }
    
    public void chaseBall(){
        if (ball.getX()> player2.getX()){
            player2.setX(player2.getX()+3);
        }
        if (ball.getX()< player2.getX()){
            player2.setX(player2.getX()-3);
        }
        if (ball.getY()> player2.getY()){
            player2.setY(player2.getY()+3);
        }
        if (ball.getY()< player2.getY()){
            player2.setY(player2.getY()-3);
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