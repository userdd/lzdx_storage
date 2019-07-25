package com.ycy.storehouse.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ycy.storehouse.LoginActivity;
import com.ycy.storehouse.MainActivity;
import com.ycy.storehouse.R;
import com.ycy.storehouse.adapter.CategroyAdapter;
import com.ycy.storehouse.adapter.SurveyAdapter;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.entity.CategoryBean;
import com.ycy.storehouse.entity.SurveyBean;
import com.ycy.storehouse.listener.OnItemClickListener;
import com.ycy.storehouse.utils.GsonUtils;
import com.ycy.storehouse.utils.ToastUtil;
import com.ycy.storehouse.utils.Tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 调查
* */
public class SurveyActivity extends BaseActivity implements OnItemClickListener,CategroyAdapter.OnItemClickListener {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.et_category)
    EditText etCategory;
    @BindView(R.id.tv_time_left)
    TextView tvTimeLeft;
    @BindView(R.id.tv_time_right)
    TextView tvTimeRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout refreshLayout;
    private int PAGE = 1;
    private int PAGE_NUM = 15;



    private Calendar selectedDate;
    private Calendar startDate;
    private Calendar endDate;


    private CategroyAdapter adapter;
    private SurveyAdapter surveyAdapter;
    private SurveyBean surveyBean;
    private List<SurveyBean.ListBean> surveyBeans = new ArrayList<>();

    private List<CategoryBean> categoryBeans = new ArrayList<>();

    List<String> content1 = new ArrayList<>();
    List<List<String>> content2 = new ArrayList<>();
    List<List<List<String>>> content3 = new ArrayList<>();

    private int optionId;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_survey;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("市场调研");
        tvTitleRight.setText("添加");
        tvTitleRight.setTextColor(getResources().getColor(R.color.color_white));
        tvTitleRight.setOnClickListener(v ->
                startActivity(new Intent(SurveyActivity.this, SurveyRecordActivity.class)));

        selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        startDate.set(2019, 0, 1);
        endDate.set(2099, 11, 31);



        surveyAdapter = new SurveyAdapter(this,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(surveyAdapter);
        getDataCategory();
        getDataList(PAGE,PAGE_NUM,null,null,null,null);

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            PAGE = 1;
            surveyBeans.clear();
            getDataList(PAGE,PAGE_NUM,null,null,null,null);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if(surveyBean.isHasNextPage()) {
                getDataList(PAGE,PAGE_NUM,null,null,null,null);
            }else{
                refreshLayout.finishLoadMore();
            }
        });
    }

    @OnClick({R.id.iv_search,R.id.et_category,R.id.tv_time_left, R.id.tv_time_right})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_search://搜索
                String searchContent = etSearch.getText().toString();
                surveyBeans.clear();
                getDataList(PAGE,PAGE_NUM,searchContent, null,null,null);
                break;
            case R.id.et_category://分类
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(SurveyActivity.this, (options1, option2, options3, v1) -> {
                    //返回的分别是三个级别的选中位置
                    if(categoryBeans.get(options1).getCategory().size() == 0){
                        etCategory.setText(categoryBeans.get(options1).getName());
                        optionId = categoryBeans.get(options1).getId();
                    }else if(categoryBeans.get(options1).getCategory().get(option2).getCategory().size() == 0){
                        etCategory.setText(categoryBeans.get(options1).getCategory().get(option2).getName());
                        optionId = categoryBeans.get(options1).getCategory().get(option2).getId();
                    }else{
                        etCategory.setText(categoryBeans.get(options1).getCategory().get(option2).getCategory().get(options3).getName());
                        optionId = categoryBeans.get(options1).getCategory().get(option2).getCategory().get(options3).getId();
                    }
                    surveyBeans.clear();
                    getDataList(PAGE,PAGE_NUM,null,optionId,null,null);
                }).build();
                pvOptions.setPicker(content1,content2,content3);
                pvOptions.show();
                break;
            case R.id.tv_time_left://开始时间
                TimePickerView pvTimeLeft = new TimePickerBuilder(this, (date, v13) -> {//选中事件回调
                    tvTimeLeft.setText(Tools.dateToString(date));
                })
                        .isCyclic(true)//是否循环滚动
                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                        .setRangDate(startDate, endDate)//起始终止年月日设定
                        .build();
                pvTimeLeft.show();
                break;
            case R.id.tv_time_right://结束时间
                if(TextUtils.isEmpty(tvTimeLeft.getText().toString())){
                    ToastUtil.showToast(this,"请先选择开始时间");
                    return;
                }
                TimePickerView pvTimeRight = new TimePickerBuilder(this, (date, v12) -> {//选中事件回调
                    if(Tools.timeCompare(Tools.stringToDate(tvTimeLeft.getText().toString()),date)){
                        tvTimeRight.setText(Tools.dateToString(date));
                        surveyBeans.clear();
                        getDataList(PAGE,PAGE_NUM,null,null,tvTimeLeft.getText().toString(),Tools.dateToString(date));
                    }else {
                        ToastUtil.showToast(SurveyActivity.this,"结束时间不能小于开始时间");
                    }
                })
                        .isCyclic(true)//是否循环滚动
                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                        .setRangDate(startDate, endDate)//起始终止年月日设定
                        .build();
                pvTimeRight.show();
                break;
        }
    }

    /**
     * 获取分类
     * */
    private void getDataCategory(){
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
                    if(options.size()>0){
                        content2.add(options);
                    }else{
                        //添加二级空数据
                        options.add("");
                        content2.add(options);
                    }
                    if(contentSun.size()>0) {
                        content3.add(contentSun);
                    }else{
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
                ToastUtil.showToast(SurveyActivity.this,"查询失败");
            }
        });
    }

    /**
     * 数据列表
     * */
    private void getDataList(int page,int size,String name,Integer categoryId,String startTime,String endTime){
        ApiInterface.ApiFactory.createApi().selectSurveyInfo(page,size,name,categoryId,startTime,endTime).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                surveyBean = GsonUtils.jsonToBean(result, SurveyBean.class);
                if(surveyBean.getList().size()>0){
                    surveyBeans.addAll(surveyBean.getList());
                    surveyAdapter.setDatas(surveyBeans);
                }

            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.showToast(SurveyActivity.this,"查询失败");
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }

    /**
     * 列表点击
     * */
    @Override
    public void onItemClick(View view, int position, int viewtype) {
        Intent intent = new Intent(SurveyActivity.this,SurveyDetailsActivity.class);
        intent.putExtra("id",surveyBeans.get(position).getId());
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        PAGE = 1;
        PAGE_NUM = 15;
        getDataList(PAGE,PAGE_NUM,null,null,null,null);
    }
}
