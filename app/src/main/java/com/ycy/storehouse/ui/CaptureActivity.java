package com.ycy.storehouse.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.king.zxing.CaptureHelper;
import com.king.zxing.Intents;
import com.king.zxing.OnCaptureCallback;
import com.king.zxing.ViewfinderView;
import com.king.zxing.camera.CameraManager;
import com.ycy.storehouse.R;
import com.ycy.storehouse.base.BaseActivity;

/**
 * 二维码扫描自定义
 * */
public class CaptureActivity extends BaseActivity implements OnCaptureCallback {

    public static final String KEY_RESULT = Intents.Scan.RESULT;

    private SurfaceView surfaceView;
    private ViewfinderView viewfinderView;

    private CaptureHelper mCaptureHelper;

    @Override
    protected int getLayoutResource() {
        return getLayoutId();
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("扫描条码");
        initUI();
    }


    /**
     * 初始化
     */
    public void initUI() {
        surfaceView = findViewById(getSurfaceViewId());
        viewfinderView = findViewById(getViewfinderViewId());
        mCaptureHelper = new CaptureHelper(this, surfaceView, viewfinderView);
        mCaptureHelper.setOnCaptureCallback(this);
        mCaptureHelper.onCreate();
    }

    /**
     * 返回true时会自动初始化{@link #setContentView(int)}，返回为false是需自己去初始化{@link #setContentView(int)}
     *
     * @param layoutId
     * @return 默认返回true
     */
    public boolean isContentView(@LayoutRes int layoutId) {
        return true;
    }

    /**
     * 布局id
     *
     * @return
     */
    public int getLayoutId() {
        return R.layout.zxl_capture;
    }

    /**
     * {@link ViewfinderView} 的 id
     *
     * @return
     */
    public int getViewfinderViewId() {
        return R.id.viewfinderView;
    }


    /**
     * 预览界面{@link #surfaceView} 的id
     *
     * @return
     */
    public int getSurfaceViewId() {
        return R.id.surfaceView;
    }

    /**
     * Get {@link CaptureHelper}
     *
     * @return {@link #mCaptureHelper}
     */
    public CaptureHelper getCaptureHelper() {
        return mCaptureHelper;
    }

    /**
     * Get {@link CameraManager}
     *
     * @return {@link #mCaptureHelper#getCameraManager()}
     */
    public CameraManager getCameraManager() {
        return mCaptureHelper.getCameraManager();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCaptureHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCaptureHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCaptureHelper.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCaptureHelper.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /**
     * 接收扫码结果回调
     *
     * @param result 扫码结果
     * @return 返回true表示拦截，将不自动执行后续逻辑，为false表示不拦截，默认不拦截
     */
    @Override
    public boolean onResultCallback(String result) {
        return false;
    }

}
