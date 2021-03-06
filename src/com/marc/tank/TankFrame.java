package com.marc.tank;

import java.awt.*;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankFrame extends Frame {
    static final int gameSizeWidth = PropertiesMgr.getInt("gameSizeWidth");
    static final int gameSizeHeight = PropertiesMgr.getInt("gameSizeHeight");

    Random random = new Random();

    Tank myTank = new Tank(random.nextInt(gameSizeWidth -Tank.tankSizeWidth), random.nextInt(gameSizeHeight -Tank.tankSizeHeight),Direction.DOWN,Group.GOOD,this);
    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> enemyTanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public TankFrame() throws HeadlessException {
        //创建窗口
        this.setSize(gameSizeWidth, gameSizeHeight);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("好玩的坦克大战");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            boolean bL = false;
            boolean bR = false;
            boolean bU = false;
            boolean bD = false;
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_LEFT :
                        bL = true;
                        break;
                    case KeyEvent.VK_RIGHT :
                        bR = true;
                        break;
                    case KeyEvent.VK_UP :
                        bU = true;
                        break;
                    case KeyEvent.VK_DOWN :
                        bD = true;
                        break;
                    case KeyEvent.VK_A:
                        enemyTanks.add(new Tank(random.nextInt(gameSizeWidth -Tank.tankSizeWidth),random.nextInt(gameSizeHeight -Tank.tankSizeHeight),Direction.DOWN,Group.BAD,TankFrame.this));
                    default:break;
                }
                setMainTankDirection();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_LEFT :
                        bL = false;
                        break;
                    case KeyEvent.VK_RIGHT :
                        bR = false;
                        break;
                    case KeyEvent.VK_UP :
                        bU = false;
                        break;
                    case KeyEvent.VK_DOWN :
                        bD = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        myTank.fire();
                        break;
                    default:break;
                }
                setMainTankDirection();
            }

            private void setMainTankDirection() {
                if(!bL && !bU && !bR && !bD) {
                    myTank.setMoving(false);
                } else {
                    myTank.setMoving(true);
                    if (bR) myTank.setDir(Direction.RIGHT);
                    if (bL) myTank.setDir(Direction.LEFT);
                    if (bU) myTank.setDir(Direction.UP);
                    if (bD) myTank.setDir(Direction.DOWN);
                }
            }
        });
    }
    //双缓冲解决闪烁
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage== null){
            offScreenImage = this.createImage(gameSizeWidth, gameSizeHeight);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, gameSizeWidth, gameSizeHeight);
        graphics.setColor(c);
        paint(graphics);
        g.drawImage(offScreenImage,0,0,null);
    }

    //窗口被改变时候调用的方法
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量：" +bulletList.size(),10,50);
        g.drawString("敌机数量："+enemyTanks.size(),10,100);
        g.setColor(c);
        myTank.paint(g);
        for (Bullet bullet : bulletList) {
            bullet.paint(g);
        }

        for (Tank enemyTank : enemyTanks) {
            enemyTank.paint(g);
        }

        for (Explode explode : explodes) {
            explode.paint(g);
        }

        for (int i=0;i<bulletList.size();i++){
            for (int j=0;j<enemyTanks.size();j++){
                bulletList.get(i).colliedWith(enemyTanks.get(j));
            }
        }
    }
}
