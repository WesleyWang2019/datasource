package com.datasource.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Wesley Wang
 * @date 2019/7/31 14:15
 */
@Configuration
@MapperScan(basePackages = TheOneConfig.PACKAGE, sqlSessionFactoryRef = "domestic1SqlSessionFactory")
public class TheOneConfig {
    static final String PACKAGE = "com.datasource.demo.dao.test01";
    static final String MAPPER_LOCATION = "classpath:mapper/test01/*.xml";

    @Bean(name = "domestic1DataSource")
    @ConfigurationProperties("spring.datasource.druid.test01")
    public DataSource domestic1DataSource() {
        return com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "domestic1TransactionManager")
    public DataSourceTransactionManager domestic1TransactionManager() {
        return new DataSourceTransactionManager(domestic1DataSource());
    }

    @Bean(name = "domestic1SqlSessionFactory")
    public SqlSessionFactory abroadSqlSessionFactory(@Qualifier("domestic1DataSource") DataSource domestic1DataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(domestic1DataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(TheOneConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
