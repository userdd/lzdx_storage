package com.ycy.storehouse.utils;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ycy.storehouse.R;
import com.ycy.storehouse.adapter.BarCodeAdapter;
import com.ycy.storehouse.base.Contents;
import com.ycy.storehouse.entity.BalanceBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BalanceUtils {
    private final static String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";   //SPP服务UUID号
    private BluetoothAdapter _bluetooth = BluetoothAdapter.getDefaultAdapter();    //获取本地蓝牙适配器，即蓝牙设备
    private final static int REQUEST_CONNECT_DEVICE = 1;    //宏定义查询设备句柄
    BluetoothSocket _socket = null;      //蓝牙通信socket
    BluetoothDevice _device = null;     //蓝牙设备
    boolean bRun = true;
    private InputStream is;    //输入流，用来接收蓝牙数据
    boolean bThread = false;
    private String fmsg = "";    //保存用数据缓存
    private String smsg = "";    //显示用数据缓存

    private String result;

    private byte[] buffer_new;

    private Activity context;
    private List<BalanceBean> decList = new ArrayList<>();
    private boolean isWeight;
    private DeviceListAdapter listAdapter = new DeviceListAdapter();

    public BalanceUtils(Activity context) {
        this.context = context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                context.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0xb01);
            }
        }
        //if(TextUtils.isEmpty(String.valueOf(SharedPreferencesUtils.getSp(Contents.BLUETOOTH,"")))) {
            context.registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));

            context.registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            //filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);   // 注册查找结束action接收器
            context.registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));

            context.registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED));

            BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
            if (btAdapter == null) {
                Toast.makeText(context, context.getResources().getString(R.string.unsupportedbluetooth), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!btAdapter.isEnabled()) {
                Toast.makeText(context, context.getResources().getString(R.string.unenablebluetooth), Toast.LENGTH_SHORT).show();
                return;
            }
            btAdapter.startDiscovery();
        //}
    }

    /**
     * 连接电子秤
     */
    public void connect() {
        if (_bluetooth.isEnabled() == false) {  //如果蓝牙服务不可用则提示
            Toast.makeText(context, " 打开蓝牙中...", Toast.LENGTH_LONG).show();
            return;
        }
        if (_socket == null) {
            if(decList.size()<=0){
                String bluetooth = String.valueOf(SharedPreferencesUtils.getSp(Contents.BLUETOOTH,""));
                if(!TextUtils.isEmpty(bluetooth)){
                    decList = GsonUtils.fromJsonList(bluetooth,BalanceBean.class);
                }
            }
            new AlertDialog.Builder(context).setTitle(R.string.selectbondeddevice)
                    .setAdapter(listAdapter, new DeviceListItemClicker()).show();//*/
            isWeight = false;
        }else{
            fmsg = "";
            isWeight = true;
            readThread.start();
        }
    }

    /**
     * 关闭服务
     */
    public void close() {
        if (readThread != null) {
            readThread.interrupt();
        }
        if(_socket!=null){
            try {
                _socket.close();
                _socket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(mReceiver!=null) {
            context.unregisterReceiver(mReceiver);
        }
    }


    //接收数据线程
    Thread readThread = new Thread() {

        public void run() {
            int num = 0;
            byte[] buffer = new byte[1024];
            buffer_new = new byte[1024];
            int i = 0;
            int n = 0;
            bRun = true;
            //接收线程
            while (true) {
                try {
                    while (true) {
                        if (!bThread)//跳出循环
                            return;

                        num = is.read(buffer);         //读入数据

                        String s0 = new String(buffer, 0, num);
                        if(isWeight){
                            s0 = "";
                            isWeight = false;
                        }
                        fmsg += s0.replaceAll(" ", "");    //保存收到数据
                        fmsg = fmsg.trim().replaceAll("\\r\\n", "")
                                .replaceAll("\\+0.00kg", "");
                        if (fmsg.length() > 70) {
                            String[] date = fmsg.split("\\+");
                            for (int x = 0; x < date.length; x++) {
                                int count = 0;
                                for (int y = 0; y < date.length; y++) {
                                    if (date[x].equals(date[y])) {
                                        count++;
                                    }
                                }
                                if (count > 5) {
                                    NoticeObserver.getInstance().notifyObservers(Contents.NOTICE_BALANCE, date[x]);
                                    return;
                                }
                            }
                        }
                        if (is.available() == 0) break;  //短时间没有数据才跳出进行显示
                    }
                } catch (IOException e) {
                }
            }
        }
    };

    // 查找到设备和搜索完成action监听器
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    if(BluetoothAdapter.STATE_ON == blueState){
                        Log.e("TAG", "STATE_ON");
                    }
                    break;
                case BluetoothDevice.ACTION_FOUND: // 查找到设备action
                    // 得到蓝牙设备
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // 如果是已配对的则略过，已得到显示，其余的在添加到列表中进行显示
                    if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                        //未配对
                    } else {  //添加到已配对设备列表
                        //mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                        //if(TextUtils.isEmpty(String.valueOf(SharedPreferencesUtils.getSp(Contents.BLUETOOTH,"")))) {
                        if("BT04-A".equals(device.getName())) {
                            if(decList.size()<=0) {
                                decList.add(new BalanceBean(device.getName(), device.getAddress()));
                            }
                            String bluetooth = GsonUtils.listToJson(decList);
                            SharedPreferencesUtils.saveSp(Contents.BLUETOOTH, bluetooth);
                        }
                    }
                    Log.e("---->", device.getName() + "---" + device.getAddress());
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:// 搜索完成action
                    break;
            }
        }
    };

    /**
     * 已配对适配器
     */
    private class DeviceListAdapter extends BaseAdapter {
        private TextView tv_name = null;
        private TextView tv_mac = null;

        @Override
        public int getCount() {
            return decList.size();
        }

        @Override
        public Object getItem(int position) {
            return decList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_printer, null);
            }
            tv_name = (TextView) convertView.findViewById(R.id.tv_device_name);
            tv_mac = (TextView) convertView.findViewById(R.id.tv_macaddress);

            if (decList != null && decList.size() > position) {
                BalanceBean printer = decList.get(position);
                tv_name.setText(printer.getName());
                tv_mac.setText(printer.getAddress());
            }
            return convertView;
        }
    }

    /**
     * 点击事件
     */
    private class DeviceListItemClicker implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            deviceClick(decList.get(which).getAddress());
        }
    }

    /**
     * 连接蓝牙
     */
    private void deviceClick(String mac) {

        // 得到蓝牙设备句柄
        _device = _bluetooth.getRemoteDevice(mac);
        // 用服务号得到socket
        try {
            _socket = _device.createRfcommSocketToServiceRecord(UUID.fromString(MY_UUID));
        } catch (IOException e) {
            Toast.makeText(context, "连接失败！", Toast.LENGTH_SHORT).show();
        }
        //连接socket
        try {
            _socket.connect();
            Toast.makeText(context, "连接" + _device.getName() + "成功！", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            try {
                Toast.makeText(context, "连接失败！", Toast.LENGTH_SHORT).show();
                _socket.close();
                _socket = null;
            } catch (IOException ee) {
                Toast.makeText(context, "连接失败！", Toast.LENGTH_SHORT).show();
            }

            return;
        }

        //打开接收线程
        try {
            is = _socket.getInputStream();   //得到蓝牙数据输入流
        } catch (IOException e) {
            Toast.makeText(context, "接收数据失败！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bThread == false) {
            readThread.start();
            bThread = true;
        } else {
            bRun = true;
        }
    }
}
