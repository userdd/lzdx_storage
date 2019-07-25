package com.ycy.storehouse.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Toast;

import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;
import com.ycy.storehouse.R;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.utils.PrintUtil;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 打印机测试
 * */
public class TestActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    private PrintUtil printUtil;
    public static final int REQUEST_CODE_SCAN = 0X01;
    public static final int RC_CAMERA = 0X01;
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_IS_CONTINUOUS = "key_continuous_scan";
    private boolean isContinuousScan = false;
    private Class<?> cls;
    private String title;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_text;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        // 调用LPAPI对象的init方法初始化对象
        printUtil = new PrintUtil(this);
    }
    // 打印文本一维码的按钮事件
    public void printText1DBarcodeOnClick(View view) {
        // 显示打印数据设置界面
        // 获取打印数据并进行打印
        String text1 = "兰州大学后勤部";
        String text2 = "1234567";
       printUtil.printClick(text1,text2);
    }

    public void LookBarcodeOnClick(View view){
        this.cls = CaptureActivity.class;
        this.title = view.toString();
        checkCameraPermissions();
    }

    /**
     * 检测拍摄权限
     */
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(){
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {//有权限
            startScan(cls,title);
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "请允许打开摄像头扫码",
                    RC_CAMERA, perms);
        }
    }

    /**
     * 扫码
     * @param cls
     * @param title
     */
    private void startScan(Class<?> cls,String title){
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(this,R.anim.in,R.anim.out);
        Intent intent = new Intent(this, cls);
        intent.putExtra(KEY_TITLE,title);
        intent.putExtra(KEY_IS_CONTINUOUS,isContinuousScan);
        ActivityCompat.startActivityForResult(this,intent,REQUEST_CODE_SCAN,optionsCompat.toBundle());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null){
            switch (requestCode){
                case REQUEST_CODE_SCAN:
                    String result = data.getStringExtra(Intents.Scan.RESULT);
                    Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
                    break;
                /*case REQUEST_CODE_PHOTO:
                    parsePhoto(data);
                    break;*/
            }

        }
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
