package com.zga.viewpage;

/**
 * Created by kzLee on 2016/7/29
 */

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    public interface pagerOnClickListener {
        void pagerListener(View v);
    }

    pagerOnClickListener clickListener;

    public void setpagerOnClickListener(pagerOnClickListener clickListeners) {
        clickListener = clickListeners;
    }

    //界面列表
    private List<View> views;


    public ViewPagerAdapter(List<View> views) {
        this.views = views;
    }

    //销毁arg1位置的界面
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }

    @Override
    public void finishUpdate(View arg0) {
        // TODO Auto-generated method stub

    }

    //获得当前界面数
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }

        return 0;
    }


    //初始化arg1位置的界面
    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(views.get(arg1), 0);
        if (arg1 == 2) {
            views.get(arg1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.pagerListener(v);
                }
            });
        }
        return views.get(arg1);
    }

    //判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public Parcelable saveState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
        // TODO Auto-generated method stub

    }

}
