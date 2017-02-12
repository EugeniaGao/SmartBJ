package com.jing.www.smartbj.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jing.www.smartbj.bean.NewsCenterTabBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/12.
 * 轮播图图片适配器
 */
public class SwitchImgVPAdapter extends PagerAdapter{
    private List<ImageView>imageViews;
    private  List<NewsCenterTabBean.TopNewsBean>topNewsBeanList;

    public SwitchImgVPAdapter(List<ImageView> imageViews, List<NewsCenterTabBean.TopNewsBean> topNewsBeanList) {
        this.imageViews = imageViews;
        this.topNewsBeanList = topNewsBeanList;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      ImageView imageView= imageViews.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
    }
}
