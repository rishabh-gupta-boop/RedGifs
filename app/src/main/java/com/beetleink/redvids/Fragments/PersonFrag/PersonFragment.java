package com.beetleink.redvids.Fragments.PersonFrag;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beetleink.redvids.Fragments.PersonFrag.Creator.CreatorAdapter;
import com.beetleink.redvids.Fragments.PersonFrag.Viewer.ViewerAdapter;
import com.beetleink.redvids.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonFragment extends Fragment {
    private CircleImageView profilePic;
    TextView userNameTextView, descriptionTextView,totalPostTextView,totalFollowerTextView;
    AppCompatButton followOrEditButtonView;
    RecyclerView usersPostsList;
    ArrayList<Integer> arrayListImages;
    ListView listView;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://redgifs-6739a-default-rtdb.asia-southeast1.firebasedatabase.app/");
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.viewer_fragment, container, false);




//        creatorFragment(view);
        viewerFragment(view);



      return view;
    }


    void creatorFragment(View view){
        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        totalPostTextView = view.findViewById(R.id.totalPostTextView);
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

    void viewerFragment(View view){
        profilePic = view.findViewById(R.id.profile_image);
        followOrEditButtonView = view.findViewById(R.id.followOrEditTextView);
        userNameTextView = view.findViewById(R.id.userNameTextView);
        EditButton(view);


        listView = view.findViewById(R.id.notificationListView);
        String countryNames[] = {
                "India",
                "China",
                "Nepal",
                "Bhutan"
        };

        String capitalNames[] = {
                "Delhi",
                "Beijing",
                "Kathmandu",
                "Thimphu"
        };

        Integer imageid[] = {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d

        };

        ////
        // Setting header
        TextView textView = new TextView(getContext());
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText("List of Countries");


        listView.addHeaderView(textView);

        // For populating list data
        ViewerAdapter viewerAdapter = new ViewerAdapter(getActivity(), countryNames, capitalNames, imageid);
        listView.setAdapter(viewerAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getContext(),"You Selected "+countryNames[position-1]+ " as Country",Toast.LENGTH_SHORT).show();        }
        });

        //firebase username change according to the firebase Database
        if(firebaseAuth.getCurrentUser()!=null){
            firebaseDatabase.getReference().child("users").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    userNameTextView.setText(snapshot.child("username").getValue().toString());
                }
                @Override
                public void onCancelled(DatabaseError error){
                    Log.i("Errorrr", error.getMessage());
                }
            });
        }

    }

    void EditButton(View view){
        followOrEditButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),EditAccount.class);
                startActivity(intent);
            }
        });
    }







    }





