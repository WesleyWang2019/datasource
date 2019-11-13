package com.datasource.demo.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 * @author Wesley Wang
 * @date 2019/11/12 17:40
 */
public class Test {
    public static void main(String[] args) {
        SMap.map.put(1,1);
        CSMap.getInstance().put(1,1);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            SMap.map.put(1,1);
            SMap.map.get(1);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("get instance directly:" + (l2 - l) );

        for (int i = 0; i < 10000000; i++) {
            CSMap.getInstance().put(1,1);
            CSMap.getInstance().get(1);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("get instance directly:" + (l3 - l2) );

    }
}

class CSMap {
    public static class Instance {
        public static final ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
    }

    public static Map<Integer, Integer> getInstance() {
        return Instance.map;
    }
}


class SMap {
    public static final Map<Integer, Integer> map = new ConcurrentHashMap<>();
}
