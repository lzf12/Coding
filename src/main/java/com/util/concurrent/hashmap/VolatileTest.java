package com.util.concurrent.hashmap;

/**
 * description
 *
 * @version 1.0
 * @Auto: yulong_wang
 * @since 2022-08-14
 **/
public class VolatileTest {

    private static volatile boolean  flag = true;

    public static void main(String[] args) {

        new Thread(() -> {
            while(flag) {

            }
           System.out.println("结束");
        }).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始");
        flag = false;
    }
}
