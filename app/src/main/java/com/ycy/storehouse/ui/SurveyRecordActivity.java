package com.ycy.storehouse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.ycy.storehouse.R;
import com.ycy.storehouse.adapter.FullyGridLayoutManager;
import com.ycy.storehouse.adapter.GridImageAdapter;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.Contents;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.entity.CategoryBean;
import com.ycy.storehouse.entity.StoreBean;
import com.ycy.storehouse.entity.UnitBean;
import com.ycy.storehouse.listener.OnLubanFinishListener;
import com.ycy.storehouse.utils.DialogUtils;
import com.ycy.storehouse.utils.GsonUtils;
import com.ycy.storehouse.utils.SharedPreferencesUtils;
import com.ycy.storehouse.utils.ToastUtil;
import com.ycy.storehouse.utils.Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;


/**
 * 调查记录
 * */
public class SurveyRecordActivity extends BaseActivity implements View.OnClickListener {

    private final static String TAG = SurveyRecordActivity.class.getSimpleName();

    @BindView(R.id.tv_back)
    TextView tvBack;
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
    EditText etReamrks;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private int maxSelectNum = 9;
    private ListPopupWindow mListPop;

    //单位数据
    private List<UnitBean> unitBeans = new ArrayList<>();
    private List<String> nameList = new ArrayList<>();


    //分类数据
    private List<CategoryBean> categoryBeans = new ArrayList<>();
    List<String> content1 = new ArrayList<>();
    List<List<String>> content2 = new ArrayList<>();
    List<List<List<String>>> content3 = new ArrayList<>();

    private int optionId;//分类id
    private int unitId;//单位id

    private int surveyId;//调研id

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_survey_record;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("添加调研记录");
        //取消
        tvBack.setOnClickListener(v -> finish());

        getUnitData();
        getDataCategory();
        etAddress.setText(String.valueOf(SharedPreferencesUtils.getSp(Contents.LOCATION_NAME,"")));
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((position, v) -> {
            if (selectList.size() > 0) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                switch (mediaType) {
                    case 1:
                        // 预览图片 可自定长按保存路径
                        //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                        PictureSelector.create(SurveyRecordActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.et_unit, R.id.tv_ensure,R.id.et_category})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_category://分类
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(SurveyRecordActivity.this, (options1, option2, options3, v1) -> {
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
            case R.id.et_unit://单位列表
                mListPop = new ListPopupWindow(this);
                mListPop.setAdapter(new ArrayAdapter<String>(this, R.layout.item_text, nameList));
                mListPop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                mListPop.setHeight(300);
                mListPop.setAnchorView(etUnit);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
                mListPop.setModal(true);//设置是否是模式
                mListPop.setOnItemClickListener((parent, view1, position, id) -> {
                    etUnit.setText(nameList.get(position));
                    unitId = unitBeans.get(position).getId();
                    mListPop.dismiss();
                });
                mListPop.show();
                break;
            case R.id.tv_ensure:

                if (etName.getText().toString() == null) {
                    ToastUtil.showToast(this, "请输入名称");
                    return;
                }
                if (etCategory.getText().toString() == null) {
                    ToastUtil.showToast(this, "请选择分类");
                    return;
                }
                if (etGoodsName.getText().toString() == null) {
                    ToastUtil.showToast(this, "请输入商品名称");
                    return;
                }
                if (etShopName.getText().toString() == null) {
                    ToastUtil.showToast(this, "请输入商家名称");
                    return;
                }
                if (etPrice.getText().toString() == null) {
                    ToastUtil.showToast(this, "请输入货物价格");
                    return;
                }
                if (etUnit.getText().toString() == null) {
                    ToastUtil.showToast(this, "请选择单位");
                    return;
                }
                if (etAddress.getText().toString() == null) {
                    ToastUtil.showToast(this, "请输入地址");
                    return;
                }

               getInfoData(etName.getText().toString(), etAddress.getText().toString(),
                        etReamrks.getText().toString(), Integer.parseInt(etPrice.getText().toString()),
                       unitId,optionId,etGoodsName.getText().toString(),etShopName.getText().toString());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        Log.i("图片1-----》", media.getPath());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = () ->
            DialogUtils.getInstance().showBottomDialog(SurveyRecordActivity.this,
                    R.layout.dialog_photo_bottom, (contentView, utils) -> {
                        TextView tvTakePhoto = (TextView) contentView.findViewById(R.id.tv_take_photo);
                        TextView tvPhoto = (TextView) contentView.findViewById(R.id.tv_photo);
                        TextView tvCancel = (TextView) contentView.findViewById(R.id.tv_cancel);
                        tvTakePhoto.setOnClickListener(v -> {
                            //拍照
                            PictureSelector.create(SurveyRecordActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .selectionMedia(selectList)// 是否传入已选图片
                                    .forResult(PictureConfig.CHOOSE_REQUEST);

                            utils.close();
                        });
                        tvPhoto.setOnClickListener(v -> {
                            //图库选择
                            PictureSelector.create(SurveyRecordActivity.this)
                                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                                    .minSelectNum(1)// 最小选择数量
                                    .imageSpanCount(4)// 每行显示个数
                                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                                    .previewImage(true)// 是否可预览图片
                                    .isCamera(true)// 是否显示拍照按钮
                                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                                    .compress(true)// 是否压缩
                                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                                    .glideOverride(100, 100)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                                    .selectionMedia(selectList)// 是否传入已选图片
                                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                            utils.close();
                        });
                        tvCancel.setOnClickListener(v -> utils.close());
                    });

    @Override
    public void onClick(View v) {

    }

    /**
     * 添加信息
     */
    private void getInfoData(String name, String address, String remarks, int price,
                             int unitId,int categoryId,String goodsName,String shopName) {
        ApiInterface.ApiFactory.createApi().addSurveyInfo(name, address, remarks,
                price,unitId,categoryId,goodsName,shopName).enqueue(new ResultCallBack(this)        {
            @Override
            public void onResponse(String msg, String result) {
                if(result!=null && result !="") {
                    surveyId = Integer.parseInt(result);
                    List<String> files = new ArrayList<>();
                    for(int i=0;i<selectList.size();i++){
                        files.add(selectList.get(i).getPath());
                    }
                    if(files.size()>0){
                        //上传图片
                        Tools.upLoadFile(SurveyRecordActivity.this, files, params -> uploadFile(params));
                    }
                    //ToastUtil.showToast(SurveyRecordActivity.this, "添加成功");
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
                        nameList.add(unitBeans.get(i).getName());
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
                ToastUtil.showToast(SurveyRecordActivity.this, "查询失败");
            }
        });
    }

    /**
     * 上传图片
     * */
    private void uploadFile(Map<String, RequestBody> params){
        ApiInterface.ApiFactory.createApi().uploadFile(params).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                if(result!=null && result!=""){
                    uploadSurvey(result, surveyId);
                }
                Log.e("picture---->",result);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 添加图片
     * */
    private void uploadSurvey(String sUrl,int id){
        ApiInterface.ApiFactory.createApi().updateSurveyInfo(sUrl,id).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                ToastUtil.showToast(SurveyRecordActivity.this,result);
                finish();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
