package cray.ajm.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

import cray.ajm.app.Update.AppUpdateChecker;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import cray.ajm.app.AllLoginAndRegister.AllLoginActivity;
import cray.ajm.app.MainRecyclerView.MediaObject;
import cray.ajm.app.MainRecyclerView.VerticalSpacingItemDecorator;
import cray.ajm.app.MainRecyclerView.VideoPlayerRecyclerAdapter;
import cray.ajm.app.MainRecyclerView.VideoPlayerRecyclerView;
import cray.ajm.app.VideoEditor.PortraitCameraActivity;
import cray.ajm.app.responses.ApiClient;
import cray.ajm.app.responses.ApiInterface;
import cray.ajm.app.responses.Users;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ImageView loader;

    ////////////////////////////////////////////////
    SessionManager sessionManager;
    private String user_id = null;
    public static ApiInterface apiInterface;

    //////////////////////////////////////////////
    private VideoPlayerRecyclerView mRecyclerView;
    ArrayList<MediaObject> mediaObjects = new ArrayList<>();

    ////////////////////
    private TextView followingText,trendingText;
    /////////////////////////////////////////////

    private static final int RC_SETTINGS_SCREEN_PERM = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        loader = (ImageView) findViewById(R.id.imageView18);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorScheme(R.color.gradientBtnStartColor, R.color.colorGreen, R.color.colorOrange, R.color.colorPurple);
        ////////////App update checker//////////////////////
        try {
            AppUpdateChecker appUpdateChecker=new AppUpdateChecker(this);  //pass the activity in constructure
            appUpdateChecker.checkForUpdate(false); //mannual check false here
        } catch (Exception e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////////////////////////

        //////////////////////////////////////////
        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        user_id = user.get(sessionManager.USER_ID);
        /////////////////////////////////////
        Glide.with(this).load(R.drawable.loader).into(loader);

        requestPermissions();

        init();
    }

    @AfterPermissionGranted(RC_SETTINGS_SCREEN_PERM)
    private void requestPermissions()
    {
        String[] perm = {Manifest.permission.INTERNET,Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
        if(EasyPermissions.hasPermissions(this,perm))
        {

        }
        else
        {
            EasyPermissions.requestPermissions(this,"This app needs to access your camera and mic",RC_SETTINGS_SCREEN_PERM,perm);

        }
    }

    private void init() {

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //////////////bottom navigation start////////////////
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigation);
        ////////////////bottom navigation end////////////////////////

        mRecyclerView = findViewById(R.id.recycler_view);

        //////////////////////////////////////////////////////////////
        followingText = (TextView) findViewById(R.id.textView20);
        trendingText = (TextView) findViewById(R.id.textView5);

        followingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FollowingActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeRight(HomeActivity.this);
                finish();
            }
        });

        ////////////////////////////////////////////////

        initRecyclerView();

    }
    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(0);
        mRecyclerView.addItemDecoration(itemDecorator);

        ///////////////////////////////////////////////////////////////
        LoadHorizontalProducts();
        ////////////////////////////////////////////////////////////////////////////////

        SnapHelper mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(mRecyclerView);

        ////////////////////////////////////
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }
    private void LoadHorizontalProducts() {

        loader.setVisibility(View.VISIBLE);

        Call<Users> call = apiInterface.all_posts();
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if(response.isSuccessful())
                {
                    loader.setVisibility(View.GONE);
                    mediaObjects = (ArrayList<MediaObject>) response.body().getAllPosts();

                    mRecyclerView.setMediaObjects(mediaObjects);
                    VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(mediaObjects, initGlide());
                    mRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    mRecyclerView.setKeepScreenOn(true);
                    mRecyclerView.smoothScrollToPosition(mediaObjects.size()+1);
                }
                else
                {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(HomeActivity.this, "Network Error.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Network Error.", Toast.LENGTH_SHORT).show();
                loader.setVisibility(View.GONE);
            }
        });
    }
    private RequestManager initGlide(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.color.colorPrimaryDark)
                .error(R.color.colorPrimaryDark);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigation =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId())
                    {
//                        case R.id.home:
//                            Intent intent2 = new Intent(HomeActivity.this, HomeActivity.class);
//                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent2);
//                            Animatoo.animateSlideUp(HomeActivity.this);
//                            finish();
//                            break;
                        case R.id.search:
                            Intent intent3 = new Intent(HomeActivity.this, DiscoverActivity.class);
                            intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent3);
                            Animatoo.animateSlideUp(HomeActivity.this);
                            finish();
                            break;
                        case R.id.plus:
                            if(sessionManager.isLogin())
                            {
                                requestPermissions();

                                Intent intent19 = new Intent(HomeActivity.this, PortraitCameraActivity.class);
                                // set the new task and clear flags
                                intent19.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent19);
                                Animatoo.animateSlideUp(HomeActivity.this);
                                finish();
                            }
                            else
                            {
                                Intent intent19 = new Intent(HomeActivity.this, AllLoginActivity.class);
                                // set the new task and clear flags
                                intent19.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent19);
                                Animatoo.animateSlideUp(HomeActivity.this);
                                finish();
                            }
                            break;
                        case R.id.notification:
                            Intent intent1 = new Intent(HomeActivity.this, NotificationActivity.class);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent1);
                            Animatoo.animateSlideUp(HomeActivity.this);
                            finish();
                            break;
                        case R.id.account:
                            Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            Animatoo.animateSlideUp(HomeActivity.this);
                            finish();
                            break;
                    }
                    return true;
                }
            };
    public static void setWindowFlag(@NotNull Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onDestroy() {
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
