package com.beetleink.redvids.Fragments.GifyFrag;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redvids.R;

import java.util.ArrayList;

public class Adapter  extends RecyclerView.Adapter<ViewHolder> {
//    ArrayList<SoundGif> soundGifs = new ArrayList<>();
    ArrayList<String> locationArrayList = new ArrayList<>();
    Context context;
    static public ViewHolder viewHolder;





        ///Calling soundgifs variables in arraylist//
//        ArrayList<SoundGif> soundGifs
    public Adapter(Context context,ArrayList<String> locationArrayList) {
        this.locationArrayList= locationArrayList;
        this.context = context;

    }

    public Adapter(){}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gify_items,parent,false);

        return new ViewHolder(view,context,locationArrayList);


    }


    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {


    }


    @Override
    public void onViewAttachedToWindow(@NonNull  ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Log.i("onViewAttachedToWindow", "yes"+String.valueOf(holder.getPosition()));
        viewHolder = holder;
//        holder.itemsFiled(holder.getPosition());
        holder.onPrepare(holder.getPosition());
        holder.resumePlayer();

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull  ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.i("onViewDchedFromWindow", "yes"+String.valueOf(holder.getPosition()));
        holder.releasePlayer();


    }



    @Override
    public int getItemCount() {

//        return soundGifs.size();
        return locationArrayList.size();
    }



}
