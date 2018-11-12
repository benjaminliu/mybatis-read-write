package com.ben.test.plugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * — <br>
 *
 * @author: 刘恒 <br>
 * @date: 2018/11/12 <br>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {

        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();

        if (dynamicDataSourceGlobal == null || dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE) {
            logger.info("使用 [Write] datasource");
            return DynamicDataSourceGlobal.WRITE.name();
        }
        logger.info("使用 [Read] datasource");
        return DynamicDataSourceGlobal.READ.name();
    }
}
