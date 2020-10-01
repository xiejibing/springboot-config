package com.xie.transaction.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


@Configuration
@MapperScan(basePackages = "com.xie.transaction.dao.task1.mappers",sqlSessionFactoryRef = "task1SqlSessionFactory")
public class DataSourceConfiguration1 {

    @Bean("task1DataSource")
    @ConfigurationProperties(prefix = "mysql.jdbc.task1")
    public DruidDataSource getDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("task1SqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("task1DataSource") DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/task1/*.xml"));
        return bean.getObject();
    }

    @Bean("task1TransactionManager")
    public DataSourceTransactionManager getTx(@Qualifier("task1DataSource") DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
