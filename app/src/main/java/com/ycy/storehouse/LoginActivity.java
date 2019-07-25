package com.ycy.storehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.Contents;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.entity.LoginBean;
import com.ycy.storehouse.utils.GsonUtils;
import com.ycy.storehouse.utils.SharedPreferencesUtils;
import com.ycy.storehouse.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * 登录页面
 * */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_admin)
    EditText etAdmin;
    @BindView(R.id.login_pwd)
    EditText etPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.spin_kit)
    SpinKitView spinKitView;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitialization(Bundle bundle) {

    }

    @OnClick({R.id.tv_login})
    public void onViewClick(View view){
        String adminName = etAdmin.getText().toString();
        String adminPwd = etPwd.getText().toString();
        initDate(adminName, adminPwd);

    }

    private void initDate(String adminName, String adminPwd) {
        if (TextUtils.isEmpty(adminName)) {
            ToastUtil.showCenterShort("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(adminPwd)) {
            ToastUtil.showCenterShort("请输入密码");
            return;
        }
        spinKitView.setVisibility(View.VISIBLE);
        ApiInterface.ApiFactory.createApi().login(adminName, adminPwd).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                //spinKitView.setVisibility(View.GONE);
                LoginBean loginBean = GsonUtils.jsonToBean(result,LoginBean.class);
                SharedPreferencesUtils.saveSp(Contents.TOKEN+"", loginBean.getToken());
                SharedPreferencesUtils.saveSp(Contents.USER_NAME, loginBean.getPersonnelInfoDomain().getName());
                SharedPreferencesUtils.saveSp(Contents.STORE_ID, loginBean.getStoreId());
                SharedPreferencesUtils.saveSp(Contents.STORE_NAME, loginBean.getStoreName());
                ToastUtil.showToast(LoginActivity.this,"登录成功");
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String msg) {
                spinKitView.setVisibility(View.GONE);
                ToastUtil.showToast(LoginActivity.this,"登录失败");
            }
        });
    }
}
