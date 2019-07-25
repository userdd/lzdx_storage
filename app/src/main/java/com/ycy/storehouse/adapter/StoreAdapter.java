package com.ycy.storehouse.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ycy.storehouse.R;
import com.ycy.storehouse.base.BaseRecycleViewAdapter;
import com.ycy.storehouse.entity.StoreGoodsBean;
import com.ycy.storehouse.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreAdapter extends BaseRecycleViewAdapter {

    private Context context;
    private OnItemClickListener clickListener;


    public StoreAdapter(Context context, OnItemClickListener clickListener){
        this.context = context;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StoreViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_store, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        StoreViewHolder holder = (StoreViewHolder) viewHolder;
        StoreGoodsBean.ListBean storeBean = (StoreGoodsBean.ListBean) datas.get(i);
        holder.tvName.setText("名称："+storeBean.getName());
        holder.tvPrice.setText("单价：¥"+storeBean.getPrice());
        holder.tvTime.setText("时间："+storeBean.getCreateTime().substring(0,10));
        if(storeBean.getMinWarn()>storeBean.getStock()){
            holder.tvStore.setTextColor(context.getResources().getColor(R.color.color_FF0000));
        }
        holder.tvWarn.setText("警戒值："+storeBean.getMinWarn()+"");
        holder.tvStore.setText(storeBean.getStock()+"");
        final int posi = i;
        holder.tvCheck.setOnClickListener(v -> clickListener.onItemClick(v,posi,0));
        holder.tvEnterStore.setOnClickListener(v -> clickListener.onItemClick(v,posi,1));
        holder.tvOutStore.setOnClickListener(v -> clickListener.onItemClick(v,posi,2));
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    class StoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_warn)
        TextView tvWarn;
        @BindView(R.id.tv_store)
        TextView tvStore;
        @BindView(R.id.tv_enter_store)
        TextView tvEnterStore;
        @BindView(R.id.tv_out_store)
        TextView tvOutStore;
        @BindView(R.id.tv_check)
        TextView tvCheck;


        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
