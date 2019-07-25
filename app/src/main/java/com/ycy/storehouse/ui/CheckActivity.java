package com.ycy.storehouse.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ycy.storehouse.R;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.Contents;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.utils.DialogUtils;
import com.ycy.storehouse.utils.SharedPreferencesUtils;
import com.ycy.storehouse.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 盘点
 * */
public class CheckActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_category)
    EditText etCategory;
    @BindView(R.id.et_store_name)
    EditText etStoreName;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_stock)
    EditText etStock;
    @BindView(R.id.et_check)
    EditText etCheck;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.tv_surplus_unit)
    TextView tvSurplusUnit;
    @BindView(R.id.tv_ensure)
    TextView tvEnsure;
    @BindView(R.id.tv_back)
    TextView tvBack;

    private int id, price, unitId, providerId,storeId;
    private String name, cateName, storeName, unit, stock, lSpecs;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_check;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("盘点");

        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        cateName = getIntent().getStringExtra("cateName");
        storeName = getIntent().getStringExtra("storeName");
        price = getIntent().getIntExtra("price", 0);
        unit = getIntent().getStringExtra("unit");
        stock = getIntent().getStringExtra("stock");
        lSpecs = getIntent().getStringExtra("lSpecs");
        unitId = getIntent().getIntExtra("unitId", 0);
        storeId = getIntent().getIntExtra("storeId", 0);
        providerId = getIntent().getIntExtra("providerId", 0);

        etName.setText(name);
        etCategory.setText(cateName);
        etStoreName.setText(storeName);
        etPrice.setText(price+"");
        tvUnit.setText(unit);
        tvSurplusUnit.setText(unit);
        etStock.setText(stock);
        tvBack.setOnClickListener(v -> finish());


    }

    @OnClick({R.id.tv_ensure})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_ensure:
                DialogUtils.getInstance().showSimpleDialog(CheckActivity.this, R.layout.dialog_center_check,
                        R.style.Theme_Dialog_From_Center, (contentView, utils) -> {
                            TextView tvTitle = contentView.findViewById(R.id.tv_title);
                            TextView tvEnsure = contentView.findViewById(R.id.tv_ensure);
                            TextView tvBack = contentView.findViewById(R.id.tv_back);
                            tvTitle.setText("当前操作记录会被保存，是否继续？");
                            tvEnsure.setOnClickListener(v12 -> {
                                if (TextUtils.isEmpty(etCheck.getText().toString())) {
                                    ToastUtil.showToast(CheckActivity.this, "盘点数量不能为空");
                                    return;
                                }
                                getData(id,name, Integer.parseInt(etCheck.getText().toString()), price, storeName,
                                        etRemarks.getText().toString(), -1, unitId, providerId, lSpecs,storeId);
                                utils.close();
                            });
                            tvBack.setOnClickListener(v1 -> utils.close());
                        });

                break;
        }
    }

    private void getData(int goodsId,String name, int number, int price, String position,
                         String lRemarks, int state, int unitId, int providerId,
                         String lSpecs,int storeId) {
        ApiInterface.ApiFactory.createApi().addGoodsLoos(goodsId,name, number, price, position, lRemarks,
                state, unitId, providerId, lSpecs,storeId).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                ToastUtil.showToast(CheckActivity.this,result);
                finish();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
