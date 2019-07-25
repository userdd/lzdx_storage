package com.ycy.storehouse.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.ycy.storehouse.R;
import com.ycy.storehouse.adapter.HomeAdapter;
import com.ycy.storehouse.base.BaseFragment;
import com.ycy.storehouse.base.Contents;
import com.ycy.storehouse.listener.OnItemClickListener;
import com.ycy.storehouse.ui.BarCodeActivity;
import com.ycy.storehouse.ui.EnterStoreActivity;
import com.ycy.storehouse.ui.OutStoreActivity;
import com.ycy.storehouse.ui.StoreActivity;
import com.ycy.storehouse.ui.SurveyActivity;
import com.ycy.storehouse.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

import static android.support.constraint.Constraints.TAG;

public class HomeFragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private HomeAdapter homeAdapter;

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        requestLocationPermisson();
        homeAdapter = new HomeAdapter(getActivity(), this);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);

        location();
    }

    private void location() {
        mLocationClient = new LocationClient(getActivity());//声明LocationClient类
        mLocationClient.registerLocationListener(myListener);//注册监听函数

        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(120000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5*60*1000);
        option.setEnableSimulateGps(false);

        mLocationClient.setLocOption(option);

        mLocationClient.start();


    }




    @TargetApi(23)
    public void requestLocationPermisson() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 100);

    }


    @Override
    public void onItemClick(View view, int position, int viewtype) {
        switch (position) {
            case 0://扫描条码
                startActivity(new Intent(getActivity(), BarCodeActivity.class));
                break;
            case 1://库存管理
                startActivity(new Intent(getActivity(), StoreActivity.class));
                break;
            case 2://入库管理
                startActivity(new Intent(getActivity(), EnterStoreActivity.class));
                break;
            case 3://出库管理
                startActivity(new Intent(getActivity(), OutStoreActivity.class));
                break;
            case 4://市场调研
                startActivity(new Intent(getActivity(), SurveyActivity.class));
                break;

        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            String addr = location.getAddrStr();    //获取详细地址信息
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String address = location.getStreet();    //获取详细地址信息
            SharedPreferencesUtils.saveSp(Contents.LOCATION_NAME,city+district+address);
            if(TextUtils.isEmpty(city)){
                location();
            }
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息

        }
    }


}
