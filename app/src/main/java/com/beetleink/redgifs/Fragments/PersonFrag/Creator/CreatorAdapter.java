package com.beetleink.redgifs.Fragments.PersonFrag.Creator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redgifs.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CreatorAdapter extends RecyclerView.Adapter<CreatorViewHolder>  {
   Context context;
    ArrayList<Integer> imagesArrayList;

    public CreatorAdapter(Context context, ArrayList<Integer> imagesArrayList) {
        this.context = context;
        this.imagesArrayList = imagesArrayList;
    }

    @NonNull
    @Override
    public CreatorViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.creator_imageview_recycler,
                parent, false);
        return new CreatorViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CreatorViewHolder holder, int position) {
        Glide.with(context)
                .load(imagesArrayList.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesArrayList.size();
    }




}
