package com.jing.www.smartbj.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jing.www.smartbj.R;
import com.jing.www.smartbj.adapter.MenuAdapter;
import com.jing.www.smartbj.adapter.TabVpAdapter;
import com.jing.www.smartbj.base.BaseFragment;
import com.jing.www.smartbj.base.BaseLoadNetDataOperator;
import com.jing.www.smartbj.fragment.GovaffairsFragment;
import com.jing.www.smartbj.fragment.HomeTabFragment;
import com.jing.www.smartbj.fragment.NewsCenterFragment;
import com.jing.www.smartbj.fragment.SettingTabFragment;
import com.jing.www.smartbj.fragment.SmartServiceTabFragment;
import com.jing.www.smartbj.bean.NewsCenterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/5.
 */

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private ViewPager vp;
    private RadioButton rb_home;
    private RadioButton rb_newscenter;
    private RadioButton rb_smartservice;
    private RadioButton rb_govaffairs;
    private RadioButton rb_setting;
    private RadioGroup rg_tab;
    private List<Fragment> fragments;
    public SlidingMenu slidingMenu;

    public List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList;
    private MenuAdapter adapter;

    public void setNewsCenterMenuBeanList(List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList) {
        this.newsCenterMenuBeanList = newsCenterMenuBeanList;
        initRecycleView();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initEvent();
        initViewPager();
        initSlidingMenu();

    }

    private void initRecycleView() {
        //1.初始化..侧滑菜单
        RecyclerView rv_menu = (RecyclerView) slidingMenu.findViewById(R.id.rv_menu);
        //2.找到布局管理器
        rv_menu.setLayoutManager(new LinearLayoutManager(this));
        //3.添加适配器添加数据
        adapter = new MenuAdapter(this, newsCenterMenuBeanList);
        rv_menu.setAdapter(adapter);
    }
        private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        rg_tab = (RadioGroup) findViewById(R.id.rg);
        rb_home = (RadioButton) findViewById(R.id.rb_tab_home);
        rb_newscenter = (RadioButton) findViewById(R.id.rb_tab_newscenter);
        rb_smartservice = (RadioButton) findViewById(R.id.rb_tab_smartservice);
        rb_govaffairs = (RadioButton) findViewById(R.id.rb_tab_govaffairs);
        rb_setting = (RadioButton) findViewById(R.id.rb_tab_setting);
    }

    private void initEvent() {
        rg_tab.setOnCheckedChangeListener(this);
    }

    private void initViewPager() {
        fragments = new ArrayList<>();

        fragments.add(new HomeTabFragment());
        fragments.add(new NewsCenterFragment());
        fragments.add(new SmartServiceTabFragment());
        fragments.add(new GovaffairsFragment());
        fragments.add(new SettingTabFragment());

        vp.setAdapter(new TabVpAdapter(getSupportFragmentManager(), fragments));

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int item = 0;
        switch (checkedId) {
            case R.id.rb_tab_home:
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                item = 0;
                break;
            case R.id.rb_tab_newscenter:
                item = 1;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

                break;
            case R.id.rb_tab_smartservice:
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                item = 2;
                break;
            case R.id.rb_tab_govaffairs:
                item = 3;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;

            case R.id.rb_tab_setting:
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                item = 4;
                break;


        }
        vp.setCurrentItem(item, false);

        //获取子fragment
        BaseFragment itemFragment = (BaseFragment) fragments.get(item);
        if (itemFragment instanceof BaseLoadNetDataOperator) {
            //调用子fragment的下载数据
            ((BaseLoadNetDataOperator) itemFragment).loadNetData();
        }
    }


    private void initSlidingMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindWidth(150);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.sliding_menu_activity);
    }

    //接口回调独立方法
    public BaseFragment getCurrentTabFragment(){
        int currentItem=vp.getCurrentItem();//1.利用容器获取当前fragment的id
       BaseFragment baseFragment = (BaseFragment) fragments.get(currentItem); //2.利用集合获取当前fragment
        return baseFragment;
    }

}

