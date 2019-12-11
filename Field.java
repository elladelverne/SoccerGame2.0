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
    private Goalie gk1;
    private Goalie gk2;

    public Field() {
        super();
        player1 = new Player1(800, 600);
        player2 = new Player2(800, 600);
        ball = new Ball(800, 600);
        goal1 = new Goal(800,300);
        goal2 = new Goal(0, 300);
        gk1 = new Goalie(10, 300);
        gk2 = new Goalie (785, 300);
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
        gk1.draw(g);
        gk2.draw(g);
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {         
            player1.update();
            player2.update();
            ball.update();
            goal1.update();
            goal2.update();
            gk1.update();
            gk2.update();
            checkCollisions();
            checkBoundaries();
            repaint();
        }
    }
    private void checkBoundaries(){
        if(ball.getX()<=0){
            ball.setX(0);
            ball.setVx(5);
        }
        if(ball.getX()>=800){
            ball.setX(800);
            ball.setVx(- 5);
        }
        if(ball.getY()<=0){
            ball.setY(0);
            ball.setVy(5);
        }
        if(ball.getY()>=600){
            ball.setY(600);
            ball.setVy(-5);
        }
        if(player1.getX()<=0){
            player1.setX(0);
        }
        if(player1.getX()>=750){
            player1.setX(750);
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
        if(player2.getX()>=750){
            player2.setX(750);
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
            ball.setVx(player2.getVx());
            ball.setVy(player2.getVy());
            if(player2.getVx()==0 && player2.getVy()==0){
                ball.stop();
            }
        }
        if(ball.getBounds().intersects(goal1.getBounds())){
            player1.setScore(player1.getScore()+1);
            restart(800,400);
            System.out.println("player 1:" + player1.getScore());
        }
        if(ball.getBounds().intersects(goal2.getBounds())){
            player2.setScore(player2.getScore()+1);
            restart(800,400);
            System.out.println("player 2:" + player2.getScore());
        }
        if(ball.getBounds().intersects(gk1.getBounds())){
            ball.setVy(0);
            ball.setVx(5);
        }
        if(ball.getBounds().intersects(gk2.getBounds())){
            ball.setVx(-5);
            ball.setVy(0);
        }
    }

    private void restart(int cWidth, int cHeight){
        if(ball.getBounds().intersects(goal1.getBounds())||ball.getBounds().intersects(goal2.getBounds())){
            ball.setX(425);
            ball.setY(200);
            player1.setX(1);
            player2.setX(cWidth - 30);
            player1.setY(200);
            player2.setY(200);
            ball.stop();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player2.move("right");
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
           player2.move("left");
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player2.move("up");
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player2.move("down");
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player1.move("right");
        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
           player1.move("left");
        }
        else if (e.getKeyCode() == KeyEvent.VK_W) {
            player1.move("up");
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            player1.move("down");
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player2.stop();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player2.stop();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player2.stop();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player2.stop();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player1.stop();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_A) {
           player1.stop();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player1.stop();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player1.stop();
        }

    }
}