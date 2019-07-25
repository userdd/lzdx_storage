package com.ycy.storehouse.listener;

import android.view.View;

/**
 * Created by ld on 2018/7/23.
 * item中子view的点击事件（回调）
 */

public interface OnItemClickListener {
    /**
     * @param position item position
     * @param viewtype 点击的view的类型，调用时根据不同的view传入不同的值加以区分
     */
    void onItemClick(View view, int position, int viewtype);
}
