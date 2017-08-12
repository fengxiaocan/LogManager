package com.evil.log;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * @name： FingerprintLoader
 * @package： com.evil.log
 * @author: Noah.冯 QQ:1066537317
 * @time: 14:36
 * @version: 1.1
 * @desc： 查看日志的界面
 */

public class LogActivity extends AppCompatActivity {
    private ListView mLv;
    private LinkedList<LogInfo> mLogCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        initView();
    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.lv);
        mLogCache = LogCache.getInstance().getLogCache();
        mLv.setAdapter(mAdapter);
    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (mLogCache != null) {
                return mLogCache.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LogInfo info = mLogCache.get(position);
            if (convertView == null) {
                convertView = View.inflate(LogActivity.this, R.layout.item_log, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.tv_content);
            tv.setText(info.toStrings());
            switch (info.type) {
                case LogUtils.VERBOSE:
                    tv.setTextColor(LogUtils.VERBOSE_COLOR);
                    break;
                case LogUtils.DEBUG:
                    tv.setTextColor(LogUtils.DEBUG_COLOR);
                    break;
                case LogUtils.INFO:
                    tv.setTextColor(LogUtils.INFO_COLOR);
                    break;
                case LogUtils.WARN:
                    tv.setTextColor(LogUtils.WARN_COLOR);
                    break;
                case LogUtils.ERROR:
                    tv.setTextColor(LogUtils.ERROR_COLOR);
                    break;
                default:
                    tv.setTextColor(Color.BLACK);
                    break;
            }
            return convertView;
        }
    };
}
