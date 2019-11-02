package com.example.myapplication;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class Layout1Adapter extends RecyclerView.Adapter<Layout1Adapter.ViewHolder>{
    private List<ShopBean> beans;
    private Context context;
    public Layout1Adapter(List<ShopBean> beans, Context context){
        this.beans=beans;
        this.context=context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameText;
        TextView addrText;
        TextView refinedScore1;
        TextView avgPrice;

        private ViewHolder(View view){
            super(view);
            this.nameText = view.findViewById(R.id.name);
            this.addrText = view.findViewById(R.id.addr);
            this.refinedScore1 = view.findViewById(R.id.refinedScore1);
            this.avgPrice = view.findViewById(R.id.avg_price);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ShopBean bean = beans.get(position);
        holder.nameText.setText("店名：" + bean.getShopName());
        holder.addrText.setText("地址：" + bean.getMainRegionName());
        holder.refinedScore1.setText("评分：" + bean.getRefinedScore1());
        holder.avgPrice.setText("人均消费：" + bean.getAvgPrice());
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    @NonNull
    @Override
    public Layout1Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.item_layout1,parent,false);
        return new Layout1Adapter.ViewHolder(view);
    }


}
