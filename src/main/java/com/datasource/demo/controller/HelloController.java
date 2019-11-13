package com.datasource.demo.controller;

import com.datasource.demo.service.HelloMainService;
import com.datasource.demo.service.HelloOneService;
import com.datasource.demo.service.HelloTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wesley Wang
 * @date 2019/7/30 21:16
 */
@RestController
@RequestMapping("/test")
public class HelloController {
    @Autowired
    private HelloMainService helloMainService;
    @Autowired
    private HelloOneService helloOneService;
    @Autowired
    private HelloTwoService helloTwoService;

    @GetMapping("/t")
    public String getHello() {
        return "hello world";
    }

    @GetMapping("/main")
    public int getMain() {
        return helloMainService.getHello();
    }

    @GetMapping("/one")
    public int getOne() {
        return helloOneService.getHello();
    }

    @GetMapping("/two")
    public int getTwo() {
        return helloTwoService.getHello();
    }


}
