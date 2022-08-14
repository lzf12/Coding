package com.util.concurrent.single;

import java.util.Objects;

/**
 * description
 *
 * @version 1.0
 * @Auto: yulong_wang
 * @since 2022-08-14
 **/
public class Singleton {
    // 使用volatile防止执行指令重排序,防止其它线程得到一个未初始化的对象,这样可能会发生空指针和其它数据问题
    private static volatile Singleton singleton = null;

    private Singleton () {

    }

    /**
     * 获取实例对象
     * @return 单实例对象
     */
    public static Singleton getInstance() {
        if (Objects.isNull(singleton)) {
            synchronized (Singleton.class) {
                if (Objects.isNull(singleton)) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
