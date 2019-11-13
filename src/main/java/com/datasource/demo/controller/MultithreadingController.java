package com.datasource.demo.controller;

import com.datasource.demo.service.MultithreadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Wesley Wang
 * @date 2019/9/11 16:51
 * 多线程测试
 */
@Controller
public class MultithreadingController {
    @Autowired
    private MultithreadingService multithreadingService;

}
