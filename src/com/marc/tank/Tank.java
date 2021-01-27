package com.marc.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Direction dir = Direction.DOWN;
    private static final int move = 10;
    public static final int TANK_SIZE_WIDTH=ResourceMgr.badTankL.getWidth(),TANK_SIZE_HEIGHT=ResourceMgr.badTankL.getHeight();
    private TankFrame tf = null;
    private boolean moving = false;
    private boolean living = true;
    private Random random = new Random();
    private Group group = Group.BAD;
    private Explode explode = null;

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

    public Tank(int x, int y, Direction dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.enemyTanks.remove(this);
            explode.paint(g);
        }
        if (group == Group.GOOD){
            switch (dir){
                case DOWN:
                    g.drawImage(ResourceMgr.goodTtankD,x,y,null);
                    break;
                case UP:
                    g.drawImage(ResourceMgr.goodTtankU,x,y,null);
                    break;
                case LEFT:
                    g.drawImage(ResourceMgr.goodTtankL,x,y,null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgr.goodTtankR,x,y,null);
                    break;
                default:break;
            }
        } else if (group == Group.BAD){
            switch (dir){
                case DOWN:
                    g.drawImage(ResourceMgr.badTankD,x,y,null);
                    break;
                case UP:
                    g.drawImage(ResourceMgr.badTankU,x,y,null);
                    break;
                case LEFT:
                    g.drawImage(ResourceMgr.badTankL,x,y,null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgr.badTankR,x,y,null);
                    break;
                default:break;
            }
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

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void fire() {
        int bx = this.x + TANK_SIZE_WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + TANK_SIZE_HEIGHT/2 - Bullet.HEIGTH/2;
        tf.bulletList.add(new Bullet(bx,by,this.dir,this.group,tf));
    }

    public void die() {
        this.living = false;
        int ex = this.x + TANK_SIZE_WIDTH/2 - Explode.WIDTH/2;
        int ey = this.y + TANK_SIZE_HEIGHT/2 - Explode.HEIGTH/2;
        tf.explodes.add(new Explode(ex,ey,tf));
    }
}
