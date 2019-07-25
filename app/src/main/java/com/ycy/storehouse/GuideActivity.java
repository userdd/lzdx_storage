package com.ycy.storehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.Contents;
import com.ycy.storehouse.utils.SharedPreferencesUtils;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.banner_guide)
    BGABanner bgaBanner;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_guide;
    }

    @Override
    protected void onInitialization(Bundle bundle) {

        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        bgaBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.index1,
                R.mipmap.index2,
                R.mipmap.index3);

        bgaBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                SharedPreferencesUtils.saveSp(Contents.IS_FIRST,"true");
                if (!SharedPreferencesUtils.getSp(Contents.TOKEN+"","").equals("")){
                    launchActivity(GuideActivity.this, MainActivity.class);
                }else {
                    launchActivity(GuideActivity.this, LoginActivity.class);
                }
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        bgaBanner.setBackgroundResource(android.R.color.white);
    }
}
