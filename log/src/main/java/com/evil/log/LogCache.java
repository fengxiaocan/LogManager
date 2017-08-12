package com.evil.log;

import java.util.LinkedList;

/**
 * @name： FingerprintLoader
 * @package： com.evil.log
 * @author: Noah.冯 QQ:1066537317
 * @time: 10:54
 * @version: 1.1
 * @desc： 缓存
 */

class LogCache {
    private LinkedList<LogInfo> mLogCache;
    //最大缓存数量
    private static int sMaxCacheSize = 1000;

    private static final LogCache ourInstance = new LogCache();


    public static LogCache getInstance() {
        return ourInstance;
    }

    private LogCache() {
        mLogCache = new LinkedList<>();
    }

    public static void setMaxCacheSize(int maxPoolSize) {
        sMaxCacheSize = maxPoolSize;
    }

    public LinkedList<LogInfo> getLogCache() {
        return mLogCache;
    }

    void add(@LogUtils.LogType int type, String tag, String msg) {
        mLogCache.add(new LogInfo(type, tag, msg));
        if (mLogCache.size() > sMaxCacheSize) {
            mLogCache.removeFirst();
        }
    }

    public void clear(){
        synchronized (LogCache.class) {
            mLogCache.clear();
        }
    }
}
