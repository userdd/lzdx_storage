package com.ycy.storehouse.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ycy.storehouse.R;
import com.ycy.storehouse.adapter.BarCodeAdapter;
import com.ycy.storehouse.base.ApiInterface;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.base.ResultCallBack;
import com.ycy.storehouse.entity.BarCodeBean;
import com.ycy.storehouse.entity.EnterStoreBean;
import com.ycy.storehouse.entity.OutStoreBean;
import com.ycy.storehouse.listener.OnItemClickListener;
import com.ycy.storehouse.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * 二维码扫描
 * */
public class BarCodeActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, OnItemClickListener {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_specs)
    TextView tvSpecs;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_update_time)
    TextView tvUpdateTime;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_remarks)
    TextView tvRemarks;
    @BindView(R.id.tv_warn)
    TextView tvWarn;
    @BindView(R.id.tv_provider_name)
    TextView tvProviderName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_stock)
    TextView tvStock;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    public static final int REQUEST_CODE_SCAN = 0X01;
    public static final int RC_CAMERA = 0X01;
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_IS_CONTINUOUS = "key_continuous_scan";
    private boolean isContinuousScan = false;

    private BarCodeBean barCodeBean;
    private List<EnterStoreBean.ListBean> enterList = new ArrayList<>();
    private List<OutStoreBean.ListBean> outList = new ArrayList<>();
    private List<String> tabList = new ArrayList<>();

    private BarCodeAdapter barCodeAdapter;
    private int id;

    private EnterStoreBean enterStoreBean;
    private OutStoreBean outStoreBean;

    private int PAGE_ENTER = 1;
    private int PAGE_OUT = 1;
    private int NUM_ENTER = 15;
    private int NUM_OUT = 15;
    private boolean isClick = false;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_bar_code;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initTitle("扫描条码");
        checkCameraPermissions();

        tabList.add("入库记录");
        tabList.add("出库记录");
        for (int i = 0; i < tabList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabList.get(i)));
        }

        barCodeAdapter = new BarCodeAdapter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(barCodeAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        barCodeAdapter.setDatas(enterList);
                        isClick = false;
                        break;
                    case 1:
                        barCodeAdapter.setDatas(outList);
                        isClick = true;
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            if(isClick){
                outList.clear();
                PAGE_OUT = 1;
                getOutInfo(id,PAGE_OUT,NUM_OUT);
            }else {
                enterList.clear();
                PAGE_ENTER = 1;
                getEnterInfo(id,PAGE_ENTER,NUM_ENTER);
            }


            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if(isClick){
                if(outStoreBean.isHasNextPage()){
                    getOutInfo(id,PAGE_OUT,NUM_OUT);
                }else{
                    refreshLayout.finishLoadMore();
                }
            }else{
                if(enterStoreBean.isHasNextPage()) {
                    getEnterInfo(id,PAGE_ENTER,NUM_ENTER);
                }else{
                    refreshLayout.finishLoadMore();
                }
            }

        });

        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {

            if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                refreshLayout.setEnableLoadMore(true);
            }else{
                refreshLayout.setEnableLoadMore(false);
            }
        });
    }

    /**
     * 检测拍摄权限
     */
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(){
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {//有权限
            startScan(CaptureActivity.class,"扫描条码");
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "请允许打开摄像头扫码",
                    RC_CAMERA, perms);
        }
    }

    /**
     * 扫码
     * @param cls
     * @param title
     */
    private void startScan(Class<?> cls,String title){
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(this,R.anim.in,R.anim.out);
        Intent intent = new Intent(this, cls);
        intent.putExtra(KEY_TITLE,title);
        intent.putExtra(KEY_IS_CONTINUOUS,isContinuousScan);
        ActivityCompat.startActivityForResult(this,intent,REQUEST_CODE_SCAN,optionsCompat.toBundle());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null){
            switch (requestCode){
                case REQUEST_CODE_SCAN:
                    String result = data.getStringExtra(Intents.Scan.RESULT);
                    int code = Integer.parseInt(result.substring(6,result.length()));
                    id = code;
                    getData(code);
                    break;
                /*case REQUEST_CODE_PHOTO:
                    parsePhoto(data);
                    break;*/
            }

        }else{
            coordinatorLayout.setVisibility(View.GONE);
            emptyRoot.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 商品信息
     * */
    private void getData(int id){
        ApiInterface.ApiFactory.createApi().goodsInfo(id).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                barCodeBean = GsonUtils.jsonToBean(result,BarCodeBean.class);
                if(barCodeBean!=null){
                    coordinatorLayout.setVisibility(View.VISIBLE);
                    tvName.setText(barCodeBean.getName());
                    tvSpecs.setText(barCodeBean.getGSpecs());
                    tvTime.setText(barCodeBean.getCreateTime().substring(0,10));
                    tvUpdateTime.setText(barCodeBean.getUpdateTime().substring(0,10));
                    tvPosition.setText(barCodeBean.getPosition());
                    tvStoreName.setText(barCodeBean.getStoreName());
                    tvRemarks.setText(barCodeBean.getGRemarks());
                    tvWarn.setText(barCodeBean.getMinWarn()+"");
                    tvProviderName.setText(barCodeBean.getProviderName());
                    tvUnit.setText(barCodeBean.getProviderName());
                    tvPrice.setText(barCodeBean.getPrice()+"");
                    tvStock.setText(barCodeBean.getStoreName());
                    tvCategory.setText(barCodeBean.getCateName());
                    getEnterInfo(id,PAGE_ENTER,NUM_ENTER);
                    getOutInfo(id,PAGE_OUT,NUM_OUT);
                }else{
                    coordinatorLayout.setVisibility(View.GONE);
                    emptyRoot.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 入库记录
     * */
    private void getEnterInfo(int id,int page,int size){
        ApiInterface.ApiFactory.createApi().enterInfoList(id,page,size).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                if(refreshLayout!=null){
                    refreshLayout.finishLoadMore();
                }
                Log.e("----------------->",result);
                enterStoreBean = GsonUtils.jsonToBean(result,EnterStoreBean.class);
                if(enterStoreBean.getList().size()>0) {
                    enterList.addAll(enterStoreBean.getList());
                    barCodeAdapter.setDatas(enterList);
                    PAGE_ENTER++;
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 出库记录
     * */
    private void getOutInfo(int id,int page,int size){
        ApiInterface.ApiFactory.createApi().outInfoList(id,page,size).enqueue(new ResultCallBack(this) {
            @Override
            public void onResponse(String msg, String result) {
                if(refreshLayout!=null){
                    refreshLayout.finishLoadMore();
                }
               outStoreBean = GsonUtils.jsonToBean(result,OutStoreBean.class);
                if(outStoreBean.getList().size()>0) {
                    outList.addAll(outStoreBean.getList());
                    barCodeAdapter.setDatas(outList);
                    PAGE_OUT++;
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onItemClick(View view, int position, int viewtype) {

    }
}
