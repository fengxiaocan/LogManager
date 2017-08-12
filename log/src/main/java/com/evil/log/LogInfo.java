package com.evil.log;

import android.text.format.DateFormat;

/**
 * @name： FingerprintLoader
 * @package： com.evil.log
 * @author: Noah.冯 QQ:1066537317
 * @time: 12:23
 * @version: 1.1
 * @desc： TODO
 */

public class LogInfo {
    public static final String FORMAT_TYPE = "yyyy.MM.dd_HH:mm:ss:SSS";
    @LogUtils.LogType
    int type;
    String TAG;
    String log;
    String time;

    public LogInfo() {
        this.time = DateFormat.format(FORMAT_TYPE, System.currentTimeMillis()).toString();
    }

    public LogInfo(int type, String TAG, String log) {
        this.type = type;
        this.TAG = TAG;
        this.log = log;
        this.time = DateFormat.format(FORMAT_TYPE, System.currentTimeMillis()).toString();
    }


    public String getType() {
        switch (type) {
            case LogUtils.VERBOSE:
                return "V";
            case LogUtils.DEBUG:
                return "D";
            case LogUtils.INFO:
                return "I";
            case LogUtils.WARN:
                return "W";
            case LogUtils.ERROR:
                return "E";
        }
        return "V";
    }

    @Override
    public String toString() {
        return time + " " + TAG + " " + getType() + ": " + log;
    }

    public String toStrings() {
        return time  + " " + TAG + ": " + log;
    }
}
