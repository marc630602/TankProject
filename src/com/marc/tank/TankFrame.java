package com.marc.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    int x = 500, y = 500;
    private static final int move = 10;
    Direction dir = Direction.DOWN;
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

            private void setMainTankDirection() {
                if (bR) dir = Direction.RIGHT;
                if (bL) dir = Direction.LEFT;
                if (bU) dir = Direction.UP;
                if (bD) dir = Direction.DOWN;
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
            }
        });
    }

    //窗口被改变时候调用的方法
    @Override
    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
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
            default:
                break;
        }
    }
}
