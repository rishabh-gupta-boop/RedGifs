package com.beetleink.redvids.Fragments.SavedFrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beetleink.redvids.R;
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
        recyclerView= view.findViewById(R.id.savedFragmentRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);

        recyclerView.setLayoutManager(gridLayoutManager);

        arrayListImages = new ArrayList<>();
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);

        Adapter adapter = new Adapter(getContext(), arrayListImages);
        recyclerView.setAdapter(adapter);




    }
}