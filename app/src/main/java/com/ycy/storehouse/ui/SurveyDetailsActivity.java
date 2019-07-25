package com.ycy.storehouse.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.ycy.storehouse.R;
import com.ycy.storehouse.adapter.SurveyDetailsAdapter;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.entity.SurveyDetailsBean;
import com.ycy.storehouse.listener.OnItemClickListener;
import com.ycy.storehouse.utils.GsonUtils;
import com.ycy.storehouse.utils.ToastUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 调查详情
 * */
public class SurveyDetailsActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_category)
    EditText etCategory;
    @BindView(R.id.et_shop_name)
    EditText etShopName;
    @BindView(R.id.et_goods_name)
    EditText etGoodsName;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_unit)
    EditText etUnit;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ll_pic)
    LinearLayout llPic;

    private SurveyDetailsAdapter detailsAdapter;

    private SurveyDetailsBean surveyBean;
    private int id;
    private List<String> urlList = new ArrayList<>();
    private List<LocalMedia> selectList = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_survey_details;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("详情");
        id = getIntent().getIntExtra("id", 0);
        getDdataList(id);

        detailsAdapter = new SurveyDetailsAdapter(this, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(detailsAdapter);
    }

    /**
     * 数据信息
     */
    private void getDdataList(int id) {
        ApiInterface.ApiFactory.createApi().selectInfo(id).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                surveyBean = GsonUtils.jsonToBean(result, SurveyDetailsBean.class);

                etName.setText(surveyBean.getName());
                etCategory.setText(surveyBean.getCategoryName());
                etShopName.setText(surveyBean.getShopName());
                etGoodsName.setText(surveyBean.getGoodsName());
                etPrice.setText(surveyBean.getPrice() + "");
                etUnit.setText(surveyBean.getUnitName());
                etAddress.setText(surveyBean.getAddress());
                etRemarks.setText(surveyBean.getRemarks());
                if (surveyBean.getSUrl() != null && !"".equals(surveyBean.getSUrl())) {
                    Type type = new TypeToken<List<String>>() {
                    }.getType();
                    urlList = new Gson().fromJson(surveyBean.getsUrl(), type);
                    for(int i=0;i<urlList.size();i++){
                        LocalMedia localMedia = new LocalMedia();
                        localMedia.setPath(urlList.get(i));
                        selectList.add(localMedia);
                    }
                    detailsAdapter.setDatas(urlList);
                } else {
                    llPic.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.showToast(SurveyDetailsActivity.this, "查询失败");
            }
        });
    }

    @Override
    public void onItemClick(View view, int position, int viewtype) {
        PictureSelector.create(SurveyDetailsActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList);
    }
}
