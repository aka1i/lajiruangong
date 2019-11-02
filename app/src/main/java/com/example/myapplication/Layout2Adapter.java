package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class Layout2Adapter extends RecyclerView.Adapter<Layout2Adapter.ViewHolder>{
    private Map<String, List<ShopBean>> map;
    private Context context;
    public Layout2Adapter(Map<String, List<ShopBean>> map, Context context){
        this.map=map;
        this.context=context;
    }

     class ViewHolder extends RecyclerView.ViewHolder{

        TextView addrText;

        private ViewHolder(View view){
            super(view);
            this.addrText = view.findViewById(R.id.addr);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Layout2Adapter.this.context,FirstPageActivity.class);
                    intent.putExtra("addr",addrText.getText().toString().substring(3));
                    Layout2Adapter.this.context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        int i = 0;
        String name = "";
        for (String key : map.keySet())
        {
            if (i == position)
                name = key;
            i++;
        }
        holder.addrText.setText("地址：" + name);

    }

    @Override
    public int getItemCount() {
        return map.keySet().size();
    }

    @NonNull
    @Override
    public Layout2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.item_layout1,parent,false);
        return new Layout2Adapter.ViewHolder(view);
    }


}
