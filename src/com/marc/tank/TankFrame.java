package com.marc.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    Tank myTank = new Tank(500,500,Direction.DOWN);
    Bullet bullet = new Bullet(550,550,Direction.DOWN);
    public TankFrame() throws HeadlessException {
        //创建窗口
        this.setSize(1000,1000);
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

    //窗口被改变时候调用的方法
    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        bullet.paint(g);
    }
}
