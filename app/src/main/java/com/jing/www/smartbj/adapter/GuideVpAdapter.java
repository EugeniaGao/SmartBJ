package com.jing.www.smartbj.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/5.
 */
public class GuideVpAdapter extends PagerAdapter {
    private List<ImageView> views;

    public GuideVpAdapter(List<ImageView> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //不明所以的方法
        return view == object;
    }

    //实例化条目,也就是填充数据
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    //销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
