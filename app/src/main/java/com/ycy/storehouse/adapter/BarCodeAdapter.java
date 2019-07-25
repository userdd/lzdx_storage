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
import com.ycy.storehouse.entity.BarCodeBean;
import com.ycy.storehouse.entity.EnterStoreBean;
import com.ycy.storehouse.entity.OutStoreBean;
import com.ycy.storehouse.entity.SurveyBean;
import com.ycy.storehouse.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BarCodeAdapter extends BaseRecycleViewAdapter {

    private Context context;
    private OnItemClickListener clickListener;


    public BarCodeAdapter(Context context, OnItemClickListener clickListener){
        this.context = context;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BarCodeViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bar_code, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        BarCodeViewHolder holder = (BarCodeViewHolder) viewHolder;
        if(datas.get(i) instanceof EnterStoreBean.ListBean){
            EnterStoreBean.ListBean enterBean = (EnterStoreBean.ListBean) datas.get(i);
            holder.tvNumber.setText(enterBean.getNumber()+"");
            holder.tvTime.setText(enterBean.getEnterTime().substring(0,10));
            holder.tvPrice.setText(enterBean.getEnterPrice()+"");
            holder.tvAddress.setText(enterBean.getAddress());
            holder.tvPriceTitle.setText("价格：");
            holder.tvTimeTittle.setText("入库时间：");
            holder.tvAddressTitle.setText("入库地址：");
        }else {
            OutStoreBean.ListBean outBean = (OutStoreBean.ListBean) datas.get(i);
            holder.tvNumber.setText(outBean.getNumber()+"");
            holder.tvTime.setText(outBean.getCreateTime().substring(0,10));
            holder.tvPrice.setText(outBean.getPerson());
            holder.tvAddress.setText(outBean.getAddress());
            holder.tvPriceTitle.setText("提取人：");
            holder.tvTimeTittle.setText("出库时间：");
            holder.tvAddressTitle.setText("出库地址：");
        }
        final int posi = i;
        viewHolder.itemView.setOnClickListener(v -> clickListener.onItemClick(v,posi,0));
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    class BarCodeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_price_title)
        TextView tvPriceTitle;
        @BindView(R.id.tv_time_title)
        TextView tvTimeTittle;
        @BindView(R.id.tv_address_title)
        TextView tvAddressTitle;


        public BarCodeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
