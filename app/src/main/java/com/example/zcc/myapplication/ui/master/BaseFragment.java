package com.example.zcc.myapplication.ui.master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zcc.myapplication.rxsample.base.ActivityLifeCycleEvent;
import com.example.zcc.myapplication.rxsample.base.BaseActivity;

import butterknife.ButterKnife;
import rx.subjects.PublishSubject;

/**
 * Created by Administrator on 2016/3/9.
 */
public abstract class BaseFragment<T> extends Fragment {
    public BaseFragment mBaseFragmentSelf;
    public String mTag;
    public BaseActivity mBaseActivity;
    public LayoutInflater mLayoutInflater;
    public FragmentManager mFragmentManager;
    public View mContentView;
    public int mStatusBar;
    public LinearLayoutManager mLinearLayoutManager;
    public LinearLayoutManager mLayoutManager;

    public void setStatusBar(int statusBar) {
        mStatusBar = statusBar;
    }
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();

    public BaseFragment() {
        mTag = this.getClass().getSimpleName() + System.currentTimeMillis();
        mBaseFragmentSelf = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBaseActivity = (BaseActivity) this.getActivity();
        mLinearLayoutManager = new LinearLayoutManager(mBaseActivity);
        mLayoutInflater = mBaseActivity.getLayoutInflater();
        mLayoutManager = mLinearLayoutManager;
        mFragmentManager = this.getFragmentManager();
        mContentView = mLayoutInflater.inflate(setContentView(), container, false);
        ButterKnife.bind(this, mContentView);
        initViews();
        initData();
        return mContentView;
    }

    //设置要渲染的View---fragment呈现的View
    public abstract int setContentView();

    public abstract void initViews();

    public abstract void initData();

    public void killSelf() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.remove(this);
        mFragmentManager.popBackStackImmediate();
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //跳转到其他Activity的操作
    public void startActivity(Class ActivityClass) {
        Intent intent = new Intent(mBaseActivity, ActivityClass);
        this.startActivity(intent);
    }

    //跳转到其他ActivityForRS的操作
    public void startActivityForResult(Class ActivityClass, int reqCode) {
        Intent intent = new Intent(mBaseActivity, ActivityClass);
        this.startActivityForResult(intent, reqCode);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public <T extends View> T getView(int srcId) {
        View view = null;
        if (view == null) {
            view = mContentView.findViewById(srcId);
        }
        return (T) view;
    }

}
