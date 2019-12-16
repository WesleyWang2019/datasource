package com.datasource.demo.dao.test01;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Wesley Wang
 * @date 2019/7/31 14:13
 */

@Mapper
@Component
public interface HelloOneMapper {
    int getCount();
}
