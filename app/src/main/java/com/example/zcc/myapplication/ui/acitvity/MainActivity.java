package com.example.zcc.myapplication.ui.acitvity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zcc.myapplication.R;
import com.example.zcc.myapplication.rxsample.base.BaseActivity;
import com.example.zcc.myapplication.ui.fragment.HomeFragment;
import com.example.zcc.myapplication.ui.fragment.MineFragment;
import com.example.zcc.myapplication.ui.fragment.NewsFragment;
import com.example.zcc.myapplication.ui.fragment.StoryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;

    private List<Fragment> list=new ArrayList<>();
    private MyAdapter adapter;
    private String[] titles = {"首页", "新闻", "段子", "我的"};
    private int images[] = {R.drawable.home_selector, R.drawable.news_selector, R.drawable.story_selector, R.drawable.mine_selector};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setActivityContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        ButterKnife.bind(this);
        list.add(new HomeFragment());
        list.add(new NewsFragment());
        list.add(new StoryFragment());
        list.add(new MineFragment());
        adapter = new MyAdapter(getSupportFragmentManager(), this);
        mViewpager.setAdapter(adapter);
        //绑定
        mTablayout.setupWithViewPager(mViewpager);
        //设置自定义视图
        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
    }

    @Override
    public void initDatas() {

    }

    class MyAdapter extends FragmentStatePagerAdapter {

        private Context context;

        public MyAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * 自定义方法，提供自定义Tab
         *
         * @param position 位置
         * @return 返回Tab的View
         */
        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.tab_custom, null);
            TextView textView = (TextView) v.findViewById(R.id.tv_title);
            ImageView imageView = (ImageView) v.findViewById(R.id.iv_icon);
            textView.setText(titles[position]);
            imageView.setImageResource(images[position]);
            //添加一行，设置颜色
            textView.setTextColor(mTablayout.getTabTextColors());//
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }
}
