package com.datasource.demo.service;

import com.datasource.demo.dao.testmain.HelloMainMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Wesley Wang
 * @date 2019/7/30 21:18
 */
@Service
@Slf4j
public class HelloMainService {
    @Resource
    private HelloMainMapper helloMainMapper;


    public int getHello() {
        return helloMainMapper.getCount();
    }
}
