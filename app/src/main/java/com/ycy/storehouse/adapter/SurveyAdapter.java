package com.ycy.storehouse.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycy.storehouse.R;
import com.ycy.storehouse.base.BaseRecycleViewAdapter;
import com.ycy.storehouse.entity.SurveyBean;
import com.ycy.storehouse.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyAdapter extends BaseRecycleViewAdapter {

    private Context context;
    private OnItemClickListener clickListener;


    public SurveyAdapter(Context context, OnItemClickListener clickListener){
        this.context = context;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SurveyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_survey, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SurveyViewHolder holder = (SurveyViewHolder) viewHolder;
        SurveyBean.ListBean surveyBean = (SurveyBean.ListBean) datas.get(i);
        holder.tvName.setText(surveyBean.getName());
        holder.tvPrice.setText("¥："+surveyBean.getPrice());
        holder.tvTime.setText(surveyBean.getCreateTime().substring(0,10));
        if(i%2 == 1){
            viewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.color_E1FAFF));
        }else{
            viewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.color_ECECEC));
        }
        final int posi = i;
        viewHolder.itemView.setOnClickListener(v -> clickListener.onItemClick(v,posi,0));
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    class SurveyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public SurveyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
