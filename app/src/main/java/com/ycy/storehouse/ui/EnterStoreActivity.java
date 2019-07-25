package com.ycy.storehouse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ycy.storehouse.R;
import com.ycy.storehouse.adapter.BarCodeAdapter;
import com.ycy.storehouse.adapter.StoreAdapter;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.entity.EnterStoreBean;
import com.ycy.storehouse.entity.OutStoreBean;
import com.ycy.storehouse.entity.StoreGoodsBean;
import com.ycy.storehouse.listener.OnItemClickListener;
import com.ycy.storehouse.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 入库记录
 * */
public class EnterStoreActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout refreshLayout;

    private List<EnterStoreBean.ListBean> enterList = new ArrayList<>();
    private BarCodeAdapter barCodeAdapter;
    private EnterStoreBean enterStoreBean;
    private int PAGE = 1;
    private int PAGE_NUM = 15;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_enter_store;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("入库记录");

        barCodeAdapter = new BarCodeAdapter(this,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(barCodeAdapter);

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            PAGE = 1;
            enterList.clear();
            getEnterInfo(PAGE,PAGE_NUM);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if(enterStoreBean!=null) {
                if (enterStoreBean.isHasNextPage()) {
                    getEnterInfo(PAGE, PAGE_NUM);
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });
        getEnterInfo(PAGE,PAGE_NUM);
    }


    private void getEnterInfo(int page,int size){
        ApiInterface.ApiFactory.createApi().enterInfoList(page,size).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                Log.e("----------------->",result);
                if(refreshLayout !=null){
                    refreshLayout.finishLoadMore();
                }
                enterStoreBean = GsonUtils.jsonToBean(result,EnterStoreBean.class);
                if(enterStoreBean.getList().size()>0) {
                    enterList.addAll(enterStoreBean.getList());
                    barCodeAdapter.setDatas(enterList);
                    PAGE++;
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void onItemClick(View view, int position, int viewtype) {

    }
}
