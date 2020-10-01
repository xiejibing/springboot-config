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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
//basePackages：扫描对该数据库操作的接口所在的包,以后调用该接口就会使用这个数据源；
//sqlSessionFactoryRef:sql会话工厂
@MapperScan(basePackages = "com.xie.transaction.dao.task.mappers", sqlSessionFactoryRef = "taskSqlSessionFactory")
public class DataSourceConfiguration {

    @Bean("taskDataSource")
    @ConfigurationProperties(prefix = "mysql.jdbc.task")
    //通过我们在配置文件配置的url,username,password,driver映射为DataSource对象
    public DruidDataSource getDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("taskSqlSessionFactory")
    public SqlSessionFactory getSqlsessionFacctory(@Qualifier("taskDataSource") DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //加载会话操作具体需要的xml文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/task/*.xml"));
        return bean.getObject();
    }

    @Bean("taskTransactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("taskDataSource") DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
