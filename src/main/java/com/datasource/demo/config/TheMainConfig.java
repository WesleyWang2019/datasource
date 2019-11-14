package com.datasource.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Wesley Wang
 * @date 2019/7/31 14:15
 */
@Configuration
@MapperScan(basePackages = TheMainConfig.PACKAGE, sqlSessionFactoryRef = "abroadSqlSessionFactory")
public class TheMainConfig {
    static final String PACKAGE = "com.datasource.demo.dao.testmain";
    static final String MAPPER_LOCATION = "classpath:mapper/testmain/*.xml";

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.druid.initial-size}")
    private int initialSize;

    @Value("${spring.datasource.druid.min-idle}")
    private  int minIdle;

    @Value("${spring.datasource.druid.max-active}")
    private  int maxActive;

    @Value("${spring.datasource.druid.max-wait}")
    private  int maxWait;

    @Bean(name = "abroadDataSource")
    @Primary
    public DataSource abroadDataSource() {
        return DruidDataSourceBuilder.newInstance()
                .appendDriverClassName(driverClassName)
                .appendUrl(url)
                .appendUsername(username)
                .appendPassword(password)
                .appendInitialSize(initialSize)
                .appendMinIdle(minIdle)
                .appendMaxActive(maxActive)
                .appendMaxWait(maxWait)
                .getDataSource();
    }

    @Bean(name = "abroadTransactionManager")
    @Primary
    public DataSourceTransactionManager abroadTransactionManager() {
        return new DataSourceTransactionManager(abroadDataSource());
    }

    @Bean(name = "abroadSqlSessionFactory")
    @Primary
    public SqlSessionFactory abroadSqlSessionFactory(@Qualifier("abroadDataSource") DataSource abroadDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(abroadDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(TheMainConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
