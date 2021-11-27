package com.beetleink.redgifs.Fragments.PersonFrag.Creator;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redgifs.R;

public class CreatorViewHolder extends  RecyclerView.ViewHolder {
    ImageView imageView;

    public CreatorViewHolder(@NonNull  View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);

    }
}

