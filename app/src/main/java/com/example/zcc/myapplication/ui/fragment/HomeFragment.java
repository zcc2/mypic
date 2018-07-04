package com.example.zcc.myapplication.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zcc.myapplication.R;
import com.example.zcc.myapplication.readapter.recyclerview.wrapper.LoadMoreWrapper;
import com.example.zcc.myapplication.rxsample.base.ActivityLifeCycleEvent;
import com.example.zcc.myapplication.rxsample.http.Api;
import com.example.zcc.myapplication.rxsample.http.HttpUtil;
import com.example.zcc.myapplication.rxsample.http.ProgressSubscriber;
import com.example.zcc.myapplication.ui.adapter.MeiTuListAdapter;
import com.example.zcc.myapplication.ui.entity.MeiTuBean;
import com.example.zcc.myapplication.ui.master.BaseFragment;

import java.util.List;

import butterknife.BindView;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.product_show_list)
    RecyclerView mProductShowList;
    @BindView(R.id.product_show_refresh)
    SwipeRefreshLayout mProductShowRefresh;
    private LinearLayoutManager mMLinearLayoutManager;
    private MeiTuListAdapter mMeiTuListAdapter;
    private int  page=1;
    private LoadMoreWrapper mLoadMoreWrapper;

    public HomeFragment() {
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
    private void showDataList(List<MeiTuBean> mList) {
        if (mMeiTuListAdapter == null) {
            StaggeredGridLayoutManager recyclerViewLayoutManager =
                    new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mProductShowList.setLayoutManager(recyclerViewLayoutManager);
            mMeiTuListAdapter = new MeiTuListAdapter(getActivity(), R.layout.home_meitu_item,mList);
            mLoadMoreWrapper = new LoadMoreWrapper(mMeiTuListAdapter);
            mLoadMoreWrapper.setLoadMoreView(R.layout.load_more);
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    page++;
//                AppTools.toast("加载更多");
                    doGetNetDatas(page);
                }
            });
//        mHeaderAndFooterWrapper.addHeaderView(mBanner);
//        mHeaderAndFooterWrapper.addFootView(mFootView);
            mProductShowList.setAdapter(mLoadMoreWrapper);
//        mHeaderAndFooterWrapper.notifyDataSetChanged();
        } else {
            if(page ==1){
                mMeiTuListAdapter.setDatas(mList);
            }else {
                Log.e("xxxxxxxxxx","xxxxxxxxx");
                mMeiTuListAdapter.setData(mList);
            }
        }

    }
    private void doGetNetDatas (int page) {
        Observable ob = Api.getDefault().getMeiTu(page);
        //嵌套请求
//        ob.flatMap(new Func1<String, Observable<HttpResult<Subject>>>() {
//
//            @Override
//            public Observable<HttpResult<Subject>> call(String s) {
//                return Api.getDefault().getUser("aa");
//            }
//        });


        HttpUtil.getInstance().toSubscribe(ob, new ProgressSubscriber<List<MeiTuBean>>(getActivity()) {

            @Override
            protected void _onNext(List<MeiTuBean> newsEntity) {
               showDataList(newsEntity);
               if(mProductShowRefresh.isRefreshing()){
                   mProductShowRefresh.setRefreshing(false);
               }
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
}
