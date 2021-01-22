package com.marc.tank;

import java.awt.*;

public class Tank {
    private int x,y;
    private Direction dir = Direction.DOWN;
    private static final int move = 10;
    private static final int TANK_SIZE_WIDTH=50,TANK_SIZE_HEIGHT=50;
    private TankFrame tf = null;
    private boolean moving = false;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Direction dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir){
            case LEFT:
                x -= move;
                break;
            case RIGHT:
                x += move;
                break;
            case DOWN:
                y += move;
                break;
            case UP:
                y -= move;
                break;
            default: break;
        }
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void fire() {
        tf.bulletList.add(new Bullet((int)(this.x+0.3*TANK_SIZE_WIDTH),(int)(this.y+0.3*TANK_SIZE_HEIGHT),this.dir));
    }
}
