package com.kcode.zhihudaily.utils;

/**
 * Created by caik on 2016/10/29.
 */

public class LogFactory {
    public final static int DEBUG = 0;
    public final static int INFO = 1;
    public final static int WARN = 2;
    public final static int ERROR = 3;
    public final static int NOTHING = 4;

    private static L log;

    /**
     * 初始化日志等级
     * {@link LogFactory#DEBUG}
     * {@link LogFactory#INFO}
     * {@link LogFactory#WARN}
     * {@link LogFactory#ERROR}
     * {@link LogFactory#NOTHING}
     */
    public static void initLogLevel(int level){
        L log = create(LogFactory.class);
        log.setLevel(level);
    }

    public static L create(Class<?> cls){
        if (log == null){
            log = new L();
        }
        log.setTag(cls.getSimpleName());
        return log;
    }

    private LogFactory() {
    }

}
