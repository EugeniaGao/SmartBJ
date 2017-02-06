package com.jing.www.smartbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.jing.www.smartbj.R;
import com.jing.www.smartbj.utils.Constant;
import com.jing.www.smartbj.utils.SPUtils;

public class SplashActivity extends Activity implements Animation.AnimationListener {
  private Handler mHandler=new Handler();
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl = (RelativeLayout) findViewById(R.id.activity_main);
       // Intent intent = new Intent(this,HomeActivity.class);
       // startActivity(intent);
        //創建閃屏界面的動畫
        Animation animation = createAnimation();
        rl.startAnimation(animation);
        animation.setAnimationListener(this);


    }

    private Animation createAnimation() {
        AnimationSet animationSet = new AnimationSet(false);
        //旋转
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2000);
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000);
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0,1,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(2000);

        animationSet.addAnimation(rotate);
        animationSet.addAnimation(alpha);
        animationSet.addAnimation(scale);
        return animationSet;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
     //要进行延迟跳转
        mHandler.postDelayed(new MyTask(),500);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    public  class  MyTask implements Runnable{

        @Override
        public void run() {
            boolean hasGuide = SPUtils.getBoolean(SplashActivity.this, Constant.KEY_HAS_GUIDE,false);
            if(hasGuide){
                //sp中进行过向导的演示,就直接进入home界面中
                Intent intent= new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }else{
                Intent intent= new Intent(getApplicationContext(),GuideActivity.class);
                startActivity(intent);
            }
               finish();
        }
    }
}

