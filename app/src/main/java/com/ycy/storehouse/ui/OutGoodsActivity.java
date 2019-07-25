package com.ycy.storehouse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.ycy.storehouse.R;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.Contents;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.entity.CategoryBean;
import com.ycy.storehouse.entity.StoreAccountBean;
import com.ycy.storehouse.entity.UnitBean;
import com.ycy.storehouse.utils.BalanceUtils;
import com.ycy.storehouse.utils.GsonUtils;
import com.ycy.storehouse.utils.NoticeObserver;
import com.ycy.storehouse.utils.SharedPreferencesUtils;
import com.ycy.storehouse.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 出库
 */
public class OutGoodsActivity extends BaseActivity implements NoticeObserver.Observer{


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_get)
    EditText etGet;
    @BindView(R.id.et_category)
    EditText etCategory;
    @BindView(R.id.et_unit)
    EditText etUnit;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_address)
    EditText etAddress;

    private int id;
    private int store_id;

    private ListPopupWindow mListPop;

    //取货人数据
    private List<StoreAccountBean> accountBeans = new ArrayList<>();
    private List<String> nameList = new ArrayList<>();

    //单位数据
    private List<UnitBean> unitBeans = new ArrayList<>();
    private List<String> unitList = new ArrayList<>();

    //分类数据
    private List<CategoryBean> categoryBeans = new ArrayList<>();
    List<String> content1 = new ArrayList<>();
    List<List<String>> content2 = new ArrayList<>();
    List<List<List<String>>> content3 = new ArrayList<>();

    private int optionId;//分类id
    private String cateName = "";
    private int getId;//取货人id
    private int unitId;//单位id

    private BalanceUtils balanceUtils;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_out_goods;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("出库");
        id = getIntent().getIntExtra("id", 0);
        optionId =getIntent().getIntExtra("cateId",0);
        cateName =  getIntent().getStringExtra("cateName");
        etCategory.setText(cateName);
        store_id = (int) SharedPreferencesUtils.getSp(Contents.STORE_ID, 0);
        tvName.setText(String.valueOf(SharedPreferencesUtils.getSp(Contents.USER_NAME, "")));

        etAddress.setText(String.valueOf(SharedPreferencesUtils.getSp(Contents.LOCATION_NAME,"")));
        NoticeObserver.getInstance().addObserver(this);
        balanceUtils = new BalanceUtils(this);

        outStoreList(id);
        getUnitData();
        getDataCategory();
    }

    @OnClick({R.id.et_get, R.id.et_category, R.id.et_unit, R.id.tv_ensure,R.id.tv_get_weight})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_get_weight://获取重量
                balanceUtils.connect();
                break;
            case R.id.et_get://提取人
                showPoPu(etGet, nameList, 1);
                break;
            case R.id.et_category://分类
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(OutGoodsActivity.this, (options1, option2, options3, v1) -> {
                    //返回的分别是三个级别的选中位置
                    if (categoryBeans.get(options1).getCategory().size() == 0) {
                        etCategory.setText(categoryBeans.get(options1).getName());
                        optionId = categoryBeans.get(options1).getId();
                    } else if (categoryBeans.get(options1).getCategory().get(option2).getCategory().size() == 0) {
                        etCategory.setText(categoryBeans.get(options1).getCategory().get(option2).getName());
                        optionId = categoryBeans.get(options1).getCategory().get(option2).getId();
                    } else {
                        etCategory.setText(categoryBeans.get(options1).getCategory().get(option2).getCategory().get(options3).getName());
                        optionId = categoryBeans.get(options1).getCategory().get(option2).getCategory().get(options3).getId();
                    }
                }).build();
                pvOptions.setPicker(content1, content2, content3);
                pvOptions.show();
                break;
            case R.id.et_unit://单位
                showPoPu(etUnit, unitList, 2);
                break;
            case R.id.tv_ensure://确认出库
                if (TextUtils.isEmpty(tvName.getText().toString())) {
                    ToastUtil.showToast(this, "登录失效，请重新登录");
                }
                if (TextUtils.isEmpty(etGet.getText().toString())) {
                    ToastUtil.showToast(this, "请选择取货人");
                }
                if (TextUtils.isEmpty(etCategory.getText().toString())) {
                    ToastUtil.showToast(this, "请选择分类");
                }
                if (TextUtils.isEmpty(etUnit.getText().toString())) {
                    ToastUtil.showToast(this, "请选择出货单位");
                }
                if (TextUtils.isEmpty(etNumber.getText().toString())) {
                    ToastUtil.showToast(this, "请输入数量");
                }
                if (TextUtils.isEmpty(etAddress.getText().toString())) {
                    ToastUtil.showToast(this, "请打开GPS定位");
                }
                outGoodsInfo(id, tvName.getText().toString(), optionId, unitId,
                        Double.valueOf(etNumber.getText().toString()),
                        etAddress.getText().toString(), getId, store_id);
                break;
        }
    }

    /**
     * 下拉框
     * type 1取货人 2单位
     */
    private void showPoPu(EditText editText, List<String> list, int type) {
        mListPop = new ListPopupWindow(this);
        mListPop.setAdapter(new ArrayAdapter<String>(this, R.layout.item_text, list));
        mListPop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mListPop.setHeight(300);
        mListPop.setAnchorView(editText);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        mListPop.setModal(true);//设置是否是模式
        mListPop.setOnItemClickListener((parent, view, position, id) -> {
            if (type == 1) {
                getId = accountBeans.get(position).getAccountId();
            } else {
                unitId = unitBeans.get(position).getId();
            }
            editText.setText(list.get(position));
            mListPop.dismiss();
        });
        mListPop.show();
    }

    /**
     * 出库数据
     */
    private void outGoodsInfo(int goodsId, String person, int cateId, int unitId,
                              double number, String address,
                              int accountId, int storeId) {
        ApiInterface.ApiFactory.createApi().addOutRecords(goodsId, person, cateId, unitId,
                number, address, accountId, storeId).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                ToastUtil.showToast(OutGoodsActivity.this, result);
                finish();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 出库权限人员列表
     */
    private void outStoreList(int storeId) {
        ApiInterface.ApiFactory.createApi().outStoreList(storeId).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                accountBeans = GsonUtils.fromJsonList(result, StoreAccountBean.class);
                if (accountBeans.size() > 0) {
                    for (int i = 0; i < accountBeans.size(); i++) {
                        nameList.add(accountBeans.get(i).getAccountName());
                    }
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 单位列表
     */
    private void getUnitData() {
        ApiInterface.ApiFactory.createApi().goodsUnit().enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                unitBeans = GsonUtils.fromJsonList(result, UnitBean.class);
                if (unitBeans.size() > 0) {
                    for (int i = 0; i < unitBeans.size(); i++) {
                        unitList.add(unitBeans.get(i).getName());
                    }
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 获取分类
     */
    private void getDataCategory() {
        ApiInterface.ApiFactory.createApi().goodsCategoryAll().enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                categoryBeans = GsonUtils.fromJsonList(result, CategoryBean.class);
                for (int i = 0; i < categoryBeans.size(); i++) {
                    //添加一级分类
                    content1.add(categoryBeans.get(i).getName());
                    List<String> options = new ArrayList<>();
                    List<List<String>> contentSun = new ArrayList<>();

                    for (int x = 0; x < categoryBeans.get(i).getCategory().size(); x++) {
                        //添加二级分类
                        options.add(categoryBeans.get(i).getCategory().get(x).getName());
                        List<String> optionsS = new ArrayList<>();
                        for (int y = 0; y < categoryBeans.get(i).getCategory().get(x).getCategory().size(); y++) {
                            //添加三级分类
                            optionsS.add(categoryBeans.get(i).getCategory().get(x).getCategory().get(y).getName());
                        }
                        if (optionsS.size() > 0) {
                            contentSun.add(optionsS);
                        } else {
                            optionsS.add("");
                            contentSun.add(optionsS);
                        }

                    }
                    if (options.size() > 0) {
                        content2.add(options);
                    } else {
                        //添加二级空数据
                        options.add("");
                        content2.add(options);
                    }
                    if (contentSun.size() > 0) {
                        content3.add(contentSun);
                    } else {
                        //添加三级空数据
                        List<String> list = new ArrayList<>();
                        list.add("");
                        contentSun.add(list);
                        content3.add(contentSun);
                    }

                }
            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.showToast(OutGoodsActivity.this, "查询失败");
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
