package com.jing.www.smartbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jing.www.smartbj.R;
import com.jing.www.smartbj.adapter.GuideVpAdapter;
import com.jing.www.smartbj.utils.Constant;
import com.jing.www.smartbj.utils.SPUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/5.
 *存放fragment的容器,使用ViewPager来完成
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager vp;
    private List<ImageView> views;
    private int[] imgs={R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    private Button btStart;
    private LinearLayout containerGrayPoint;
    private View redPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();

        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initVp();
        vp.addOnPageChangeListener(this);
        initGrayPoint();
    }



    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        btStart = (Button) findViewById(R.id.btn_guide);
        containerGrayPoint = (LinearLayout) findViewById(R.id.container_gray_point);
        redPoint = findViewById(R.id.red_point);
        btStart.setOnClickListener(this);
    }
    //动态创建灰色的点
    private void initGrayPoint() {
        for(int resId:imgs) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.point_gray_bg);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
            params.rightMargin=20;
            containerGrayPoint.addView(view,params);
        }
    }
    //初始化ViewPager的数据,即初始化fragment图片
    private void initVp() {
        views = new ArrayList<>();
        for(int resId:imgs){
          //因为imgs中存放的是数字,需要转换成对象
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(resId);
              views.add(iv);
        }
        //给ViewPager绑定适配器
        vp.setAdapter(new GuideVpAdapter(views) );

    }
    //1.滑动时调用
    //position:当前滑动页面的下标
    // positionOffset：页面的滑动比率
    // positionOffsetPixels：页面滑动的实际像素
    private int width;
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
     //计算两个灰色点的距离,计算红点移动的距离
        if(width == 0) {
            width = containerGrayPoint.getChildAt(1).getLeft() - containerGrayPoint.getChildAt(0).getLeft();
        }
        //width*positionOffset+width*position;
        //重新设置小红点的位置与相对布局的位置
        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
        params.leftMargin= (int) (width*positionOffset+width*position);
        redPoint.setLayoutParams(params);
    }
    //2.页面被选中的时候调用
    @Override
    public void onPageSelected(int position) {
        if(position == imgs.length-1){
            btStart.setVisibility(View.VISIBLE);
        }else{
            btStart.setVisibility(View.GONE);
        }
    }
    //3.滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //引导按钮的点击事件
    @Override
    public void onClick(View v) {
       SPUtils.saveBoolean(this, Constant.KEY_HAS_GUIDE,true);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
