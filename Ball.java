/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccergame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author 801621
 */
public class Ball {
    private int height, width, x, y, vx, vy;
    private Rectangle bounds;
    private Color color;
    private boolean goal;
    
public Ball(int cWidth, int cHeight) {
        this.goal = true;
        this.x = cWidth / 2;
        this.y = cHeight / 2;
        this.vx = 0;
        this.vy = 0;
        this.width = 25;
        this.height = 25;
        this.color = Color.GRAY;
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
}
    public boolean isGoal() {
        return goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

public void draw(Graphics g){
        g.setColor(this.color);
        Graphics2D g2d = (Graphics2D)g;
        g2d.draw(bounds);
        g.fillOval(x, y, width, height);
    }

public void update(){
        this.x += vx;
        this.y += vy;
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
    }
public void stop() {
        this.vx = 0;
        this.vy = 0;
    }
}

