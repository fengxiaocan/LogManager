package com.evil.log;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.annotation.IntDef;
import android.util.Log;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.util.Log.ASSERT;

/**
 * The type Log utils.
 *
 * @name： FingerprintLoader
 * @package： com.evil.log
 * @author: Noah.冯 QQ:1066537317
 * @time: 10 :46
 * @version: 1.1
 * @desc： 日志操作工具类
 */
public class LogUtils {
    private LogUtils() {
    }

    /**
     * The constant VERBOSE.
     */
    public static final int VERBOSE = 2;
    /**
     * The constant DEBUG.
     */
    public static final int DEBUG = 3;
    /**
     * The constant INFO.
     */
    public static final int INFO = 4;
    /**
     * The constant WARN.
     */
    public static final int WARN = 5;
    /**
     * The constant ERROR.
     */
    public static final int ERROR = 6;
    /**
     * The constant VERBOSE_COLOR.
     */
    //日志颜色
    static int VERBOSE_COLOR = Color.BLACK;
    /**
     * The Debug color.
     */
    static int DEBUG_COLOR = Color.BLUE;
    /**
     * The Info color.
     */
    static int INFO_COLOR = Color.GRAY;
    /**
     * The Warn color.
     */
    static int WARN_COLOR = Color.YELLOW;
    /**
     * The Error color.
     */
    static int ERROR_COLOR = Color.RED;

    //是否打开打印LOG功能
    private static boolean openLog = true;
    //是否打开写入LOG文件功能
    private static boolean openWrite = false;
    //是否开启在线缓存LOG日志功能
    private static boolean openCache = false;
    /**
     * The constant LOG_FILE_PATH.
     */
    //日志文件保存路径
    static String LOG_FILE_PATH = new File(Environment.getExternalStorageDirectory(), "logs").getAbsolutePath();

    /**
     * Sets max pool size.
     *
     * @param maxPoolSize the max pool size
     */
    public static void setMaxPoolSize(int maxPoolSize) {
        LogPool.getInstance().setMaxPoolSize(maxPoolSize);
    }

    /**
     * Sets max pool size.
     *
     * @param logFilePath the log file path
     */
    public static void setMaxPoolSize(String logFilePath) {
        LOG_FILE_PATH = logFilePath;
    }

    /**
     * Open log.
     */
    public static void openLog() {
        openLog = true;
    }

    /**
     * Close log.
     */
    public static void closeLog() {
        openLog = false;
    }

    /**
     * Close write.
     */
    public static void closeWrite() {
        openWrite = false;
    }

    /**
     * Open write.
     */
    public static void openWrite() {
        openWrite = true;
    }

    /**
     * Close cache.
     */
    public static void closeCache() {
        openCache = false;
    }

    /**
     * Open cache.
     */
    public static void openCache() {
        openCache = true;
    }

    /**
     * Flush.
     */
    public static void flush() {
        if (openWrite) {
            LogPool.getInstance().flush();
        }
    }

    /**
     * 设置日志颜色
     *
     * @param verboseColor the verbose color
     */
    public static void setVerboseColor(int verboseColor) {
        VERBOSE_COLOR = verboseColor;
    }

    /**
     * 设置日志颜色
     *
     * @param debugColor the debug color
     */
    public static void setDebugColor(int debugColor) {
        DEBUG_COLOR = debugColor;
    }

    /**
     * 设置日志颜色
     *
     * @param infoColor the info color
     */
    public static void setInfoColor(int infoColor) {
        INFO_COLOR = infoColor;
    }

    /**
     * 设置日志颜色
     *
     * @param warnColor the warn color
     */
    public static void setWarnColor(int warnColor) {
        WARN_COLOR = warnColor;
    }

    /**
     * 设置日志颜色
     *
     * @param errorColor the error color
     */
    public static void setErrorColor(int errorColor) {
        ERROR_COLOR = errorColor;
    }

    /**
     * Clear pool.
     */
    public static void clearPool() {
        LogPool.getInstance().clear();
    }

    /**
     * Clear cache.
     */
    public static void clearCache() {
        LogCache.getInstance().clear();
    }

    /**
     * V.
     *
     * @param TAG the tag
     * @param msg the msg
     */
    public static void v(String TAG, String msg) {
        if (openLog) {
            Log.v(TAG, msg);
        }
        if (openWrite) {
            LogPool.getInstance().add(LogUtils.VERBOSE, TAG, msg);
        }
        if (openCache) {
            LogCache.getInstance().add(LogUtils.VERBOSE, TAG, msg);
        }
    }

    /**
     * D.
     *
     * @param TAG the tag
     * @param msg the msg
     */
    public static void d(String TAG, String msg) {
        if (openLog) {
            Log.d(TAG, msg);
        }
        if (openWrite) {
            LogPool.getInstance().add(LogUtils.DEBUG, TAG, msg);
        }
        if (openCache) {
            LogCache.getInstance().add(LogUtils.DEBUG, TAG, msg);
        }
    }

    /**
     * .
     *
     * @param TAG the tag
     * @param msg the msg
     */
    public static void i(String TAG, String msg) {
        if (openLog) {
            Log.i(TAG, msg);
        }
        if (openWrite) {
            LogPool.getInstance().add(LogUtils.INFO, TAG, msg);
        }
        if (openCache) {
            LogCache.getInstance().add(LogUtils.INFO, TAG, msg);
        }
    }

    /**
     * W.
     *
     * @param TAG the tag
     * @param msg the msg
     */
    public static void w(String TAG, String msg) {
        if (openLog) {
            Log.w(TAG, msg);
        }
        if (openWrite) {
            LogPool.getInstance().add(LogUtils.WARN, TAG, msg);
        }
        if (openCache) {
            LogCache.getInstance().add(LogUtils.WARN, TAG, msg);
        }
    }

    /**
     * E.
     *
     * @param TAG the tag
     * @param msg the msg
     */
    public static void e(String TAG, String msg) {
        if (openLog) {
            Log.e(TAG, msg);
        }
        if (openWrite) {
            LogPool.getInstance().add(LogUtils.ERROR, TAG, msg);
        }
        if (openCache) {
            LogCache.getInstance().add(LogUtils.ERROR, TAG, msg);
        }
    }

    /**
     * 查看日志
     *
     * @param context the context
     */
    public static void lookLog(Context context){
        context.startActivity(new Intent(context,LogActivity.class));
    }

    /**
     * The interface Log type.
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT})
    public @interface LogType {}
}
