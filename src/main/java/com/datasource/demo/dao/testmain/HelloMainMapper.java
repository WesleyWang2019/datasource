package com.datasource.demo.dao.testmain;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * @author Wesley Wang
 * @date 2019/7/31 14:13
 */
@Mapper
@Component
public interface HelloMainMapper {
    public int getCount();
}
