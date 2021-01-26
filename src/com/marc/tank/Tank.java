package com.marc.tank;

import java.awt.*;

public class Tank {
    private int x,y;
    private Direction dir = Direction.DOWN;
    private static final int move = 10;
    public static final int TANK_SIZE_WIDTH=ResourceMgr.tankD.getWidth(),TANK_SIZE_HEIGHT=ResourceMgr.tankD.getHeight();
    private TankFrame tf = null;
    private boolean moving = false;
    private boolean living = true;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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

    public Tank(int x, int y, Direction dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!living) tf.enemyTanks.remove(this);
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
            default:break;
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
        int bx = this.x + TANK_SIZE_WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + TANK_SIZE_HEIGHT/2 - Bullet.HEIGTH/2;
        tf.bulletList.add(new Bullet(bx,by,this.dir,tf));
    }

    public void die() {
        this.living = false;
    }
}
