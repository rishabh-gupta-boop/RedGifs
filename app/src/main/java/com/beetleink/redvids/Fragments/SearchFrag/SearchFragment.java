package com.beetleink.redvids.Fragments.SearchFrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.beetleink.redvids.Fragments.SearchFrag.Horizontal.HorizontalAdapter;
import com.beetleink.redvids.Fragments.SearchFrag.Vertical.VerticalAdapter;
import com.beetleink.redvids.R;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;


public class SearchFragment extends Fragment {
    MaterialSearchBar searchBar;
    RecyclerView horizontalRecyclerView;

    ArrayList<String> horizontalTextArrayList;
    ArrayList<String> verticalImagesArrayList;
    LinearLayoutManager HorizontalLayout;

    //vertical variable name
    RecyclerView verticalRecyclerView;
    ArrayList<Integer> arrayListImages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);

        horizontalRecyclerView(view);
        verticalRecyclerView(view);





        return  view;
    }

    void horizontalRecyclerView(View view){
        horizontalTextArrayList = new ArrayList<>();
        verticalImagesArrayList = new ArrayList<>();

        //horizontal ArrayList tags added;
        horizontalTextArrayList.add("Hot");
        horizontalTextArrayList.add("Sound");
        horizontalTextArrayList.add("Trendy");
        horizontalTextArrayList.add("Small");
        horizontalTextArrayList.add("BBC");
        horizontalTextArrayList.add("Tiny");
        horizontalTextArrayList.add("Spread");
        horizontalTextArrayList.add("Hot");
        horizontalTextArrayList.add("Sound");
        horizontalTextArrayList.add("Trendy");
        horizontalTextArrayList.add("Small");
        horizontalTextArrayList.add("BBC");
        horizontalTextArrayList.add("Tiny");
        horizontalTextArrayList.add("Spread");



        horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView);
        HorizontalLayout
                = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        horizontalRecyclerView.setLayoutManager(HorizontalLayout);

        HorizontalAdapter adapter = new HorizontalAdapter
                (getContext(),horizontalTextArrayList);
        horizontalRecyclerView.setAdapter(adapter);
    }

    void verticalRecyclerView(View view){
        verticalRecyclerView= view.findViewById(R.id.verticalRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);

        verticalRecyclerView.setLayoutManager(gridLayoutManager);

        arrayListImages = new ArrayList<>();
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);
        arrayListImages.add(R.drawable.c);

        VerticalAdapter adapter = new VerticalAdapter(getContext(), arrayListImages);
        verticalRecyclerView.setAdapter(adapter);
    }
}