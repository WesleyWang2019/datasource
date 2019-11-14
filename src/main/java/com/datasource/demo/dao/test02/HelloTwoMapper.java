package com.datasource.demo.dao.test02;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Wesley Wang
 * @date 2019/7/31 14:14
 */
@Mapper
@Component
public interface HelloTwoMapper {
    int getCount();
}
