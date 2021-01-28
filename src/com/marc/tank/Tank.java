package com.marc.tank;

import java.awt.*;
import java.util.Random;

public class Tank {

    private static final int move = PropertiesMgr.getInt("tankSpeed");;
    public static final int tankSizeWidth = ResourceMgr.badTankL.getWidth();
    public static final int tankSizeHeight = ResourceMgr.badTankL.getHeight();

    private int x,y;
    private Direction dir = Direction.DOWN;
    private boolean moving = true;
    private boolean living = true;
    private Group group = Group.BAD;

    private TankFrame tf = null;
    private Random random = new Random();
    Rectangle rect = new Rectangle();
    FireStrategy fs;

    public Tank(int x, int y, Direction dir, Group group, TankFrame tf)  {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = tankSizeWidth;
        rect.height = tankSizeHeight;

        if (group == Group.GOOD){
            String goodFS = PropertiesMgr.getString("goodFS");
            try {
                fs = (FireStrategy) Class.forName(goodFS).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (group == Group.BAD){
            fs = new DefaultFireStrategy();
        }
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

    public TankFrame getTf() { return tf; }

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
        if (x+ tankSizeWidth >TankFrame.gameSizeWidth) x = TankFrame.gameSizeWidth - tankSizeWidth;
        if (y<0) y = 0;
        if (y+ tankSizeHeight >TankFrame.gameSizeHeight) y = TankFrame.gameSizeHeight - tankSizeHeight;
    }

    private void randomDir() {
        this.dir = Direction.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }

    public void die() {
        this.living = false;
        explode();
    }

    private void explode() {
        int ex = this.x + tankSizeWidth /2 - Explode.WIDTH/2;
        int ey = this.y + tankSizeHeight /2 - Explode.HEIGTH/2;
        tf.explodes.add(new Explode(ex,ey,tf));
    }
}
