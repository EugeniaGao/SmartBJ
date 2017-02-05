package com.jing.www.smartbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jing.www.smartbj.R;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
