package com.ycy.storehouse.ui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.ycy.storehouse.R;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.Contents;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.entity.BalanceBean;
import com.ycy.storehouse.utils.BalanceUtils;
import com.ycy.storehouse.utils.NoticeObserver;
import com.ycy.storehouse.utils.SharedPreferencesUtils;
import com.ycy.storehouse.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 入库
 */
public class EnterGoodsActivity extends BaseActivity implements NoticeObserver.Observer {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_number_unit)
    TextView tvNumberUnit;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.tv_goods)
    TextView tvGoods;

    private BalanceUtils balanceUtils;

    private int id;
    private String goodsName, cateName, unitName, storeName;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_enter_goods;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("入库");
        NoticeObserver.getInstance().addObserver(this);
        id = getIntent().getIntExtra("id", 0);
        goodsName = getIntent().getStringExtra("name");
        cateName = getIntent().getStringExtra("cateName");
        unitName = getIntent().getStringExtra("unit");
        storeName = getIntent().getStringExtra("storeName");
        tvName.setText(storeName);
        tvGoods.setText(goodsName);
        tvNumberUnit.setText(unitName);
        tvCategory.setText(cateName);
        etAddress.setText(String.valueOf(SharedPreferencesUtils.getSp(Contents.LOCATION_NAME,"")));

        balanceUtils = new BalanceUtils(this);


    }

    @OnClick({R.id.tv_ensure, R.id.tv_get_weight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_weight://获取重量
                balanceUtils.connect();
                break;
            case R.id.tv_ensure://提交
                if(TextUtils.isEmpty(etNumber.getText().toString())){
                    ToastUtil.showToast(this,"请输入数量");
                    return;
                }

                if(TextUtils.isEmpty(etPrice.getText().toString())){
                    ToastUtil.showToast(this,"请输入价格");
                    return;
                }

                if(TextUtils.isEmpty(etAddress.getText().toString())){
                    ToastUtil.showToast(this,"请输入地址");
                    return;
                }
                enterInfo(id,Double.parseDouble(etNumber.getText().toString()),
                        Integer.parseInt(etPrice.getText().toString()),
                        etAddress.getText().toString());
               // balanceUtils.connectBreak();
                break;
        }
    }

    /**
     * 入库
     */
    private void enterInfo(int goodsId, double number, int enterPrice,String address) {
        ApiInterface.ApiFactory.createApi().addEnterRecords(goodsId, number, enterPrice,address).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                ToastUtil.showToast(EnterGoodsActivity.this, result);
                finish();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        balanceUtils.close();//关闭服务
        super.onDestroy();
        NoticeObserver.getInstance().removeObserver(this);
    }

    @Override
    public <T> void update(int what, T t) {
        switch (what) {
            case Contents.NOTICE_BALANCE:
                Message msg = Message.obtain();
                msg.obj = t;
                handler.sendMessage(msg);

                break;
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = (String) msg.obj;
            etNumber.setText(result.replace("kg",""));

        }
    };


}
