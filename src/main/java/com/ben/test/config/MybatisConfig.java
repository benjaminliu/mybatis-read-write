package com.ben.test.config;

import com.ben.test.plugin.DynamicDataSourcePlugin;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * — <br>
 *
 * @author: 刘恒 <br>
 * @date: 2018/11/12 <br>
 */
@Configuration
public class MybatisConfig implements InitializingBean {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private DynamicDataSourcePlugin dynamicDataSourcePlugin;

    @Bean
    public DynamicDataSourcePlugin dynamicPluginInterceptor() {
        DynamicDataSourcePlugin plugin = new DynamicDataSourcePlugin();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        plugin.setProperties(properties);
        return plugin;
    }


    //plugin interceptor
    @Override
    public void afterPropertiesSet() throws Exception {
        Iterator var3 = this.sqlSessionFactoryList.iterator();

        while (var3.hasNext()) {
            SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) var3.next();
            sqlSessionFactory.getConfiguration().addInterceptor(dynamicDataSourcePlugin);
        }
    }
}
