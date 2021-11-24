package com.beetleink.redgifs.Fragments.SavedFrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beetleink.redgifs.R;

import java.util.ArrayList;

public class SavedFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Integer> arrayListImages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        init(view);


        return view;

    }
    void init(View view){
        Adapter adapter = new Adapter(this);
        final GreedoLayoutManager layoutManager = new GreedoLayoutManager(photosAdapter);
        layoutManager.setMaxRowHeight(MeasUtils.dpToPx(150, this));

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(photosAdapter);

        int spacing = MeasUtils.dpToPx(4, this);
        recyclerView.addItemDecoration(new GreedoSpacingItemDecoration(spacing));



    }
}