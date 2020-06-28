package com.datasource.demo.common.cache;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wesley Wang
 * @date 2020/3/24 13:43
 */
@Data
public class LocalCacheEntity<T> implements Serializable {
    //值
    private T value;
    //时间戳
    private Long gmtModify;
    //过期时间
    private int expire;

    public LocalCacheEntity(T value, Long gmtModify, int expire) {
        this.value = value;
        this.gmtModify = gmtModify;
        this.expire = expire;
    }

    public LocalCacheEntity() {
    }
}
