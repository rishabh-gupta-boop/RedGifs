package com.beetleink.redgifs.Fragments.SearchFrag.Horizontal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redgifs.R;

public class ViewHolder extends  RecyclerView.ViewHolder {
    TextView textView;
    public ViewHolder(@NonNull  View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textview);

    }
}

