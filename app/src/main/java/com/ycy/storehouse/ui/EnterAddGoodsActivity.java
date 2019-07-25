package com.ycy.storehouse.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.ycy.storehouse.R;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.Contents;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.entity.CategoryBean;
import com.ycy.storehouse.entity.ProviderBean;
import com.ycy.storehouse.entity.UnitBean;
import com.ycy.storehouse.utils.BalanceUtils;
import com.ycy.storehouse.utils.DialogUtils;
import com.ycy.storehouse.utils.GsonUtils;
import com.ycy.storehouse.utils.NoticeObserver;
import com.ycy.storehouse.utils.PrintUtil;
import com.ycy.storehouse.utils.SharedPreferencesUtils;
import com.ycy.storehouse.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 添加商品
 */
public class EnterAddGoodsActivity extends BaseActivity implements NoticeObserver.Observer{

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pinyin)
    EditText etPinyin;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_specs)
    EditText etSpecs;
    @BindView(R.id.et_category)
    EditText etCategory;
    @BindView(R.id.et_unit)
    EditText etUnit;
    @BindView(R.id.et_provider)
    EditText etProvider;
    @BindView(R.id.et_min_warn)
    EditText etMinWarn;
    @BindView(R.id.et_position)
    EditText etPosition;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_remarks)
    EditText etRemarks;

    private int optionId, unitId, providerId, storeId;

    private ListPopupWindow mListPop;
    //单位数据
    private List<UnitBean> unitBeans = new ArrayList<>();
    private List<String> unitList = new ArrayList<>();

    //供应商数据
    private List<ProviderBean> providerBeans = new ArrayList<>();
    private List<String> providerList = new ArrayList<>();

    //分类数据
    private List<CategoryBean> categoryBeans = new ArrayList<>();
    List<String> content1 = new ArrayList<>();
    List<List<String>> content2 = new ArrayList<>();
    List<List<List<String>>> content3 = new ArrayList<>();

    private PrintUtil printUtil;

    private BalanceUtils balanceUtils;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_add_goods;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("添加商品");

        storeId = (int) SharedPreferencesUtils.getSp(Contents.STORE_ID, 0);
        NoticeObserver.getInstance().addObserver(this);
        printUtil = new PrintUtil(this);//初始化打印机
        balanceUtils = new BalanceUtils(this);//电子秤
        etAddress.setText(String.valueOf(SharedPreferencesUtils.getSp(Contents.LOCATION_NAME, "")));
        getDataCategory();
        getUnitData();
        getProviderData();
    }

    @OnClick({R.id.tv_ensure, R.id.et_category, R.id.et_unit, R.id.et_provider, R.id.tv_get_weight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_weight://获取重量
                balanceUtils.connect();
                break;
            case R.id.et_category://分类
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(EnterAddGoodsActivity.this, (options1, option2, options3, v1) -> {
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
                showPoPu(etUnit, unitList, 1);
                break;
            case R.id.et_provider://供应商
                showPoPu(etProvider, providerList, 2);
                break;
            case R.id.tv_ensure://提交

                if (TextUtils.isEmpty(etName.getText().toString())) {
                    ToastUtil.showToast(this, "商品名称为空");
                    return;
                }
                if (TextUtils.isEmpty(etNumber.getText().toString())) {
                    ToastUtil.showToast(this, "数量为空");
                    return;
                }
                if (TextUtils.isEmpty(etPrice.getText().toString())) {
                    ToastUtil.showToast(this, "请输入价格");
                    return;
                }
                if (TextUtils.isEmpty(etUnit.getText().toString())) {
                    ToastUtil.showToast(this, "请选择单位");
                    return;
                }
                if (TextUtils.isEmpty(etCategory.getText().toString())) {
                    ToastUtil.showToast(this, "请选择分类");
                    return;
                }
                if (TextUtils.isEmpty(etProvider.getText().toString())) {
                    ToastUtil.showToast(this, "请选择供应商");
                    return;
                }
                if (TextUtils.isEmpty(etMinWarn.getText().toString())) {
                    ToastUtil.showToast(this, "请输入警戒值");
                    return;
                }
                if (TextUtils.isEmpty(etAddress.getText().toString())) {
                    ToastUtil.showToast(this, "请获取地址");
                    return;
                }

                if (!printUtil.isPrinterConnected()) {
                    ToastUtil.showToast(this, getResources().getString(R.string.pleaseconnectprinter));
                    return;
                }
                enterInfo(etName.getText().toString(), etPinyin.getText().toString(),
                        Double.parseDouble(etNumber.getText().toString()),
                        Integer.parseInt(etPrice.getText().toString()), optionId, unitId, providerId, storeId,
                        Integer.parseInt(etMinWarn.getText().toString()), etPosition.getText().toString(),
                        etSpecs.getText().toString(), etRemarks.getText().toString(), etAddress.getText().toString());

                break;
        }
    }


    /**
     * 上传数据
     */
    private void enterInfo(String name, String pinYin, double stock, int price, int cateId,
                           int unitId, int providerId, int storeId, int minWarn, String position, String gSpecs,
                           String gRemarks, String address) {

        ApiInterface.ApiFactory.createApi().addGoods(name, pinYin, stock, price,
                cateId, unitId, providerId, storeId, minWarn, position, gSpecs,
                gRemarks, address).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                DialogUtils.getInstance().showSimpleDialog(EnterAddGoodsActivity.this, R.layout.dialog_center_check,
                        R.style.Theme_Dialog_From_Center, (contentView, utils) -> {
                            TextView tvTitle = contentView.findViewById(R.id.tv_title);
                            TextView tvEnsure = contentView.findViewById(R.id.tv_ensure);
                            TextView tvBack = contentView.findViewById(R.id.tv_back);
                            tvTitle.setText("添加成功，是否打印条码？");
                            tvEnsure.setOnClickListener(v12 -> {
                                printUtil.printClick("兰州大学后勤部", result);
                                utils.close();
                                finish();
                            });
                            tvBack.setOnClickListener(v1 -> utils.close());
                        });

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
                ToastUtil.showToast(EnterAddGoodsActivity.this, "查询失败");
            }
        });
    }

    /**
     * 下拉框
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
                unitId = unitBeans.get(position).getId();
            } else {
                providerId = providerBeans.get(position).getId();
            }

            editText.setText(list.get(position));
            mListPop.dismiss();
        });
        mListPop.show();
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
     * 供应商列表
     */
    private void getProviderData() {
        ApiInterface.ApiFactory.createApi().getAllProvider().enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                providerBeans = GsonUtils.fromJsonList(result, ProviderBean.class);
                if (providerBeans.size() > 0) {
                    for (int i = 0; i < providerBeans.size(); i++) {
                        providerList.add(providerBeans.get(i).getRealName());
                    }
                }
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
