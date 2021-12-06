package com.beetleink.redvids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.beetleink.redvids.Fragments.PersonFrag.Authentication.RegistrationActivity;
import com.beetleink.redvids.Fragments.GifyFrag.Adapter;

import com.beetleink.redvids.Fragments.GifyFrag.GifyView;
import com.beetleink.redvids.Fragments.PersonFrag.PersonFragment;
import com.beetleink.redvids.Fragments.PersonFrag.main_menu.ChangeAccountType;
import com.beetleink.redvids.Fragments.PersonFrag.main_menu.DataUsage;
import com.beetleink.redvids.Fragments.PersonFrag.main_menu.SettingChangePassword;
import com.beetleink.redvids.Fragments.SavedFrag.SavedFragment;
import com.beetleink.redvids.Fragments.SearchFrag.SearchFragment;
import com.beetleink.redvids.Fragments.UplaodFrag.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import static com.beetleink.redvids.Fragments.GifyFrag.Adapter.viewHolder;


public class HomeActivity extends AppCompatActivity {

    boolean isActivityRunning = false;
    FragmentManager fragmentManager;
    FirebaseAuth firebaseAuth;
    BottomNavigationView.OnNavigationItemSelectedListener navListener;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        isActivityRunning = true;
        init();
        toolBar();

    }


    //main-menu ..............................................




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
        firebaseAuth = FirebaseAuth.getInstance();


        bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewHolder.resumePlayer();
                        selectItem(0);
                        toolbar.setVisibility(View.GONE);
                        return true;
                    case R.id.search:
                        viewHolder.pausePlayer();
                        selectItem(1);
                        toolbar.setVisibility(View.GONE);
                        return true;
                    case R.id.saved:
                        viewHolder.pausePlayer();
                        selectItem(3);
                        toolbar.setVisibility(View.GONE);
                        return true;
                    case R.id.person:
                        viewHolder.pausePlayer();
                        if(firebaseAuth.getCurrentUser()==null) {
                            startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                        }else{
                            toolbar.setVisibility(View.VISIBLE);

                            selectItem(4);
                        }


                        return true;
                    default:
                        return false;
                }

            }
        });


    }

    void toolBar(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setVisibility(View.GONE);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.changePassword){
                    Intent intent = new Intent(getApplicationContext(), SettingChangePassword.class);
                    startActivity(intent);
                }else if(item.getItemId()==R.id.changeAccountType){
                    Intent intent = new Intent(getApplicationContext(), ChangeAccountType.class);
                    startActivity(intent);
                }else if(item.getItemId()==R.id.dataUsage){
                    Intent intent = new Intent(getApplicationContext(), DataUsage.class);
                    startActivity(intent);
                }else if(item.getItemId()==R.id.logout){
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    if(auth.getCurrentUser()!=null){
                        auth.signOut();
                    }

                }
                return false;
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