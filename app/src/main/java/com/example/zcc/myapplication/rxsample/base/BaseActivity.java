package com.example.zcc.myapplication.rxsample.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zcc.myapplication.R;
import com.example.zcc.myapplication.rxsample.util.AppTools;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import rx.subjects.PublishSubject;


/**
 * Created by helin on 2016/11/11 10:41.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public BaseActivity mActivitySelf;
    public FragmentManager mFragmentManager;
    public LayoutInflater mLayoutInflater;
    public static String sUserId;
    public static List<String> ids = new ArrayList<>();
    private String mFid;
    public ImageView mImgvLeft;
    public TextView mTvLeft;
    public TextView mTvCenter;
    public ImageView mImgvCenter;
    public ImageView mImgvRight;
    public TextView mTvRight;
    public View mTitleBar;
    public int mStatusBarHeight;
    public LinearLayoutManager mLayoutManager;

    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();


    //    @NonNull
//    public <T> Observable.Transformer<T, T> bindUntilEvent(@NonNull final ActivityLifeCycleEvent event) {
//        return new Observable.Transformer<T, T>() {
//            @Override
//            public Observable<T> call(Observable<T> sourceObservable) {
//                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
//                        lifecycleSubject.takeFirst(new Func1<ActivityLifeCycleEvent, Boolean>() {
//                            @Override
//                            public Boolean call(ActivityLifeCycleEvent activityLifeCycleEvent) {
//                                return activityLifeCycleEvent.equals(event);
//                            }
//                        });
//                return sourceObservable.takeUntil(compareLifecycleObservable);
//            }
//        };
//    }
//获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        super.onCreate(savedInstanceState);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#00000000"));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        mStatusBarHeight = getStatusBarHeight(this);
        mLayoutManager = new LinearLayoutManager(mActivitySelf);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTitleBar = getLayoutInflater().inflate(R.layout.titile_bar, null);
        mTitleBar.setVisibility(View.GONE);
        initTitleBar(mTitleBar);
        mActivitySelf = this;
        mFragmentManager = this.getSupportFragmentManager();
        mLayoutInflater = this.getLayoutInflater();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.addView(mTitleBar);
        View inflate = mLayoutInflater.inflate(setActivityContentView(), null);
        linearLayout.addView(inflate);
        setContentView(linearLayout);
//        ButterKnife.bind(this);
//        initBga(mBgaRefreshLayout);
        initViews();
        initDatas();
    }
    public abstract int setActivityContentView();
    public abstract void initViews();

    public abstract void initDatas();
    public void initTitleBar(View titleBar) {
        mImgvLeft = (ImageView) titleBar.findViewById(R.id.imgv_left);
        mTvLeft = (TextView) titleBar.findViewById(R.id.tv_left);
        mTvCenter = (TextView) titleBar.findViewById(R.id.tv_center);
        mImgvCenter = (ImageView) titleBar.findViewById(R.id.imgv_center);
        mImgvRight = (ImageView) titleBar.findViewById(R.id.imgv_right);
        mTvRight = (TextView) titleBar.findViewById(R.id.tv_right);
    }
    public void showTitleBar(boolean isShowLeft, String strTitle, String strRight, View.OnClickListener rightOnclick){
        mTitleBar.setVisibility(View.VISIBLE);
        if(isShowLeft){
            mImgvLeft.setImageResource(R.drawable.fanhui);
        }
        if(AppTools.checkStringNoNull(strTitle)){
            mTvCenter.setText(strTitle);
        }
        if(AppTools.checkStringNoNull(strRight)){
            mTvRight.setText(strRight);
        }
        if(rightOnclick!=null){
            mTvRight.setOnClickListener(rightOnclick);
        }
    }
    public void showTitleBar(boolean isShowLeft, String strTitle){
        showTitleBar(isShowLeft,strTitle,null,null);
    }


    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
    }
}
