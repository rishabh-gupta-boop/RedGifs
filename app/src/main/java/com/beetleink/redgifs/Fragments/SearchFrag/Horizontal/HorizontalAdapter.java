package com.beetleink.redgifs.Fragments.SearchFrag.Horizontal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redgifs.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HorizontalAdapter extends RecyclerView.Adapter<ViewHolder>  {
   Context context;
    ArrayList<String> textArrayList;

    public HorizontalAdapter(Context context, ArrayList<String> textArrayList) {
        this.context = context;
        this.textArrayList = textArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_fragment_horizontal_scroll, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(textArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return textArrayList.size();
    }




}
