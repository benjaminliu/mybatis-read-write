package com.ben.test.config;

import com.ben.test.plugin.DynamicDataSource;
import com.ben.test.plugin.DynamicDataSourceGlobal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * — <br>
 *
 * @author: 刘恒 <br>
 * @date: 2018/11/12 <br>
 */
@Configuration
public class DatabaseConfig {
    private static Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean(name = "momentWriteDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.write")
    public DataSource momentWriteDataSource() {
        logger.info("-------------------- write DataSource init ---------------------");
        return DataSourceBuilder.create().build();
    }

    /**
     * mysql-momentRead 数据源
     */
    @Bean(name = "momentReadDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.read")
    public DataSource momentReadDataSource() {
        logger.info("-------------------- read DataSource init ---------------------");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DataSource(@Qualifier("momentWriteDataSource") DataSource momentWriteDataSource, @Qualifier("momentReadDataSource") DataSource momentReadDataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DynamicDataSourceGlobal.READ.name(), momentReadDataSource);
        targetDataSource.put(DynamicDataSourceGlobal.WRITE.name(), momentWriteDataSource);

        // 传入数据源map，AbstractRoutingDataSource将以key来分配数据源
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(momentWriteDataSource());
        return dataSource;
    }
}
