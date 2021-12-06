package com.beetleink.redvids.Fragments.SearchFrag.Horizontal;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redvids.R;

public class ViewHolder extends  RecyclerView.ViewHolder {
    TextView textView;
    public ViewHolder(@NonNull  View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textview);

    }
}

