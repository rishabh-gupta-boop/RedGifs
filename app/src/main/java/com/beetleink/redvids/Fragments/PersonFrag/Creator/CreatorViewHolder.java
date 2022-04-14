package com.beetleink.redvids.Fragments.PersonFrag.Creator;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redvids.R;

public class CreatorViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imageView;
    OnRecyclerViewItemListener onRecyclerViewItemListener;
    public CreatorViewHolder(@NonNull  View itemView,OnRecyclerViewItemListener onRecyclerViewItemListener) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        this.onRecyclerViewItemListener = onRecyclerViewItemListener;
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        onRecyclerViewItemListener.getpositon(getAdapterPosition());
    }

    public interface OnRecyclerViewItemListener{
        void getpositon(int position);
    }
}


