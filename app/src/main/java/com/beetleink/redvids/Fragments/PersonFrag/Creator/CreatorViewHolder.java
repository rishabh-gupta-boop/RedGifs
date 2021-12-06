package com.beetleink.redvids.Fragments.PersonFrag.Creator;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redvids.R;

public class CreatorViewHolder extends  RecyclerView.ViewHolder {
    ImageView imageView;

    public CreatorViewHolder(@NonNull  View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);

    }
}

