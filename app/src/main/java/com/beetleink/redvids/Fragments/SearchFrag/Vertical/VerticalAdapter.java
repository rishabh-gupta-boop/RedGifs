package com.beetleink.redvids.Fragments.SearchFrag.Vertical;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redvids.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<ViewHolder>  {
   Context context;
    ArrayList<Integer> imagesArrayList;

    public VerticalAdapter(Context context, ArrayList<Integer> imagesArrayList) {
        this.context = context;
        this.imagesArrayList = imagesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_fragement_adapter_videoview, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(imagesArrayList.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesArrayList.size();
    }




}
