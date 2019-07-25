package com.ycy.storehouse.ui;

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
 * 出库管理
 * */
public class OutStoreActivity extends BaseActivity implements OnItemClickListener {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout refreshLayout;

    private List<OutStoreBean.ListBean> outList = new ArrayList<>();
    private BarCodeAdapter barCodeAdapter;

    private int PAGE = 1;
    private int PAGE_NUM = 15;

    private OutStoreBean outStoreBean;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_out_store;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("出库记录");


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
            outList.clear();
            getOutInfo(PAGE,PAGE_NUM);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if(outStoreBean!=null) {
                if (outStoreBean.isHasNextPage()) {
                    getOutInfo(PAGE, PAGE_NUM);
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });
        getOutInfo(PAGE,PAGE_NUM);

    }


    private void getOutInfo(int page,int size){
        ApiInterface.ApiFactory.createApi().outInfoList(page,size).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                refreshLayout.finishLoadMore();
                outStoreBean = GsonUtils.jsonToBean(result,OutStoreBean.class);
                if(outStoreBean.getList().size()>0) {
                    outList.addAll(outStoreBean.getList());
                    barCodeAdapter.setDatas(outList);
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
