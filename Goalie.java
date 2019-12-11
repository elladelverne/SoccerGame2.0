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
public class Goalie {
    private int height, width, x, y, vy, vx;
    private Rectangle bounds;
    private Color color;
    private final int SPEED = 5;
    
public Goalie(int x, int cHeight) {
        this.x = x;
        this.y = cHeight/2;
        this.vy = SPEED;
        this.vx = 0;
        this.width = 15;
        this.height = 30;
        this.color = Color.GRAY;
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
    
    public void move() {        
        if(y == 150){
            vy = SPEED;
        }
        else if(y == 285){
            vy = -SPEED;
        }
    }
    
    public void draw(Graphics g) {
        g.setColor(this.color);
        Graphics2D g2d = (Graphics2D) g;
        g2d.fill(bounds);
    }
    public void update() {
        move();
        this.y += vy;
        this.bounds = new Rectangle(this.x, this.y, this.width, this.height);
    }
}
