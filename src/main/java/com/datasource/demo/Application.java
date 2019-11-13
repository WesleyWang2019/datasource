package com.datasource.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

/**
 * @author Wesley Wang
 * @date 2019/6/18 11:13
 */

@SpringBootApplication  // 关闭数据源自动配置
//@Import({DynamicCacheRegister.class})  // 动态注册缓存实现
@EnableTransactionManagement  // 开启事务管理
public class Application {
    public static void main(String[] args) {
        SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
        SpringApplication.run(Application.class, args);
    }
}
