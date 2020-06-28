package com.datasource.demo.util;

/**
 * @author 石玉森
 * @create 2019-10-24 15:05
 **/

public class CacheKeyUtil {

    /**
     * 获取引擎的缓存key
     *
     * @param key
     * @return
     */
    public static String getDSKey(String key) {
        String cacheKey = "TEST" + key;
        return cacheKey;
    }

}
