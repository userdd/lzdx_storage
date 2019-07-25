package com.ycy.storehouse.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ycy.storehouse.R;
import com.ycy.storehouse.base.BaseRecycleViewAdapter;
import com.ycy.storehouse.entity.SurveyBean;
import com.ycy.storehouse.listener.OnItemClickListener;
import com.ycy.storehouse.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyDetailsAdapter extends BaseRecycleViewAdapter {

    private Context context;
    private OnItemClickListener clickListener;


    public SurveyDetailsAdapter(Context context, OnItemClickListener clickListener){
        this.context = context;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SurveyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_survey_record, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SurveyViewHolder holder = (SurveyViewHolder) viewHolder;
        String sUrl = (String) datas.get(i);
        Tools.loadImg(sUrl,holder.fiv);
        holder.llDel.setVisibility(View.INVISIBLE);
        viewHolder.itemView.setOnClickListener(v -> clickListener.onItemClick(v,i,0));
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    class SurveyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fiv)
        ImageView fiv;
        @BindView(R.id.ll_del)
        LinearLayout llDel;

        public SurveyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
