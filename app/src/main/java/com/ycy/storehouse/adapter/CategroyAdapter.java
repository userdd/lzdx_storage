package com.ycy.storehouse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ycy.storehouse.R;
import com.ycy.storehouse.entity.CategoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类
 */

public class CategroyAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryBean> mArrayList;
    public OnItemClickListener onItemClickListener;//item子view点击事件

    public CategroyAdapter(Context context, List<CategoryBean> mArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.mArrayList = mArrayList;
    }


    @Override
    public int getCount() {
        if (mArrayList == null) {
            return 0;
        } else {
            return this.mArrayList.size();
        }

    }

    @Override
    public Object getItem(int position) {
        if (mArrayList == null) {
            return null;
        } else {
            return this.mArrayList.get(position);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryViewHolder holder = null;
        if (convertView == null) {
            holder = new CategoryViewHolder(convertView);
            convertView = LayoutInflater.from(context).inflate(R.layout.item_text, null, false);
            convertView.setTag(holder);
        } else {
            holder = (CategoryViewHolder) convertView.getTag();
        }

        if (this.mArrayList != null) {
            holder.tvText.setText(mArrayList.get(position).getName());
        }
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });

        return convertView;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView tvText;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    /**
     * item点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
