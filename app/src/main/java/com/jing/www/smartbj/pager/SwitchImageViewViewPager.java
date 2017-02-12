package com.jing.www.smartbj.pager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.jing.www.smartbj.utils.MyToast;

/**
 * Created by Administrator on 2017/2/12.
 * 自定义轮播图的切换自定控件
 * 控制轮播图的开始和结束
 * 处理轮播图的点击事件
 */

public class SwitchImageViewViewPager extends ViewPager {
    //控制轮播图开始和停止的对象
    private NewsCenterContentTabPager tabPager;
    private float startX;
    private float startY;

    public SwitchImageViewViewPager(Context context) {
        super(context);
    }

    public SwitchImageViewViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTabPager(NewsCenterContentTabPager tabPager) {
        this.tabPager = tabPager;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            //按下:停止
            //移动:停止
            //抬起:切换
            case MotionEvent.ACTION_DOWN:
                tabPager.stopSwitch();
                startX = ev.getX();
                startY = ev.getY();

                break;
            //右移不打开侧滑,事件分发不拦截
            case MotionEvent.ACTION_MOVE:
                tabPager.stopSwitch();
                float moveX = ev.getX();
                float moveY = ev.getY();
                int disX= (int) (moveX- startX);
                int disY = (int) (moveY- startY);
                if (Math.abs(disX)>Math.abs(disY) && moveX>startX){
                    requestDisallowInterceptTouchEvent(true);
                }
                break;
            //原点抬起判断为点击事件
            case MotionEvent.ACTION_UP:
                float upX=ev.getX();
                float upY=ev.getY();
                if(startX==upX&& startY==upY){
                    //点击事件 TODO
                    MyToast.show(getContext(),"点击事件");
                }
                tabPager.startSwitch();
                break;

        }

        return super.dispatchTouchEvent(ev);
    }
}
