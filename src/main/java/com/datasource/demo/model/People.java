package com.datasource.demo.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Wesley Wang
 * @date 2019/12/16 11:34
 */
@Component
@ConfigurationProperties(prefix = "nl.test")
@Data
@ToString
public class People {
    private String name;
    private String age;
    private String hobby;
    private Integer sex;
}
