package com.ycy.storehouse;

import android.os.Bundle;
import android.widget.Toast;

import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.fragment.HomeFragment;
import com.ycy.storehouse.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_view)
    TabView tabView;
    private long exitTime = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        List<TabViewChild> tabViewChildList = new ArrayList<>();
        //底端tab
        tabViewChildList.add(new TabViewChild(R.mipmap.home_light, R.mipmap.home, "首页", new HomeFragment()));
        tabViewChildList.add(new TabViewChild(R.mipmap.my_light, R.mipmap.my, "我的", new UserFragment()));
        tabView.setTabViewChild(tabViewChildList, getSupportFragmentManager());

    }

    //监听back返回键，不超过2秒 连续点击退出应用
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            //计算当前时间
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            //退出应用
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
