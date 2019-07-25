package com.ycy.storehouse.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ycy.storehouse.R;
import com.ycy.storehouse.base.BaseApp;
import com.ycy.storehouse.listener.OnLubanFinishListener;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by Administrator on 2018/6/13.
 */

public class Tools {

    public static void loadImg(String url, ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.loading).error(R.mipmap.error);
        Glide.with(BaseApp.getAppContext())
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static String toUtf8(String str) {
        String result = null;
        try {
            result = new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static Spanned setColor(String textStart, String textCenter, String textEnd) {
        String text = textStart + "<font color=\"#FB4945\">" + textCenter + "</font>" + textEnd;
        return Html.fromHtml(text);
    }

    public static Spanned setTextColor(String textStart, String textCenter, String textEnd, String textEndRight) {
        String text = textStart + "<font color=\"#FB4945\">" + textCenter + "</font>" + textEnd +
                "<font color=\"#FB4945\">" + textEndRight + "</font>";
        return Html.fromHtml(text);
    }

    /**
     * 弹出软键盘
     *
     * @param et
     */
    public static void showSoftInput(EditText et) {
        if (et != null) {
            et.setFocusable(true);
            et.setFocusableInTouchMode(true);
            et.requestFocus();
            Message msg = new Message();
            msg.obj = et;
            showInputHandler.sendMessageDelayed(msg, 400);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param et
     */
    public static void hideSoftInput(EditText et) {
        InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    public static Handler showInputHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EditText view = (EditText) msg.obj;
            view.requestFocus();
            view.setSelection(view.getText().toString().length());
            InputMethodManager im = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.showSoftInput(view, 0);
        }
    };

    /**
     * 填写textview
     *
     * @param tv
     * @param value
     */
    public static void setTextView(TextView tv, String value) {
        tv.setText(TextUtils.isEmpty(value) ? "" : value);
    }

    /**
     * 设置外边距
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 两个日期比较
     * */
    public static boolean timeCompare(Date date1,Date date2){
        if(date1.compareTo(date2)>0){
            return false;
        }else {
            return true;
        }
    }
    /**
     * date 转为字符串
     */
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static Date stringToDate(String strTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formateDate(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(time * 1000);
        String str = formatter.format(curDate);
        return str;
    }

    public static String formateDateCN(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(time * 1000);
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 上传单张图片
     */
    public static List<MultipartBody.Part> uploadImage(File file, String catalog) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型

        //2.获取图片，创建请求体
        if (file.exists()) {
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);//表单类型
            builder.addFormDataPart("image", file.getName(), body); //添加图片数据，body创建的请求体
        } else {
            Log.e("path", "文件目录出错");
        }

        //3.调用MultipartBody.Builder的addFormDataPart()方法添加表单数据
        builder.addFormDataPart("token", Methods.getToken());//传入服务器需要的key，和相应value值
        builder.addFormDataPart("catalog", catalog);//传入服务器需要的key，和相应value值

        //4.创建List<MultipartBody.Part> 集合，
        //  调用MultipartBody.Builder的build()方法会返回一个新创建的MultipartBody
        //  再调用MultipartBody的parts()方法返回MultipartBody.Part集合
        List<MultipartBody.Part> parts = builder.build().parts();
        return parts;
    }

    /**
     * 上传多张图片
     */
    public static void upLoadFile(Activity context, List<String> imgStrs, OnLubanFinishListener listener){
        Map<String, RequestBody> params = new HashMap<>();
        Luban.with(context)
                .load(imgStrs)
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                        //注意：file就是与服务器对应的key,后面filename是服务器得到的文件名
                        params.put("file\"; filename=\"" + file.getName(), requestFile);
                        Log.e("s",file.toString());
                        if(params.size() == imgStrs.size()){
                            listener.finish(params);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();
    }

}
