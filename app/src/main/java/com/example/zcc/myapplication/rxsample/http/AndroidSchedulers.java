package com.example.zcc.myapplication.rxsample.http;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * 作者： zcc on 2018/7/3 19:04
 * 邮箱：m15632271759_1@163.com
 */
public class AndroidSchedulers implements Executor {

    private static AndroidSchedulers instance;

    private final Scheduler mMainScheduler;
    private final Handler mHandler;

    private AndroidSchedulers() {
        mHandler = new Handler(Looper.myLooper());
        mMainScheduler = Schedulers.from(this);
    }

    public static synchronized Scheduler mainThread() {
        if (instance == null) {
            instance = new AndroidSchedulers();
        }
        return instance.mMainScheduler;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mHandler.post(command);
    }


}
