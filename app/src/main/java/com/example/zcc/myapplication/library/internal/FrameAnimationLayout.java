package com.example.zcc.myapplication.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.zcc.myapplication.R;
import com.example.zcc.myapplication.library.PullToRefreshBase;


/**
 * Package_name:com.handmark.pulltorefresh.library.internal
 * Author:zhaoQiang
 * Email:zhao_hero@163.com
 * Date:2016/11/28  19:34
 *
 * 帧动画  实现加载自定义的动画   实现的是帧动画
 */
public class FrameAnimationLayout extends LoadingLayout {

    private AnimationDrawable mAnimationDrawable;
    private Animation mAnim;

    public FrameAnimationLayout(Context context, PullToRefreshBase.Mode mode,
                                PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);

        mHeaderImage.setImageResource(R.drawable.ptr_animation);
        mAnimationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.ptr_animation;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
        System.out.println("FrameAnimationLayout.onPullImpl");

    }

    @Override
    protected void pullToRefreshImpl() {
        System.out.println("FrameAnimationLayout.pullToRefreshImpl");
        mAnim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_thank_you_tyrant);
        mHeaderImage.setAnimation(mAnim);
    }

    @Override
    protected void refreshingImpl() {
        mAnimationDrawable.start();//开启动画
    }

    @Override
    protected void releaseToRefreshImpl() {
        System.out.println("FrameAnimationLayout.releaseToRefreshImpl");
    }

    @Override
    protected void resetImpl() {
        System.out.println("FrameAnimationLayout.resetImpl");
    }
}
