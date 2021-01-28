package com.marc.tank;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        TankFrame tf = new TankFrame();
        Random random = new Random();

        //初始化敌机
        int initTankCount = PropertiesMgr.getInt("initTankCount");
        for (int i = 0; i < initTankCount; i++) {
            tf.enemyTanks.add(new Tank(random.nextInt(tf.getWidth()-Tank.tankSizeWidth),random.nextInt(tf.getHeight()-Tank.tankSizeHeight),Direction.values()[random.nextInt(4)],Group.BAD,tf));
        }

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }


    }
}
