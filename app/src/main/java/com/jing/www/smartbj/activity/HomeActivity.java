package com.jing.www.smartbj.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jing.www.smartbj.R;

/**
 * Created by Administrator on 2017/2/5.
 */

public class HomeActivity extends Activity {

    private ViewPager vp;
    private RadioButton rb_home;
    private RadioButton rb_newscenter;
    private RadioButton rb_smartservice;
    private RadioButton rb_govaffairs;
    private RadioButton rb_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        RadioGroup rg_tab = (RadioGroup) findViewById(R.id.rg);
        rb_home = (RadioButton) findViewById(R.id.rb_tab_home);
        rb_newscenter = (RadioButton) findViewById(R.id.rb_tab_newscenter);
        rb_smartservice = (RadioButton) findViewById(R.id.rb_tab_smartservice);
        rb_govaffairs = (RadioButton) findViewById(R.id.rb_tab_govaffairs);
        rb_setting = (RadioButton) findViewById(R.id.rb_tab_setting);
    }
}
