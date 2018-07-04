package com.example.zcc.myapplication.ui.adapter;

import android.content.Context;

import com.example.zcc.myapplication.readapter.recyclerview.CommonAdapter;
import com.example.zcc.myapplication.readapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by zcc on 2017/3/16.
 */
public class TextNewAdapter extends CommonAdapter<String> {
    public TextNewAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {

    }

}
