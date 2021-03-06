package com.datasource.demo.controller;

import com.datasource.demo.model.People;
import com.datasource.demo.service.HelloMainService;
import com.datasource.demo.service.HelloOneService;
import com.datasource.demo.service.HelloTwoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wesley Wang
 * @date 2019/7/30 21:16
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class HelloController {
    @Autowired
    private HelloMainService helloMainService;
    @Autowired
    private HelloOneService helloOneService;
    @Autowired
    private HelloTwoService helloTwoService;
    @Autowired
    private People people;


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


    @GetMapping("/con")
    public void testConfig() {
        People people = this.people;
        System.out.println(people);
    }


}
