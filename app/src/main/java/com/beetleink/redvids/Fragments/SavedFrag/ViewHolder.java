package com.beetleink.redvids.Fragments.SavedFrag;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redvids.R;

public class ViewHolder extends  RecyclerView.ViewHolder {
    ImageView imageView;

    public ViewHolder(@NonNull  View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);

    }
}

