package com.ycy.storehouse.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dothantech.lpapi.LPAPI;
import com.dothantech.printer.IDzPrinter;
import com.ycy.storehouse.R;

import java.util.ArrayList;
import java.util.List;

public class PrintUtil {

    private Activity activity;
    //private Button btnConnectDevice;
    private List<IDzPrinter.PrinterAddress> pairedPrinters = new ArrayList<IDzPrinter.PrinterAddress>();
    private LPAPI api;
    // 用于处理各种通知消息，刷新界面的handler
    private final Handler mHandler = new Handler();
    // 上次连接成功的设备对象
    private IDzPrinter.PrinterAddress mPrinterAddress = null;
    // 状态提示框
    private AlertDialog stateAlertDialog = null;
    //打印参数
    private int printDensity = -1;
    private int printSpeed = -1;
    private int gapType = -1;

    private final LPAPI.Callback mCallback = new LPAPI.Callback() {

        /****************************************************************************************************************************************/
        // 所有回调函数都是在打印线程中被调用，因此如果需要刷新界面，需要发送消息给界面主线程，以避免互斥等繁琐操作。

        /****************************************************************************************************************************************/

        // 打印机连接状态发生变化时被调用
        @Override
        public void onStateChange(IDzPrinter.PrinterAddress arg0, IDzPrinter.PrinterState arg1) {
            final IDzPrinter.PrinterAddress printer = arg0;
            switch (arg1) {
                case Connected:
                case Connected2:
                    // 打印机连接成功，发送通知，刷新界面提示
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onPrinterConnected(printer);
                        }
                    });
                    break;

