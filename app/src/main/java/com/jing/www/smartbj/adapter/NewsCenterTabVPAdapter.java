package com.jing.www.smartbj.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.jing.www.smartbj.pager.NewsCenterContentTabPager;
import com.jing.www.smartbj.bean.NewsCenterBean;
import com.jing.www.smartbj.utils.Constant;

import java.util.List;

/**
 * Created by Administrator on 2017/2/10.
 */
public class NewsCenterTabVPAdapter extends PagerAdapter {
    //通过构造传入数据 //设置适配器,该ViewPAger要存储Views,beanList数据
        public List<NewsCenterContentTabPager> views;
    private List<NewsCenterBean.NewsCenterNewsTabBean> tabBeenList;

    public NewsCenterTabVPAdapter(List<NewsCenterContentTabPager> views,List<NewsCenterBean.NewsCenterNewsTabBean> tabBeenList) {
        this.views=views;
        this.tabBeenList=tabBeenList;
    }

    @Override
    public int getCount() {

        return views != null?views.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override //将只指定条目的view放入container中
    public Object instantiateItem(ViewGroup container, int position) {
        View view =views.get(position).view;
        container.addView(view);

        //之前 是将头放进去,现在要加载网络的主体内容:调用fragment中的方法
        //通过position得到对应的view,转换成对应的Basepager,即新闻主体的碎片
       NewsCenterContentTabPager tabPager=views.get(position);
        String url = Constant.HOST+tabBeenList.get(position).getUrl();
//        tabPager.loadNetData(url );


        return view;
    }

    @Override  //删除条目
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override  //设置indcater的头
    public CharSequence getPageTitle(int position) {
        return tabBeenList.get(position).getTitle();
    }
}
