package com.jing.www.smartbj.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.jing.www.smartbj.R;
import com.jing.www.smartbj.activity.MainActivity;
import com.jing.www.smartbj.adapter.NewsCenterTabVPAdapter;
import com.jing.www.smartbj.base.BaseFragment;
import com.jing.www.smartbj.base.BaseLoadNetDataOperator;
import com.jing.www.smartbj.pager.NewsCenterContentTabPager;
import com.jing.www.smartbj.bean.NewsCenterBean;
import com.jing.www.smartbj.bean.NewsCenterTabBean;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/5.
 */

public class NewsCenterFragment extends BaseFragment implements BaseLoadNetDataOperator {


    private NewsCenterBean newsCenterBean;
    private Context context;
    private NewsCenterTabBean  newsCenterTabBean;
    private ImageButton ibNext;
    private TabPageIndicator tabPagerIndicator;
    private ViewPager vpNewscenterContent;
    private List<NewsCenterContentTabPager> views;

    @Override
    public void initTitle() {
        setMenu(true);
        setTitle("新闻中心");
        setType(false);

    }

    @Override
    public View createContent() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.newscenter_content, (ViewGroup) getView(), false);
        tabPagerIndicator = (TabPageIndicator) view.findViewById(R.id.tabPagerIndicator);
        ibNext = (ImageButton) view.findViewById(R.id.ib_next);
        vpNewscenterContent = (ViewPager) view.findViewById(R.id.vp_newscenter_content);

        //初始化viewPager
        initViewPager();
        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = vpNewscenterContent.getCurrentItem();
                if(currentItem != newsCenterBean.data.get(0).children.size()-1){
                    vpNewscenterContent.setCurrentItem(currentItem+1);
                }
            }
        });
        return view;
    }

    private void initViewPager() {

        views = new ArrayList();
        for(NewsCenterBean.NewsCenterNewsTabBean tabBean:newsCenterBean.data.get(0).children){
            NewsCenterContentTabPager tabPager =new NewsCenterContentTabPager(getContext());
            views.add(tabPager);
        }

        NewsCenterTabVPAdapter newsCenterTabVPAdapter =new NewsCenterTabVPAdapter(views,newsCenterBean.data.get(0).children);
        vpNewscenterContent.setAdapter(newsCenterTabVPAdapter);
        //indicater与viewPager联合
        tabPagerIndicator.setViewPager(vpNewscenterContent);
        //第一个调用时机
        views.get(0).startSwitch();
        tabPagerIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < views.size(); i++) {
                   NewsCenterContentTabPager newsCenterContentTabPager =views.get(i);
                    if(position == i){
                        newsCenterContentTabPager.startSwitch();
                    }else{
                        newsCenterContentTabPager.stopSwitch();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void loadNetData() {
//        final String url = Constant.NEWSCENTER_URL;
//
//        OkHttpUtils
//                .get()
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        MyToast.show(getActivity(), "网络连接失败");
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        MyToast.show(getActivity(), "网络连接成功");
//                        ProcessData(response);
//                        //渲染的是本fragment的布局
//                        View view = createContent();
//                        addView(view);
//                    }
//                });


    }

    public List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeenList;

    private void ProcessData(String json) {
        Gson gson = new Gson();
        //转换成模型对象用bean接受
        newsCenterBean = gson.fromJson(json, NewsCenterBean.class);
        //因为是要给slidingmenu添加数据,所以需要让MainAcyicvity来操作,获取碎片所在的activity
        //传递数据给Acyivity
        ((MainActivity) getActivity()).setNewsCenterMenuBeanList(newsCenterBean.data);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
