package com.ben.test.plugin;

/**
 * — <br>
 *
 * @author: 刘恒 <br>
 * @date: 2018/11/12 <br>
 */
public class DynamicDataSourceHolder {
    private static final ThreadLocal<DynamicDataSourceGlobal> holder = new ThreadLocal<DynamicDataSourceGlobal>();

    private DynamicDataSourceHolder() {
    }

    public static void putDataSource(DynamicDataSourceGlobal dataSource) {
        holder.set(dataSource);
    }

    public static DynamicDataSourceGlobal getDataSource() {
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }
}