package com.datasource.demo.dao.testmain;

import org.mybatis.spring.annotation.MapperScan;

/**
 * @author Wesley Wang
 * @date 2019/7/31 14:13
 */
@MapperScan
public interface HelloMainMapper {
    public int getCount();
}
