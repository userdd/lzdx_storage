package com.ycy.storehouse.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ycy.storehouse.R;
import com.zhy.autolayout.AutoLayoutActivity;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AutoLayoutActivity implements BaseView {

    public TextView tvTitle, tvTitleRight;
    public View viewStatus;
    public ImageView titleBack, titleRight1, titleRight2;
    public RelativeLayout rlRoot;
    public LinearLayout emptyRoot;
    public View inflater;

    private boolean showPDialogWithoutCancleable = false;
    protected BasePresenter mPresenter;

    protected abstract int getLayoutResource();

    protected abstract void onInitialization(Bundle bundle);

    public Context context;

    private boolean status = false;

    protected Class getLogicClazz() {
        return null;
    }

    protected void onInitData2Remote() {
        if (getLogicClazz() != null)
            mPresenter = getLogicImpl();
    }

    public void initTitle(String text, boolean status) {
        initTitle(text);
        //status false 黑色 ; true 红色
        if (status) {
            //viewStatus.setBackgroundColor(Color.rgb(29, 28, 36));
            rlRoot.setBackgroundColor(getResources().getColor(R.color.mainColor));
        } else {
            //viewStatus.setBackgroundColor(Color.rgb(29, 28, 36));
            rlRoot.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    public void initTitle(String text) {
        tvTitle = (TextView) findViewById(R.id.title);
        tvTitleRight = (TextView) findViewById(R.id.title_right);
        titleBack = (ImageView) findViewById(R.id.title_back);
        titleRight1 = (ImageView) findViewById(R.id.title_right1);
        titleRight2 = (ImageView) findViewById(R.id.title_right2);
        viewStatus = findViewById(R.id.include_status);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        emptyRoot = (LinearLayout) findViewById(R.id.empty_root);
        inflater = LayoutInflater.from(this).inflate(R.layout.common_view_empty, null);
        emptyRoot.addView(inflater);
        if (titleBack != null) {
            titleBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    //如果键盘已展开 则隐藏
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
                    }
                    finish();
                }
            });
        }
        if (tvTitle != null) {
            tvTitle.setText(text);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = BaseActivity.this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        BaseApp.getInstance().addActivity(this);
        statusBarDark();
        if (getLayoutResource() != 0)
            setContentView(getLayoutResource());
        ButterKnife.bind(this);
        this.onInitialization(savedInstanceState);
        this.onInitData2Remote();
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    //获得该页面的实例
    public <T> T getLogicImpl() {
        return LogicProxy.getInstance().bind(getLogicClazz(), this);
    }

    @Override
    protected void onStart() {
        if (!this.showPDialogWithoutCancleable)
            //cancelPDialog();
        super.onStart();
        if (mPresenter != null && !mPresenter.isViewBind()) {
            LogicProxy.getInstance().bind(getLogicClazz(), this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       BaseApp.getInstance().removeActivity(this);
        LogicProxy.getInstance().unbind(getLogicClazz(), this);
        if (mPresenter != null)
            mPresenter.detachView();
        //cancelPDialog();
    }

    /*修改状态栏字体颜色*/
    public void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void statusBarDark() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (SystemBarHelper.isMIUI6Later() || SystemBarHelper.isFlyme4Later() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                SystemBarHelper.setStatusBarDarkMode(this);
//                SystemBarHelper.tintStatusBar(this, Color.TRANSPARENT, 0);
//                getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_IMMERSIVE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//
//            } else {
//                SystemBarHelper.tintStatusBar(this, Color.parseColor("#bbbbbb"), 0);
//            }
//        }
        /**
         * 初始化状态栏相关，
         * PS: 设置全屏需要在调用super.onCreate(arg0);之前设置setIsFullScreen(true);否则在Android 6.0下非全屏的activity会出错;
         * SDK19：可以设置状态栏透明，但是半透明的SYSTEM_BAR_BACKGROUNDS会不好看；
         * SDK21：可以设置状态栏颜色，并且可以清除SYSTEM_BAR_BACKGROUNDS，但是不能设置状态栏字体颜色（默认的白色字体在浅色背景下看不清楚）；
         * SDK23：可以设置状态栏为浅色（SYSTEM_UI_FLAG_LIGHT_STATUS_BAR），字体就回反转为黑色。
         * 为兼容目前效果，仅在SDK23才显示沉浸式。
         */

        Window win = getWindow();

        //KITKAT也能满足，只是SYSTEM_UI_FLAG_LIGHT_STATUS_BAR（状态栏字体颜色反转）只有在6.0才有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
            // 状态栏字体设置为深色，SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 为SDK23增加
            win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            //win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // 部分机型的statusbar会有半透明的黑色背景
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            win.setStatusBarColor(Color.TRANSPARENT);// SDK21
        }
    }

    /**
     * 获取Edittext内容
     *
     * @param editText
     * @return
     */
    public String getEdtText(EditText editText) {
        if (editText.getText() != null) {
            return editText.getText().toString();
        } else {
            return "";
        }
    }

    /**
     * 获取Textview内容
     *
     * @param tv
     * @return
     */
    public String getTvText(TextView tv) {
        if (tv.getText() != null) {
            return tv.getText().toString();
        } else {
            return "";
        }
    }

    /**
     * 判断Edittext 或TextView 内容是否为空 或者""；
     */
    public boolean EditTextIsNotNull(EditText editText) {
        if (editText.getText() != null) {
            if (editText.getText().toString().length() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean TextViewIsNotNull(TextView tv) {
        if (tv.getText() != null) {
            if (tv.getText().toString().length() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void toast0(String msg) {
        if (msg == null) {
            toast0("");
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void toast1(String msg) {
        if (msg == null) {
            toast1("");
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public static void launchActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    /*
     * Activity的跳转-带参数
     */
    public void setIntentClass(Class<?> cla, String type, Object obj) {
        Intent intent = new Intent();
        intent.setClass(this, cla);
        intent.putExtra(type, (Serializable) obj);
        startActivity(intent);


    }
}