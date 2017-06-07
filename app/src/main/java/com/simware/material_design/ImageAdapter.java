package com.simware.material_design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    private List<ImageData> datas;
    private Context context;
    public ImageAdapter(List<ImageData> datas){
        this.datas=datas;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.item_image,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageData data=datas.get(viewHolder.getAdapterPosition());
                Intent intent=new Intent(context,DataActivity.class);
                //intent.putExtra(DataActivity.DATA_NAME,data.getName());
                //intent.putExtra(DataActivity.DATA_IMAGE_ID,data.getImageId());
                intent.putExtra(DataActivity.DATA,data);
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageData data=datas.get(position);
        holder.textView.setText(data.getName());
        Glide.with(context).load(data.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        CardView cardView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.card);
            imageView=(ImageView)itemView.findViewById(R.id.image);
            textView=(TextView)itemView.findViewById(R.id.text);
        }
    }
}
