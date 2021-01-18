package com.marc.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    int x = 0, y = 0;
    int move = 10;
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
        g.fillRect(x,y,200,200);
        x = x<=990? x+10:0;
        y = y<=990? y+10:0;
    }
}
