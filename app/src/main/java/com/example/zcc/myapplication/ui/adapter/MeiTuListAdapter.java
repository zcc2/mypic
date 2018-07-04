package com.example.zcc.myapplication.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zcc.myapplication.R;
import com.example.zcc.myapplication.readapter.recyclerview.CommonAdapter;
import com.example.zcc.myapplication.readapter.recyclerview.base.ViewHolder;
import com.example.zcc.myapplication.ui.entity.MeiTuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcc on 2017/3/16.
 */
public class MeiTuListAdapter extends CommonAdapter<MeiTuBean> {

    private ArrayList<Integer> mHeight;
    public MeiTuListAdapter(Context context, int layoutId, List<MeiTuBean> datas) {
        super(context, layoutId, datas);
        mHeight =new ArrayList<>();
        for (int i = 0; i <= datas.size(); i++) {
            //依次给给图片设置宽高
            mHeight.add((int)(300+Math.random()*400));
        }
    }

    @Override
    protected void convert(ViewHolder holder, MeiTuBean s, int position) {
          holder.setText(R.id.name_item,"美女"+position);
          holder.setImagePath(R.id.image_item,s.getUrl());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ImageView imageview=holder.getView(R.id.image_item);
        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) imageview.getLayoutParams();
        //设置高
        params.height= mHeight.get(position%mHeight.size());
        imageview.setLayoutParams(params);
    }
}
