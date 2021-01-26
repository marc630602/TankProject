package com.marc.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //创建窗口
        TankFrame tf = new TankFrame();
        //初始化敌方坦克
//        for (int i=0;i<5;i++){
//            tf.enemyTanks.add(new Tank(50+i*100,200,Direction.DOWN,tf));
//        }

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
