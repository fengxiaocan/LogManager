package com.evil.log;

import android.text.format.DateFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

/**
 * @name： FingerprintLoader
 * @package： com.evil.log
 * @author: Noah.冯 QQ:1066537317
 * @time: 10:54
 * @version: 1.1
 * @desc： TODO
 */

class LogPool {
    private LinkedList<LogInfo> mLogPool;
    //最大缓存数量
    private static int sMaxPoolSize = 1000;

    private static final LogPool ourInstance = new LogPool();


    public static LogPool getInstance() {
        return ourInstance;
    }

    private LogPool() {
        mLogPool = new LinkedList<>();
    }

    public static void setMaxPoolSize(int maxPoolSize) {
        sMaxPoolSize = maxPoolSize;
    }


    void add(@LogUtils.LogType int type, String tag, String msg) {
        mLogPool.add(new LogInfo(type, tag, msg));
        if (mLogPool.size() >= sMaxPoolSize) {
            flush();
        }
    }

    public void flush() {
        final LinkedList<LogInfo> cloneList;
        synchronized (LogPool.class) {
            cloneList = (LinkedList<LogInfo>) mLogPool.clone();
            mLogPool.clear();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LogPool.class) {
                    try {
                        CharSequence format = DateFormat.format("yy_MM_dd_HH_mm.txt", System.currentTimeMillis());
                        File file = new File(LogUtils.LOG_FILE_PATH, format.toString());
                        File parentFile = file.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        PrintStream pw = new PrintStream(new FileOutputStream(file,true),true);
                        for (LogInfo info : cloneList) {
                            pw.print(info.time);
                            pw.print(" ");
                            pw.print(info.getType());
                            pw.print(" ");
                            pw.print(info.TAG);
                            pw.print(":");
                            pw.print(info.log);
                            pw.println();
                        }
                        pw.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void clear(){
        synchronized (LogPool.class) {
            mLogPool.clear();
        }
    }
}
