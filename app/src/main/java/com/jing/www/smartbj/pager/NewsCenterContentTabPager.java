package com.jing.www.smartbj.pager;

import android.content.Context;
import android.os.Handler;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.jing.www.smartbj.R;
import com.jing.www.smartbj.adapter.SwitchImgVPAdapter;
import com.jing.www.smartbj.bean.NewsCenterTabBean;
import com.jing.www.smartbj.fragment.NewsCenterFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/12.
 * 加载新闻中心新闻主体内容的碎片
 */

public class NewsCenterContentTabPager implements ViewPager.OnPageChangeListener {
    //1.创建handler
    private Handler mHandler = new Handler();
    private boolean hasSwitch;
    private static final String TAG = "tag";
    //添加布局,在构造时候就初始化

    public Context context;
    public View view;

    @BindView(R.id.vp_switch_image)
    com.jing.www.smartbj.pager.SwitchImageViewViewPager vpSwitchImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_point_container)
    LinearLayout llPointContainer;
    private NewsCenterTabBean newsCenterTabBean;
    private List<ImageView> imgViews;

    public NewsCenterContentTabPager(Context context) {
        this.context = context;
        view = initView();
    }

    private View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.newscenter_content_tab, null);
        ButterKnife.bind(this, view);
        return view;
    }

    //    //从网络上获取数据
//    public void loadNetData(String url) {
//        OkHttpUtils
//                .get()
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        //TODO
//                        MyToast.show(context, "tab_新闻主体数据加载失败");
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        MyLogger.i(TAG,response);
//                        processData(response);
//                    }
//
//
//                });
//
//    }
    public void processData(String json) {
        Gson gson = new Gson();
        newsCenterTabBean = gson.fromJson(json, NewsCenterTabBean.class);
        //加载数据要将数据绑定到对应的控件上
        bindDataToView();
        //把当前的NewsCenterContentTabPager对象传递给SwitchImageViewViewPager
        vpSwitchImage.setTabPager(this);
    }


    private void bindDataToView() {
        initSwitchImgView();
        initPoint();
    }

    /**
     * 创建小圆点
     */
    private void initPoint() {
        llPointContainer.removeAllViews();
        for (int i = 0; i < newsCenterTabBean.data.topnews.size(); i++) {
            View view = new View(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(5, 5);
            params.rightMargin = 10;
            view.setBackgroundResource(R.drawable.point_gray_bg);
            llPointContainer.addView(view, params);
        }
        //设置小红点
        llPointContainer.getChildAt(0).setBackgroundResource(R.drawable.point_red_bg);

    }

    private void initSwitchImgView() {
        //根据网络数据中的图片个数来进行添加
        imgViews = new ArrayList<>();
        int size = newsCenterTabBean.data.topnews.size();

        for (int i = -1; i < size + 1; i++) {
            NewsCenterTabBean.TopNewsBean topNewsBean = null;
            if (i == -1) {
                topNewsBean = newsCenterTabBean.data.topnews.get(size - 1);

            } else if (i == size) {
                topNewsBean = newsCenterTabBean.data.topnews.get(0);
            } else {
                topNewsBean = newsCenterTabBean.data.topnews.get(i);
            }
            ImageView iv = new ImageView(context);
//            Picasso
//                    .with(context)
//                    .load(topNewsBean.topimage)
//                    .into(iv);
//            imgViews.add(iv);
            Picasso.get()
                    .load(topNewsBean.topimage)
                    .into(iv);
            imgViews.add(iv);

        }
        //创建轮播图适配器
        SwitchImgVPAdapter adapter = new SwitchImgVPAdapter(imgViews, newsCenterTabBean.data.topnews);
        vpSwitchImage.setAdapter(adapter);
        //添加轮播图上文字的显示
        tvTitle.setText(newsCenterTabBean.data.topnews.get(0).title);
        //设置文字的改变监听
        vpSwitchImage.addOnPageChangeListener(this);
        vpSwitchImage.setCurrentItem(1, false);
    }

    @OnClick(R.id.vp_switch_image)
    public void onClick() {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {
        //动态改变轮播文字
        //修正文字下表
        int size = newsCenterTabBean.data.topnews.size();
        int textIndex = 0;

        if (position == 0) {
            textIndex = size - 1;
            vpSwitchImage.setCurrentItem(size, false);
        } else if (position == size + 1) {
            textIndex = 0;
            vpSwitchImage.setCurrentItem(1, false);
        } else {

            textIndex = position - 1;
        }
        tvTitle.setText(newsCenterTabBean.data.topnews.get(textIndex).title);
        //动态改变原点
        int pointCount = llPointContainer.getChildCount();
        for (int i = 0; i < pointCount; i++) {
            View child = llPointContainer.getChildAt(i);
            if (textIndex == i) {
                child.setBackgroundResource(R.drawable.point_red_bg);
            } else {
                child.setBackgroundResource(R.drawable.point_gray_bg);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //2.自定义开始切换方法开始轮播,未指定嗲用
    //被调用时机思考:tab被选中或是切换时,这个类是放在views中的,切换时是tab点击
    public void startSwitch() {
        if (!hasSwitch) {
            //发送handler切换
            mHandler.postDelayed(new SwitchTask(), 3000);
            hasSwitch = true;
        }
    }

    //3.自定义开始切换方法停止轮播,未指定嗲用
    public void stopSwitch() {
        if (hasSwitch) {
            //发送handler切换,参数为取而代之
            mHandler.removeCallbacksAndMessages(null);
            hasSwitch = false;
        }
    }

    //4.在子线程处理切换的逻辑
    private class SwitchTask implements Runnable {

        @Override
        public void run() {
            //指定循环的出口
            if (vpSwitchImage != null) {
                //1.获取当前的item
                System.out.println(newsCenterTabBean.data.topnews.size() + "COUNT");
                int currentItem = vpSwitchImage.getCurrentItem();
                //2.判断是否是最后条目
                //                  newsCenterTabBean.data.topnews.size()
                if (currentItem == vpSwitchImage.getChildCount()) {
                    // if (currentItem == newsCenterTabBean.data.topnews.size()) {
                    currentItem = 0;

                } else {
                    // currentItem = currentItem + 1;
                    currentItem++;
                }

                //3.重置当前的条目
                vpSwitchImage.setCurrentItem(currentItem);
            }
            //4.返回主线程或循环
            mHandler.postDelayed(this, 3000);
        }

    }


}
