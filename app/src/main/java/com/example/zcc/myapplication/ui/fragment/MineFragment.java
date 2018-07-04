package com.example.zcc.myapplication.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zcc.myapplication.R;
import com.example.zcc.myapplication.rxsample.base.ActivityLifeCycleEvent;
import com.example.zcc.myapplication.rxsample.enity.NewsEntity;
import com.example.zcc.myapplication.rxsample.http.Api;
import com.example.zcc.myapplication.rxsample.http.HttpUtil;
import com.example.zcc.myapplication.rxsample.http.ProgressSubscriber;
import com.example.zcc.myapplication.ui.master.BaseFragment;

import java.util.List;

import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


    public MineFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int setContentView() {
        return R.layout.frag_mine;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }
    private void doGetNetDatas () {
        Observable ob = Api.getDefault().getNews("1", 1);
        //嵌套请求
//        ob.flatMap(new Func1<String, Observable<HttpResult<Subject>>>() {
//
//            @Override
//            public Observable<HttpResult<Subject>> call(String s) {
//                return Api.getDefault().getUser("aa");
//            }
//        });


        HttpUtil.getInstance().toSubscribe(ob, new ProgressSubscriber<List<NewsEntity>>(getActivity()) {

            @Override
            protected void _onNext(List<NewsEntity> newsEntity) {
                Log.e("newentity=====", newsEntity.toString());
            }

            @Override
            protected void _onError(String message) {

            }
        }, "cacheKey", ActivityLifeCycleEvent.DESTROY, lifecycleSubject, false, false);
    }

}
