package com.ycy.storehouse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ycy.storehouse.R;
import com.ycy.storehouse.adapter.StoreAdapter;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.entity.CategoryBean;
import com.ycy.storehouse.entity.StoreBean;
import com.ycy.storehouse.entity.StoreGoodsBean;
import com.ycy.storehouse.listener.OnItemClickListener;
import com.ycy.storehouse.utils.GsonUtils;
import com.ycy.storehouse.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 出库记录
 * */
public class StoreActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R.id.et_store)
    EditText etStore;
    @BindView(R.id.et_category)
    EditText etCategory;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout refreshLayout;


    private StoreAdapter storeAdapter;
    private List<StoreGoodsBean.ListBean> listBeans = new ArrayList<>();
    private List<StoreBean> storeBeans = new ArrayList<>();
    private List<String> nameList = new ArrayList<>();
    private ListPopupWindow mListPop;
    private StoreGoodsBean storeGoodsBean;

    private int PAGE = 1;
    private int PAGE_NUM = 10;

    //分类数据
    private List<CategoryBean> categoryBeans = new ArrayList<>();
    List<String> content1 = new ArrayList<>();
    List<List<String>> content2 = new ArrayList<>();
    List<List<List<String>>> content3 = new ArrayList<>();

    private int optionId;//分类id

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_store;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("库存管理");
        tvTitleRight.setText("添加");
        tvTitleRight.setTextColor(getResources().getColor(R.color.color_white));
        tvTitleRight.setOnClickListener(v ->
                startActivity(new Intent(StoreActivity.this, EnterAddGoodsActivity.class)));

        storeAdapter = new StoreAdapter(this,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(storeAdapter);

        getStoreData();
        getData(PAGE,PAGE_NUM,"",null,null);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            PAGE = 1;
            listBeans.clear();
            getData(PAGE,PAGE_NUM,"",null,null);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if(storeGoodsBean!=null) {
                if (storeGoodsBean.isHasNextPage()) {
                    getData(PAGE, PAGE_NUM, "", null, null);
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });

        getDataCategory();
    }

   @OnClick({R.id.et_store,R.id.et_category,R.id.iv_search})
   public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.et_store://选择库房
                mListPop = new ListPopupWindow(this);
                mListPop.setAdapter(new ArrayAdapter<String>(this, R.layout.item_text, nameList));
                mListPop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                mListPop.setHeight(300);
                mListPop.setAnchorView(etStore);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
                mListPop.setModal(true);//设置是否是模式
                mListPop.setOnItemClickListener((parent, view, position, id) -> {
                    etStore.setText(nameList.get(position));
                    PAGE = 1;
                    PAGE_NUM = 10;
                    listBeans.clear();
                    getData(PAGE,PAGE_NUM,"",null,storeBeans.get(position).getId());
                    mListPop.dismiss();
                });
                mListPop.show();
                break;
            case R.id.et_category://选择分类
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(StoreActivity.this, (options1, option2, options3, v1) -> {
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
                    listBeans.clear();
                    getData(PAGE,PAGE_NUM,null,optionId,null);
                }).build();
                pvOptions.setPicker(content1, content2, content3);
                pvOptions.show();
                break;
            case R.id.iv_search://搜索
                listBeans.clear();
                PAGE = 1;
                PAGE_NUM = 10;
                getData(PAGE,PAGE_NUM,etSearch.getText().toString(),null,null);
                break;
        }
   }

    @Override
    public void onItemClick(View view, int position, int viewtype) {
        switch (viewtype){
            case 0://盘库
                Intent intent  = new Intent(this,CheckActivity.class);
                intent.putExtra("id",listBeans.get(position).getId());
                intent.putExtra("name",listBeans.get(position).getName());
                intent.putExtra("cateName",listBeans.get(position).getCateName());
                intent.putExtra("storeName",listBeans.get(position).getStoreName());
                intent.putExtra("price",listBeans.get(position).getPrice());
                intent.putExtra("unit",listBeans.get(position).getUnitName());
                intent.putExtra("stock",listBeans.get(position).getStock()+"");
                intent.putExtra("unitId",listBeans.get(position).getUnitId());
                intent.putExtra("storeId",listBeans.get(position).getStoreId());
                intent.putExtra("providerId",listBeans.get(position).getProviderId());
                intent.putExtra("lSpecs",listBeans.get(position).getGSpecs());
                startActivity(intent);
                break;
            case 1://入库
                Intent intentEnter = new Intent(this,EnterGoodsActivity.class);
                intentEnter.putExtra("id",listBeans.get(position).getId());
                intentEnter.putExtra("name",listBeans.get(position).getName());
                intentEnter.putExtra("cateName",listBeans.get(position).getCateName());
                intentEnter.putExtra("unit",listBeans.get(position).getUnitName());
                intentEnter.putExtra("storeName",listBeans.get(position).getStoreName());
                startActivity(intentEnter);
                break;
            case 2://出库
                Intent intentOut = new Intent(this,OutGoodsActivity.class);
                intentOut.putExtra("id",listBeans.get(position).getId());
                intentOut.putExtra("cateId",listBeans.get(position).getCateId());
                intentOut.putExtra("cateName",listBeans.get(position).getCateName());
                startActivity(intentOut);
                break;
        }

    }
    /**
     * 获取仓库
     * */
    private void getStoreData() {
        ApiInterface.ApiFactory.createApi().goodsStore().enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                storeBeans = GsonUtils.fromJsonList(result,StoreBean.class);
                if(storeBeans.size()>0){
                    for(int i=0;i<storeBeans.size();i++){
                        nameList.add(storeBeans.get(i).getName());
                    }
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
    /**
     * 获取物品
     * */
    private void getData(int page,int size,String name,Integer cateId,Integer storeId) {
        ApiInterface.ApiFactory.createApi().goodsInfoList(page,size,name,cateId,storeId).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                refreshLayout.finishLoadMore();
                storeGoodsBean = GsonUtils.jsonToBean(result, StoreGoodsBean.class);
                if(storeGoodsBean!=null && storeGoodsBean.getList().size()>0){
                        listBeans.addAll(storeGoodsBean.getList());
                        storeAdapter.setDatas(listBeans);
                        PAGE++;
                }else {
                    storeAdapter.notifyDataSetChanged();
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
                ToastUtil.showToast(StoreActivity.this, "查询失败");
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        PAGE = 1;
        PAGE_NUM = 10;
        getData(PAGE,PAGE_NUM,"",null,null);
    }
}
