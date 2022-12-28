package com.util.concurrent.hashmap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description
 *
 * @version 1.0
 * @Auto: yulong_wang
 * @since 2022-07-30
 **/
public class MapTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
        Map<String, Object> map = new ConcurrentHashMap<>();

    }
}
