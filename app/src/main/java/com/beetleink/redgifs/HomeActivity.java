package com.beetleink.redgifs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.PrecomputedTextCompat;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static android.graphics.PixelFormat.TRANSLUCENT;
import static android.provider.Telephony.TextBasedSmsColumns.STATUS;

public class HomeActivity extends AppCompatActivity {
    private ImageView disc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //disc loading in layout
        disc = findViewById(R.id.disk);
        Glide.with(this).load(R.drawable.disk).into(disc);
        /////////////////////////////////////////////////////////


    }


}