package com.example.zcc.myapplication.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zcc.myapplication.R;
import com.example.zcc.myapplication.readapter.recyclerview.wrapper.LoadMoreWrapper;
import com.example.zcc.myapplication.rxsample.base.ActivityLifeCycleEvent;
import com.example.zcc.myapplication.rxsample.enity.NewsEntity;
import com.example.zcc.myapplication.rxsample.http.Api;
import com.example.zcc.myapplication.rxsample.http.HttpUtil;
import com.example.zcc.myapplication.rxsample.http.ProgressSubscriber;
import com.example.zcc.myapplication.ui.adapter.NewsListAdapter;
import com.example.zcc.myapplication.ui.master.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.product_show_list)
    RecyclerView mProductShowList;
    @BindView(R.id.product_show_refresh)
    SwipeRefreshLayout mProductShowRefresh;
    private LinearLayoutManager mMLinearLayoutManager;
    private NewsListAdapter mInsNewAdapter;
    private Unbinder unbinder;
    private LoadMoreWrapper mLoadMoreWrapper;
    int  page=1;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int setContentView() {
        return R.layout.frag_home;
    }

    @Override
    public void initViews() {
        doGetNetDatas(page);
        // 顶部刷新的样式
        mProductShowRefresh.setColorSchemeResources(android.R.color.holo_green_light);
        mProductShowRefresh.setOnRefreshListener(this);
        mProductShowRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    public void initData() {

    }
    private void showDataList(List<NewsEntity> mList) {
        if (mInsNewAdapter == null) {
            mMLinearLayoutManager = new LinearLayoutManager(getActivity());
            mMLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mProductShowList.setLayoutManager(mMLinearLayoutManager);
            mInsNewAdapter = new NewsListAdapter(getActivity(), R.layout.news_list_item,mList);
            mLoadMoreWrapper = new LoadMoreWrapper(mInsNewAdapter);
            mLoadMoreWrapper.setLoadMoreView(R.layout.load_more);
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    page++;
                    doGetNetDatas(page);
                }
            });
//        mHeaderAndFooterWrapper.addHeaderView(mBanner);
//        mHeaderAndFooterWrapper.addFootView(mFootView);
            mProductShowList.setAdapter(mLoadMoreWrapper);
//        mHeaderAndFooterWrapper.notifyDataSetChanged();
        } else {
            if(page==1){
                mInsNewAdapter.setDatas(mList);
            }else {
                mInsNewAdapter.setData(mList);
            }
        }

    }
    private void doGetNetDatas (int page) {
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
                Log.e("xxxxxxxxxxx",newsEntity.get(0).getProfile_image());
                showDataList(newsEntity);
                mProductShowRefresh.setRefreshing(false);
            }

            @Override
            protected void _onError(String message) {

            }
        }, "cacheKey", ActivityLifeCycleEvent.DESTROY, lifecycleSubject, false, false);
    }

    @Override
    public void onRefresh() {
        page=1;
        doGetNetDatas(page);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
