package com.example.zcc.myapplication.ui.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.zcc.myapplication.R;
import com.example.zcc.myapplication.readapter.recyclerview.CommonAdapter;
import com.example.zcc.myapplication.readapter.recyclerview.base.ViewHolder;
import com.example.zcc.myapplication.ui.constant.VideoConstant;
import com.example.zcc.myapplication.ui.entity.VideoEntity;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by zcc on 2017/3/16.
 */
public class StoryVideoAdapter extends CommonAdapter<VideoEntity> {


    public StoryVideoAdapter(Context context, int layoutId, List<VideoEntity> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, VideoEntity s, int position) {
//          holder.setImagePath(R.id.image_item,s.getTop_commentsHeader());
        JZVideoPlayerStandard jzVideoPlayerStandard = holder.getView(R.id.videoplayer);
        jzVideoPlayerStandard.setUp(VideoConstant.videoUrlList[position%VideoConstant.videoUrlList.length],
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                s.getText());
       Glide.with(mContext).load(s.getTop_commentsHeader()).centerCrop().into(jzVideoPlayerStandard.thumbImageView);
    }
}
