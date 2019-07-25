package com.ycy.storehouse.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.ycy.storehouse.R;
import com.ycy.storehouse.base.BaseActivity;
import com.ycy.storehouse.utils.BalanceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


/**
 * 电子秤测试
 * */
public class Test2Activity extends BaseActivity {

    /*private final static String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";   //SPP服务UUID号
    private BluetoothAdapter _bluetooth = BluetoothAdapter.getDefaultAdapter();    //获取本地蓝牙适配器，即蓝牙设备
    private final static int REQUEST_CONNECT_DEVICE = 1;    //宏定义查询设备句柄
    BluetoothSocket _socket = null;      //蓝牙通信socket
    BluetoothDevice _device = null;     //蓝牙设备
    boolean bRun = true;
    private InputStream is;    //输入流，用来接收蓝牙数据
    boolean bThread = false;
    private String fmsg = "";    //保存用数据缓存
    private String smsg = "";    //显示用数据缓存
    private String result;*/

    private BalanceUtils balanceUtils;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_test2;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        balanceUtils = new BalanceUtils(this);
    }

    //连接按键响应函数
    public void onConnectButtonClicked(View v){
        balanceUtils.connect();
       /* if(_bluetooth.isEnabled()==false){  //如果蓝牙服务不可用则提示
            Toast.makeText(this, " 打开蓝牙中...", Toast.LENGTH_LONG).show();
            return;
        }


        //如未连接设备则打开DeviceListActivity进行设备搜索
        Button btn = (Button) findViewById(R.id.BtnConnect);
        if(_socket==null){
            Intent serverIntent = new Intent(this, DeviceListActivity.class); //跳转程序设置
            startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);  //设置返回宏定义
        }
        else{
            //关闭连接socket
            try{
                bRun = false;
                Thread.sleep(2000);

                is.close();
                _socket.close();
                _socket = null;

                btn.setText("连接");
            }catch(IOException e){}
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return;*/
    }
    //接收活动结果，响应startActivityForResult()
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       // balanceUtils.requestResult(requestCode,resultCode,data);
       /* switch(requestCode){
            case REQUEST_CONNECT_DEVICE:     //连接结果，由DeviceListActivity设置返回
                // 响应返回结果
                if (resultCode == Activity.RESULT_OK) {   //连接成功，由DeviceListActivity设置返回
                    // MAC地址，由DeviceListActivity设置返回
                    String address = data.getExtras()
                            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    // 得到蓝牙设备句柄
                    _device = _bluetooth.getRemoteDevice(address);

                    // 用服务号得到socket
                    try{
                        _socket = _device.createRfcommSocketToServiceRecord(UUID.fromString(MY_UUID));
                    }catch(IOException e){
                        Toast.makeText(this, "连接失败！", Toast.LENGTH_SHORT).show();
                    }
                    //连接socket
                    Button btn = (Button) findViewById(R.id.BtnConnect);
                    try{
                        _socket.connect();
                        Toast.makeText(this, "连接"+_device.getName()+"成功！", Toast.LENGTH_SHORT).show();
                        btn.setText("断开");
                    }catch(IOException e){
                        try{
                            Toast.makeText(this, "连接失败！", Toast.LENGTH_SHORT).show();
                            _socket.close();
                            _socket = null;
                        }catch(IOException ee){
                            Toast.makeText(this, "连接失败！", Toast.LENGTH_SHORT).show();
                        }

                        return;
                    }

                    //打开接收线程
                    try{
                        is = _socket.getInputStream();   //得到蓝牙数据输入流
                    }catch(IOException e){
                        Toast.makeText(this, "接收数据失败！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(bThread==false){
                        readThread.start();
                        bThread=true;
                    }else{
                        bRun = true;
                    }
                }
                break;
            default:break;
        }*/
    }

   /* //接收数据线程
    Thread readThread=new Thread(){

        public void run(){
            int num = 0;
            byte[] buffer = new byte[1024];
            byte[] buffer_new = new byte[1024];
            int i = 0;
            int n = 0;
            bRun = true;
            //接收线程
            while(true){
                try{
                    while(is.available()==0){
                        while(bRun == false){}
                    }
                    while(true){
                        if(!bThread)//跳出循环
                            return;

                        num = is.read(buffer);         //读入数据
                        n=0;

                        //String s0 = new String(buffer,0,num);
                        //fmsg+=s0;    //保存收到数据
                        for(i=0;i<num;i++){
                            if((buffer[i] == 0x0d)&&(buffer[i+1]==0x0a)){
                                buffer_new[n] = 0x0a;
                                i++;
                            }else{
                                buffer_new[n] = buffer[i];
                            }
                            n++;
                        }
                        //String s = new String(buffer_new,0,n);
                        String s = new String(buffer_new);
                        smsg = s;   //写入接收缓存
                        if(is.available()==0)break;  //短时间没有数据才跳出进行显示
                    }

                    if(smsg.length()>3) {
                        String str = smsg.substring(smsg.indexOf("+")+1);
                        int indexS = str.indexOf("kg");
                        String strs = str.substring(0, indexS);
                        result = strs;
                        try {
                            Thread.sleep(3000);
                            //发送显示消息，进行显示刷新
                            handler.sendMessage(handler.obtainMessage());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }catch(IOException e){
                }
            }
        }
    };
    //消息处理队列
    Handler handler= new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            Log.e("-------------------->",result);
           // Log.e("++++++++++++++++++++>",fmsg);

        }
    };*/
}
