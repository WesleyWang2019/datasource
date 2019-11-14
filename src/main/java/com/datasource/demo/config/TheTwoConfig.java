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
@MapperScan(basePackages = TheTwoConfig.PACKAGE, sqlSessionFactoryRef = "domestic2SqlSessionFactory")
public class TheTwoConfig {
    static final String PACKAGE = "com.datasource.demo.dao.test02";
    static final String MAPPER_LOCATION = "classpath:mapper/test02/*.xml";

    @ConfigurationProperties("spring.datasource.druid.test02")
    @Bean(name = "domestic2DataSource")
    public DataSource domestic2DataSource() {
        return com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "domestic2TransactionManager")
    public DataSourceTransactionManager domestic2TransactionManager() {
        return new DataSourceTransactionManager(domestic2DataSource());
    }

    @Bean(name = "domestic2SqlSessionFactory")
    public SqlSessionFactory abroadSqlSessionFactory(@Qualifier("domestic2DataSource") DataSource domestic2DataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(domestic2DataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(TheTwoConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
