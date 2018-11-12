package com.ben.test.config;

import com.ben.test.plugin.DynamicDataSourcePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * — <br>
 *
 * @author: 刘恒 <br>
 * @date: 2018/11/12 <br>
 */
@Configuration
public class MybatisConfig {
    @Bean
    public DynamicDataSourcePlugin dynamicPluginInterceptor() {
        DynamicDataSourcePlugin plugin = new DynamicDataSourcePlugin();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        plugin.setProperties(properties);
        return plugin;
    }
}
