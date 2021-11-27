package com.beetleink.redgifs.Fragments.PersonFrag;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beetleink.redgifs.Fragments.PersonFrag.Creator.CreatorAdapter;
import com.beetleink.redgifs.Fragments.SavedFrag.Adapter;
import com.beetleink.redgifs.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

    public class PersonFragment extends Fragment {
    private CircleImageView profilePic;
    TextView userNameTextView, descriptionTextView,totalPostTextView,totalFollowingTextView,totalFollowerTextView;
    ImageView settidngImageView;
    AppCompatButton followOrEditTextView;
    RecyclerView usersPostsList;
    ArrayList<Integer> arrayListImages;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.creator_fragment, container, false);
        profilePic = view.findViewById(R.id.profile_image);
        userNameTextView = view.findViewById(R.id.userNameTextView);
        settidngImageView = view.findViewById(R.id.settingImageView);
        followOrEditTextView = view.findViewById(R.id.followOrEditTextView);
        creatorFragment(view);



      return view;
    }


    void creatorFragment(View view){
        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        totalPostTextView = view.findViewById(R.id.totalPostTextView);
        totalFollowingTextView = view.findViewById(R.id.totalFollowingTextView);
        totalFollowerTextView = view.findViewById(R.id.totalFollowerTextView);
        usersPostsList = view.findViewById(R.id.usersPostsList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);

        usersPostsList.setLayoutManager(gridLayoutManager);

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

        CreatorAdapter adapter = new CreatorAdapter(getContext(), arrayListImages);
        usersPostsList.setAdapter(adapter);


    }





}