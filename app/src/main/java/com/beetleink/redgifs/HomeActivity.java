package com.beetleink.redgifs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.beetleink.redgifs.Model.Adapter;

import com.beetleink.redgifs.Fragments.GifyView;
import com.beetleink.redgifs.Fragments.PersonFragment;
import com.beetleink.redgifs.Fragments.SavedFragment;
import com.beetleink.redgifs.Fragments.SearchFragment;
import com.beetleink.redgifs.Fragments.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.beetleink.redgifs.Model.Adapter.viewHolder;


public class HomeActivity extends AppCompatActivity {

    boolean isActivityRunning = false;
    FragmentManager fragmentManager;
    BottomNavigationView.OnNavigationItemSelectedListener navListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        isActivityRunning = true;
        init();

    }


    @Override
    protected void onResume() {
        super.onResume();
        if(Adapter.viewHolder!=null){
            Adapter.viewHolder.resumePlayer();
        }
    }



    @Override
    public void onStop() {
        super.onStop();
        Adapter.viewHolder.pausePlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("destor", "yes");
       Adapter.viewHolder.releasePlayer();
    }

    private void init() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        getSupportFragmentManager().beginTransaction().add(R.id.relativeLayout, new GifyView(),"zero").commit();
        fragmentManager = getSupportFragmentManager();

        bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewHolder.resumePlayer();
                        selectItem(0);


                        return true;
                    case R.id.search:
                        viewHolder.pausePlayer();
                        selectItem(1);
                        return true;
                    case R.id.saved:
                        viewHolder.pausePlayer();
                        selectItem(2);
                        return true;
                    case R.id.person:
                        viewHolder.pausePlayer();
                        selectItem(3);
                        return true;
                    case R.id.upload:
                        viewHolder.pausePlayer();
                        selectItem(4);
                        return true;
                    default:
                        return false;
                }

            }
        });


    }

    private void selectItem(int position) {
        switch (position) {
            case 0:
                if (fragmentManager.findFragmentByTag("zero") != null) {
                    //if the fragment exists, show it.
                    Log.i("gify show", "yes");
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("zero")).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                    fragmentManager.beginTransaction().add(R.id.relativeLayout, new GifyView(), "zero").commit();
                }
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the other fragment is visible, hide it.

                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("one")).commit();
                }
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("two")).commit();
                }
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("three")).commit();
                }
                if (fragmentManager.findFragmentByTag("four") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("four")).commit();
                }
                break;
            case 1:
                Log.i("gify show", "search Fragment");
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("one")).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                    fragmentManager.beginTransaction().add(R.id.relativeLayout, new SearchFragment(), "one").commit();
                }
                if (fragmentManager.findFragmentByTag("zero") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("zero")).commit();
                }
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("two")).commit();
                }
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("three")).commit();
                }
                if (fragmentManager.findFragmentByTag("four") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("four")).commit();
                }
                break;

            case 2:
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("two")).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                    fragmentManager.beginTransaction().add(R.id.relativeLayout, new UploadFragment(), "two").commit();
                }
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("one")).commit();
                }
                if (fragmentManager.findFragmentByTag("zero") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("zero")).commit();
                }
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("three")).commit();
                }
                if (fragmentManager.findFragmentByTag("four") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("four")).commit();
                }
                break;
            case 3:
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("three")).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                    fragmentManager.beginTransaction().add(R.id.relativeLayout, new SavedFragment(), "three").commit();
                }
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("one")).commit();
                }
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("two")).commit();
                }
                if (fragmentManager.findFragmentByTag("zero") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("zero")).commit();
                }
                if (fragmentManager.findFragmentByTag("four") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("four")).commit();
                }
                break;
            case 4:
                if (fragmentManager.findFragmentByTag("four") != null) {
                    //if the fragment exists, show it.
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("four")).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                    fragmentManager.beginTransaction().add(R.id.relativeLayout, new PersonFragment(), "four").commit();
                }
                if (fragmentManager.findFragmentByTag("one") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("one")).commit();
                }
                if (fragmentManager.findFragmentByTag("two") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("two")).commit();
                }
                if (fragmentManager.findFragmentByTag("zero") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("zero")).commit();
                }
                if (fragmentManager.findFragmentByTag("three") != null) {
                    //if the other fragment is visible, hide it.
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("three")).commit();
                }
                break;
        }


    }
}