                case Disconnected:
                    // 打印机连接失败、断开连接，发送通知，刷新界面提示
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onPrinterDisconnected();
                        }
                    });
                    break;

                default:
                    break;
            }
        }

        // 蓝牙适配器状态发生变化时被调用
        @Override
        public void onProgressInfo(IDzPrinter.ProgressInfo arg0, Object arg1) {
        }

        @Override
        public void onPrinterDiscovery(IDzPrinter.PrinterAddress arg0, IDzPrinter.PrinterInfo arg1) {
        }

        // 打印标签的进度发生变化是被调用
        @Override
        public void onPrintProgress(IDzPrinter.PrinterAddress address, Object bitmapData, IDzPrinter.PrintProgress progress, Object addiInfo) {
            switch (progress) {
                case Success:
                    // 打印标签成功，发送通知，刷新界面提示
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onPrintSuccess();
                        }
                    });
                    break;

                case Failed:
                    // 打印标签失败，发送通知，刷新界面提示
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onPrintFailed();
                        }
                    });
                    break;

                default:
                    break;
            }
        }
    };

    public PrintUtil(Activity activity) {
        this.activity = activity;
        this.api = LPAPI.Factory.createInstance(mCallback);

        // 尝试连接上次成功连接的打印机
        if (mPrinterAddress != null) {
            if (api.openPrinterByAddress(mPrinterAddress)) {
                // 连接打印机的请求提交成功，刷新界面提示
                onPrinterConnecting(mPrinterAddress, false);
                return;
            }
        }else{
            selectClick();
        }
    }

    //选择打印机按钮事件
    public void selectClick() {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            Toast.makeText(activity, activity.getResources().getString(R.string.unsupportedbluetooth), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!btAdapter.isEnabled()) {
            Toast.makeText(activity, activity.getResources().getString(R.string.unenablebluetooth), Toast.LENGTH_SHORT).show();
            return;
        }
        pairedPrinters = api.getAllPrinterAddresses(null);
        new AlertDialog.Builder(activity).setTitle(R.string.selectbondeddevice).setAdapter(new DeviceListAdapter(), new DeviceListItemClicker()).show();
    }

    //打印机打印事件
    public void printClick(String textTitle, String code) {
        if (isPrinterConnected()) {
            if (printText1DBarcode(textTitle, code, getPrintParam(1, 90))) {
                onPrintStart();
            } else {
                onPrintFailed();
            }
        }
    }

    // 打印文本一维码
    private boolean printText1DBarcode(String textTitle, String onedBarcde, Bundle param) {

        // 开始绘图任务，传入参数(页面宽度, 页面高度)
        api.startJob(40, 30, 0);

        // 开始一个页面的绘制，绘制文本字符串
        // 传入参数(需要绘制的文本字符串, 绘制的文本框左上角水平位置, 绘制的文本框左上角垂直位置, 绘制的文本框水平宽度, 绘制的文本框垂直高度, 文字大小, 字体风格)
        api.drawText(textTitle, 7, 4, 32, 17, 4);

        // 设置之后绘制的对象内容旋转180度
        //api.setItemOrientation(180);

        // 绘制一维码，此一维码绘制时内容会旋转180度，
        // 传入参数(需要绘制的一维码的数据, 绘制的一维码左上角水平位置, 绘制的一维码左上角垂直位置, 绘制的一维码水平宽度, 绘制的一维码垂直高度)
        api.draw1DBarcode(onedBarcde, LPAPI.BarcodeType.AUTO, 4, 10, 32, 15, 3);

        // 结束绘图任务提交打印
        return api.commitJob();
    }

    // 连接打印机请求成功提交时操作
    private void onPrinterConnecting(IDzPrinter.PrinterAddress printer, boolean showDialog) {
        // 连接打印机请求成功提交，刷新界面提示
        String txt = printer.shownName;
        if (TextUtils.isEmpty(txt))
            txt = printer.macAddress;
        txt = activity.getResources().getString(R.string.nowisconnectingprinter) + '[' + txt + ']';
        txt += activity.getResources().getString(R.string.printer);
        if (showDialog) {
            showStateAlertDialog(txt);
        }
        //打印机名称
        //btnConnectDevice.setText(txt);
    }

    // 连接打印机成功时操作
    private void onPrinterConnected(IDzPrinter.PrinterAddress printer) {
        // 连接打印机成功时，刷新界面提示，保存相关信息
        clearAlertDialog();
        Toast.makeText(activity, activity.getResources().getString(R.string.connectprintersuccess), Toast.LENGTH_SHORT).show();
        mPrinterAddress = printer;
        // 调用LPAPI对象的getPrinterInfo方法获得当前连接的打印机信息
        String txt = activity.getResources().getString(R.string.printer) + activity.getResources().getString(R.string.chinesecolon);
        txt += api.getPrinterInfo().deviceName + "\n";
        txt += api.getPrinterInfo().deviceAddress;
    }

    // 连接打印机操作提交失败、打印机连接失败或连接断开时操作
    private void onPrinterDisconnected() {
        // 连接打印机操作提交失败、打印机连接失败或连接断开时，刷新界面提示
        clearAlertDialog();
        Toast.makeText(activity, activity.getResources().getString(R.string.connectprinterfailed), Toast.LENGTH_SHORT).show();
    }

    // 开始打印标签时操作
    private void onPrintStart() {
        // 开始打印标签时，刷新界面提示
        showStateAlertDialog(R.string.nowisprinting);
    }

    // 标签打印成功时操作
    private void onPrintSuccess() {
        // 标签打印成功时，刷新界面提示
        clearAlertDialog();
        Toast.makeText(activity, activity.getResources().getString(R.string.printsuccess), Toast.LENGTH_SHORT).show();
    }

    // 打印请求失败或标签打印失败时操作
    private void onPrintFailed() {
        // 打印请求失败或标签打印失败时，刷新界面提示
        clearAlertDialog();
        Toast.makeText(activity, activity.getResources().getString(R.string.printfailed), Toast.LENGTH_SHORT).show();
    }

    // 判断当前打印机是否连接
    public boolean isPrinterConnected() {
        // 调用LPAPI对象的getPrinterState方法获取当前打印机的连接状态
        IDzPrinter.PrinterState state = api.getPrinterState();

        // 打印机未连接
        if (state == null || state.equals(IDzPrinter.PrinterState.Disconnected)) {
            Toast.makeText(activity, activity.getResources().getString(R.string.pleaseconnectprinter), Toast.LENGTH_SHORT).show();
            return false;
        }

        // 打印机正在连接
        if (state.equals(IDzPrinter.PrinterState.Connecting)) {
            Toast.makeText(activity, activity.getResources().getString(R.string.waitconnectingprinter), Toast.LENGTH_SHORT).show();
            return false;
        }

        // 打印机已连接
        return true;
    }

    // 获取打印时需要的打印参数
    private Bundle getPrintParam(int copies, int orientation) {
        Bundle param = new Bundle();

        // 打印浓度
        if (printDensity >= 0) {
            param.putInt(IDzPrinter.PrintParamName.PRINT_DENSITY, printDensity);
        }

        // 打印速度
        if (printSpeed >= 0) {
            param.putInt(IDzPrinter.PrintParamName.PRINT_SPEED, printSpeed);
        }

        // 间隔类型
        if (gapType >= 0) {
            param.putInt(IDzPrinter.PrintParamName.GAP_TYPE, gapType);
        }

        // 打印页面旋转角度
        if (orientation != 0) {
            param.putInt(IDzPrinter.PrintParamName.PRINT_DIRECTION, orientation);
        }

        // 打印份数
        if (copies > 1) {
            param.putInt(IDzPrinter.PrintParamName.PRINT_COPIES, copies);
        }

        return param;
    }

    // 显示连接、打印的状态提示框
    private void showStateAlertDialog(int resId) {
        showStateAlertDialog(activity.getResources().getString(resId));
    }

    // 显示连接、打印的状态提示框
    private void showStateAlertDialog(String str) {
        if (stateAlertDialog != null && stateAlertDialog.isShowing()) {
            stateAlertDialog.setTitle(str);
        } else {
            stateAlertDialog = new AlertDialog.Builder(activity).setCancelable(false).setTitle(str).show();
        }
    }

    // 清除连接、打印的状态提示框
    private void clearAlertDialog() {
        if (stateAlertDialog != null && stateAlertDialog.isShowing()) {
            stateAlertDialog.dismiss();
        }
        stateAlertDialog = null;
    }

    // 用于填充打印机列表的Adapter
    private class DeviceListAdapter extends BaseAdapter {
        private TextView tv_name = null;
        private TextView tv_mac = null;

        @Override
        public int getCount() {
            return pairedPrinters.size();
        }

        @Override
        public Object getItem(int position) {
            return pairedPrinters.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(activity).inflate(R.layout.item_printer, null);
            }
            tv_name = (TextView) convertView.findViewById(R.id.tv_device_name);
            tv_mac = (TextView) convertView.findViewById(R.id.tv_macaddress);

            if (pairedPrinters != null && pairedPrinters.size() > position) {
                IDzPrinter.PrinterAddress printer = pairedPrinters.get(position);
                tv_name.setText(printer.shownName);
                tv_mac.setText(printer.macAddress);
            }

            return convertView;
        }
    }

    // 打印机列表的每项点击事件
    private class DeviceListItemClicker implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            IDzPrinter.PrinterAddress printer = pairedPrinters.get(which);
            if (printer != null) {
                // 连接选择的打印机
                if (api.openPrinterByAddress(printer)) {
                    // 连接打印机的请求提交成功，刷新界面提示
                    onPrinterConnecting(printer, true);
                    return;
                }
            }

            // 连接打印机失败，刷新界面提示
            onPrinterDisconnected();
        }
    }
}
