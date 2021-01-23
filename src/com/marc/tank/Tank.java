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
        switch (dir){
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;

        }

        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir){
            case LEFT:
                if (x-move >= 0) x -= move;
                break;
            case RIGHT:
                if (x+move+TANK_SIZE_WIDTH < TankFrame.GAME_SIZE_WIDTH) x += move;
                break;
            case DOWN:
                if (y+move+TANK_SIZE_HEIGHT < TankFrame.GAME_SIZE_HEIGHT) y += move;
                break;
            case UP:
                if (y-move-TANK_SIZE_HEIGHT >= 0) y -= move;
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
        tf.bulletList.add(new Bullet((int)(this.x+0.3*TANK_SIZE_WIDTH),(int)(this.y+0.3*TANK_SIZE_HEIGHT),this.dir,tf));
    }
}
