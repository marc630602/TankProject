package com.marc.tank;

import java.awt.*;
import java.util.Random;

public class Tank {

    private static final int move = 10;
    public static final int TANK_SIZE_WIDTH=ResourceMgr.badTankL.getWidth(),TANK_SIZE_HEIGHT=ResourceMgr.badTankL.getHeight();

    private int x,y;
    private Direction dir = Direction.DOWN;
    private boolean moving = true;
    private boolean living = true;
    private Group group = Group.BAD;

    private TankFrame tf = null;
    private Random random = new Random();
    Rectangle rect = new Rectangle();

    public Tank(int x, int y, Direction dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = TANK_SIZE_WIDTH;
        rect.height = TANK_SIZE_HEIGHT;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.enemyTanks.remove(this);
        }
        switch (dir){
            case DOWN:
                g.drawImage(group==Group.GOOD?ResourceMgr.goodTtankD:ResourceMgr.badTankD,x,y,null);
                break;
            case UP:
                g.drawImage(group==Group.GOOD?ResourceMgr.goodTtankU:ResourceMgr.badTankU,x,y,null);
                break;
            case LEFT:
                g.drawImage(group==Group.GOOD?ResourceMgr.goodTtankL:ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(group==Group.GOOD?ResourceMgr.goodTtankR:ResourceMgr.badTankR,x,y,null);
                break;
            default:break;
            }
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
        boundCheck();
        if (this.group==Group.BAD && random.nextInt(10) > 7) this.fire();
        if (this.group==Group.BAD && random.nextInt(10) > 7) randomDir();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundCheck() {
        if (x<0) x = 0;
        if (x+TANK_SIZE_WIDTH>TankFrame.GAME_SIZE_WIDTH) x = TankFrame.GAME_SIZE_WIDTH-TANK_SIZE_WIDTH;
        if (y<0) y = 0;
        if (y+TANK_SIZE_HEIGHT>TankFrame.GAME_SIZE_HEIGHT) y = TankFrame.GAME_SIZE_HEIGHT-TANK_SIZE_HEIGHT;
    }

    private void randomDir() {
        this.dir = Direction.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx = this.x + TANK_SIZE_WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + TANK_SIZE_HEIGHT/2 - Bullet.HEIGTH/2;
        tf.bulletList.add(new Bullet(bx,by,this.dir,this.group,tf));
    }

    public void die() {
        this.living = false;
        explode();
    }

    private void explode() {
        int ex = this.x + TANK_SIZE_WIDTH/2 - Explode.WIDTH/2;
        int ey = this.y + TANK_SIZE_HEIGHT/2 - Explode.HEIGTH/2;
        tf.explodes.add(new Explode(ex,ey,tf));
    }
}
