package com.ycy.storehouse.listener;

import android.view.View;

import java.util.Map;

import okhttp3.RequestBody;

public interface OnLubanFinishListener {

    void finish(Map<String, RequestBody> params);
}
