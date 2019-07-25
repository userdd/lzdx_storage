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
import com.ycy.storehouse.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends BaseRecycleViewAdapter {

    private Context context;
    private OnItemClickListener clickListener;
    private int[] drawList = {R.mipmap.scan,R.mipmap.store_admin,R.mipmap.enter_store,R.mipmap.out_store,R.mipmap.search};
    private String[] nameList = {"扫描条码","库存管理","入库记录","出库记录","市场调研"};


    public HomeAdapter(Context context,OnItemClickListener clickListener){
        this.context = context;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        HomeViewHolder holder = (HomeViewHolder) viewHolder;
        holder.imageView.setImageDrawable(context.getResources().getDrawable(drawList[i]));
        holder.textView.setText(nameList[i]);
        final int posi = i;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v,posi,0);
            }
        });
    }



    @Override
    public int getItemCount() {
        return drawList.length;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_icon)
        ImageView imageView;
        @BindView(R.id.tv_text)
        TextView textView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
