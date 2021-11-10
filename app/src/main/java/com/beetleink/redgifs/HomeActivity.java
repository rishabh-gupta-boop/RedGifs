package com.beetleink.redgifs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.PrecomputedTextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.beetleink.redgifs.Model.Adapter;
import com.beetleink.redgifs.Model.Model;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.PixelFormat.TRANSLUCENT;
import static android.provider.Telephony.TextBasedSmsColumns.STATUS;

public class HomeActivity extends AppCompatActivity {
    private ImageView disc;
    private List<Model> models;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        init();

    }

    private void init(){
        models = new ArrayList<>();
        recyclerView = findViewById(R.id.recylerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //it help to switch one layout to another without any screen mixed///
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        ////////////////////////////////////////////////////////////////////


        models.add(new Model(" ", "","","","","","","","",""));
        models.add(new Model(" ", "","","","","","","","",""));
        models.add(new Model(" ", "","","","","","","","",""));

        Adapter adapter = new Adapter(models,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }



}