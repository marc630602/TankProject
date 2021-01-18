package com.marc.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {
    public static void main(String[] args) throws InterruptedException {
        //创建窗口
        Frame tf = new TankFrame();
        while (true){
            Thread.sleep(100);
            tf.repaint();
        }
    }
}
