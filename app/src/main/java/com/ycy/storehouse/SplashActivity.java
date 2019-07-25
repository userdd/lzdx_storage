package com.ycy.storehouse;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.RelativeLayout;

import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.Contents;
import com.ycy.storehouse.utils.SharedPreferencesUtils;

import butterknife.BindView;

/**
 * 启动页
 * */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash)
    RelativeLayout splash;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        showAnimation();
    }


    //动画效果的方法
    private void showAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1);
        alphaAnimation.setDuration(1500);
        alphaAnimation.setFillAfter(true);
        animationSet.addAnimation(alphaAnimation);
        //开始动画
        splash.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //当动画结束时，调用的方法，跳转到主页面
            @Override
            public void onAnimationEnd(Animation animation) {
                if (!SharedPreferencesUtils.getSp(Contents.IS_FIRST,"").equals("")){
                    if (!SharedPreferencesUtils.getSp(Contents.TOKEN+"","").equals("")){
                        launchActivity(SplashActivity.this, MainActivity.class);
                    }else {
                        launchActivity(SplashActivity.this, LoginActivity.class);
                    }
                }else{
                    launchActivity(SplashActivity.this, GuideActivity.class);
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
