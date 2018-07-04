package com.example.zcc.myapplication.ui.adapter;

import android.content.Context;

import com.example.zcc.myapplication.R;
import com.example.zcc.myapplication.readapter.recyclerview.CommonAdapter;
import com.example.zcc.myapplication.readapter.recyclerview.base.ViewHolder;
import com.example.zcc.myapplication.rxsample.enity.NewsEntity;

import java.util.List;

/**
 * Created by zcc on 2017/3/16.
 */
public class NewsListAdapter extends CommonAdapter<NewsEntity> {
    public NewsListAdapter(Context context, int layoutId, List<NewsEntity> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NewsEntity s, int position) {
          holder.setText(R.id.tv_item_news_titles,s.getText());
          holder.setImagePath(R.id.iv_item_news,s.getProfile_image());
    }

}
