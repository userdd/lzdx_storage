package com.ycy.storehouse.base;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.ycy.storehouse.LoginActivity;
import com.ycy.storehouse.utils.CProgressDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ld on 2018/7/20.
 * retrofit response 回调封装
 */

public abstract class ResultCallBack implements Callback<ResponseBody> {

    private Activity context;

    public ResultCallBack(Activity context) {
        this.context = context;
       // CProgressDialogUtils.showProgressDialog(context, "加载中", true, null);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        //CProgressDialogUtils.cancelProgressDialog(context);
        String str = "";
        try {
            str = response.body().string();
            Log.e("接口返回数据----->", str);
            String jsonStr = "";
            JSONObject jsonObject = new JSONObject(str);
            String code = jsonObject.getString("status");
            String msg = jsonObject.getString("status");
            try {
                jsonStr = String.valueOf(jsonObject.get("info"));
            } catch (JSONException e) {

            }
            Log.e("jsonStr----->", jsonStr);
            switch (code) {
                case "-1"://请求成功 code返回为-1
                    onResponseError(code, jsonStr);
                    break;
                case "1"://请求成功 code返回为1
                    onResponse(msg, jsonStr);
                    break;
                case "2"://请求成功 code返回为2
                    onResponsePay(msg, jsonStr);
                    break;
                case "400"://请求失败
                    Toast.makeText(context, "参数错误", Toast.LENGTH_SHORT).show();
                    break;
                case "101"://登录失效
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra("type", "登录失效");
                    context.startActivity(intent);
                    context.finish();
                    break;
                default:
                    onResponseError(msg, jsonStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("解析异常:错误日志-------->>>", e.toString());
            Log.e("解析异常:后台返回数据为-------->>>", str);
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
       // CProgressDialogUtils.cancelProgressDialog(context);
        onFailure(t.getMessage());//请求失败
        Log.e("jsonStr----->", "请求失败!!!" + "<----->" + t.getMessage());
    }

    public abstract void onResponse(String msg, String result);

    public void onResponseError(String msg, String result) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }

    public void onResponsePay(String msg, String result) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public abstract void onFailure(String msg);
}
