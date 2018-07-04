package com.example.zcc.myapplication;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * 作者： zcc on 2018/7/3 19:02
 * 邮箱：m15632271759_1@163.com
 */
public class MyApplication extends Application{
    private static MyApplication instance;

    public static MyApplication getInstance() {
        if (instance == null) {
            synchronized (MyApplication.class) {
                if (instance == null) {
                    instance = new MyApplication();
                }
            }
        }
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
