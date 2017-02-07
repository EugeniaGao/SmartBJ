package com.jing.www.smartbj.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jing.www.smartbj.R;
import com.jing.www.smartbj.activity.MainActivity;

/**
 * Created by Administrator on 2017/2/6.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private TextView title;
    private ImageButton menu;
    private ImageButton type;
    private FrameLayout frameContaner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.base_fragment_activity,container,false);
        title = (TextView) view.findViewById(R.id.tv_title);
        menu = (ImageButton) view.findViewById(R.id.ib_menu);
        type = (ImageButton) view.findViewById(R.id.ib_type);
        frameContaner = (FrameLayout) view.findViewById(R.id.base_fl_container);
        initEvent();
        return view;
    }

    private void initEvent() {
        menu.setOnClickListener(this);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       //初始化标题
        initTitle();
    }



    //我只是一个基类,标题的内容,图标的显示,我不能决定,需要子类来决定,好吧,那就强制告诉子类去提供吧
    public abstract void initTitle();
    //设置标题
    public void setTitle(String text){
        title.setText(text);
    }
    //设置menu可见性
    public void setMenu(boolean boo){
        menu.setVisibility(boo? View.VISIBLE:View.GONE);
    }
    //设置type可见性
    public void setType(boolean boo){
        type.setVisibility(boo? View.VISIBLE:View.GONE);
    }
    //创建内容
    public abstract View createContent();
    //向容器中添加内容
    public void addView(View view){
      frameContaner.removeAllViews();
      frameContaner.addView(view);
    }


    //点击侧滑菜单
    @Override
    public void onClick(View v) {
       //侧滑菜单在activity中
        ((MainActivity)getActivity()).slidingMenu.toggle();
    }
}
