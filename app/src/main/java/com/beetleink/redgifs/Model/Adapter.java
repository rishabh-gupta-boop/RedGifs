package com.beetleink.redgifs.Model;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redgifs.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Model>  models;
    Context context;
    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }



    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView disc;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            disc = itemView.findViewById(R.id.disk);
            Glide.with(context).load(R.drawable.disk).into(disc);

        }
    }
}
