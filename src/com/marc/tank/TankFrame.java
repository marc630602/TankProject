package com.marc.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankFrame extends Frame {
    Tank myTank = new Tank(500,500,Direction.DOWN,this);
    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> enemyTanks = new ArrayList<>();
    static final int GAME_SIZE_WIDTH = 1000, GAME_SIZE_HEIGHT = 1000;
    Random random = new Random();

    public TankFrame() throws HeadlessException {
        //创建窗口
        this.setSize(GAME_SIZE_WIDTH,GAME_SIZE_HEIGHT);
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
//                    case KeyEvent.VK_SPACE:
//                        enemyTanks.add(new Tank(random.nextInt(GAME_SIZE_WIDTH),random.nextInt(GAME_SIZE_HEIGHT),Direction.DOWN,new TankFrame()));
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
            offScreenImage = this.createImage(GAME_SIZE_WIDTH,GAME_SIZE_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,GAME_SIZE_WIDTH,GAME_SIZE_HEIGHT);
        graphics.setColor(c);
        paint(graphics);
        g.drawImage(offScreenImage,0,0,null);
    }

    //窗口被改变时候调用的方法
    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        for (int i = 0;i<bulletList.size();i++){
            bulletList.get(i).paint(g);
        }
        for (int j =0;j<enemyTanks.size();j++){
            enemyTanks.get(j).paint(g);
        }
    }
}
