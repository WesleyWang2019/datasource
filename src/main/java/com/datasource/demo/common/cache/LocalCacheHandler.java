package com.datasource.demo.common.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Wesley Wang
 * @date 2020/3/24 13:35
 */
@Slf4j
@Component
public class LocalCacheHandler<T> {
    //默认容量
    private static int DEFAULT_CAPACITY = 2 << 10;
    //最大容量
    private static int MAX_CAPACITY = 2 << 16;
    //刷新缓存频率
    private static int MONITOR_DURATION = 2;

    private ReentrantLock lock = new ReentrantLock();

    //使用默认容器创建一个map
    private static ConcurrentHashMap<String, LocalCacheEntity> cache = new ConcurrentHashMap<>(DEFAULT_CAPACITY);

    static {
        new Thread(new TimeoutTimerThread()).start();
    }

    static class TimeoutTimerThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.MINUTES.sleep(MONITOR_DURATION);
                    checkTime();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //检查key是否过期
        private void checkTime() {
            for (String key : cache.keySet()) {
                LocalCacheEntity entity = cache.get(key);
                long expireTime = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - entity.getGmtModify());
                if (entity.getExpire() > expireTime) {
                    continue;
                }
                cache.remove(key);
            }
        }
    }


    //存方法
    private boolean putCloneValue(String key, Object value, int expireTime) {
        try {
            if (cache.size() >= MAX_CAPACITY) {
                return false;
            }
            LocalCacheEntity<Object> entity = clone(new LocalCacheEntity<>(value, System.nanoTime(), expireTime));
            cache.put(key, entity);
            return true;
        } catch (Exception e) {
            log.error("local cache putCloneValue error. msg={}", e.getMessage(), e);
        }
        return false;
    }

    private <T extends Serializable> T clone(T object) {
        T cloneObject = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(object);
            oos.close();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            cloneObject = (T) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            log.error("local cache clone error. msg={}", e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            log.error("local cache clone error. msg={}", e.getMessage(), e);
        }
        return cloneObject;
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @return
     */
    public T getValue(String key) {
        if (cache.contains(key)) {
            LocalCacheEntity<T> localCacheEntity = cache.get(key);
            if (null != localCacheEntity) {
                return localCacheEntity.getValue();
            }
        }
        return null;
    }

    public void clear() {
        cache.clear();
    }

    public void remove(String key) {
        try {
            lock.lock();
            if (cache.contains(key)) {
                cache.remove(key);
            }
        } catch (Exception e) {
            log.info("remove local cache error. key={}", key, e);
        } finally {
            lock.unlock();
        }
    }

    public void removeFromRedisTopic(String key) {
        remove(key);
    }

}
