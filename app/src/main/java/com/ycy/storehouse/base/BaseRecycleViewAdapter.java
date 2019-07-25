package com.ycy.storehouse.base;


import android.support.v7.widget.RecyclerView;

import com.ycy.storehouse.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ld on 2018/7/23.
 *
 */

public abstract class BaseRecycleViewAdapter <T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected OnItemClickListener itemListener;
    protected List<T> datas = new ArrayList<T>();


    public List<T> getDatas() {
        if (datas == null) {
            datas = new ArrayList<T>();
        }
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;

        notifyDataSetChanged();
    }
    /**
     * 清除所有集合中的数据并更新listview
     */
    public void clearAndNotify() {
        datas.clear();
        notifyDataSetChanged();
    }

    public void onItemListener(OnItemClickListener listener) {
        this.itemListener = listener;
    }
}

