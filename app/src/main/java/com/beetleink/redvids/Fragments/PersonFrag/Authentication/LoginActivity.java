package com.beetleink.redvids.Fragments.PersonFrag.Authentication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.beetleink.redvids.Fragments.GifyFrag.HomePageTrendingApi;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Login.ReceiverLoginCred;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.UserProfile.UserProfile;
import com.beetleink.redvids.Fragments.PersonFrag.EditAccount;
import com.beetleink.redvids.Fragments.PersonFrag.PersonFragment;
import com.beetleink.redvids.HomeActivity;
import com.beetleink.redvids.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private ImageView logo, ivSignIn, btnTwitter;
    private AutoCompleteTextView email, password;
    private TextView forgotPass, signUp;
    private Button btnSignIn;
    private FirebaseAuth firebaseAuth;
    TextView webCreateNewAccount;

    private ProgressDialog progressDialog;
    public  static String token;
    public  static String tokenType;
    HomePageTrendingApi homePageFeedApi;
    Retrofit retrofit;
    FirebaseDatabase firebaseDatabase;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeGUI();
        retrofit  = new Retrofit.Builder()
                .baseUrl("https://api.redgifs.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        homePageFeedApi = retrofit.create(HomePageTrendingApi.class);

        webCreateNewAccount = findViewById(R.id.webCreateNewAccount);

        //check if already sign in user
        if(firebaseAuth.getCurrentUser() != null) {
            Log.i("unsuccessfullasdf", "yes");
            finish();
            startActivity(new Intent(LoginActivity.this, PersonFragment.class));
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inEmail = email.getText().toString();
                String inPassword = password.getText().toString();

                if(validateInput(inEmail, inPassword)){
                    signUser(inEmail, inPassword);
                }

            }
        });



//        signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
//                Intent openURL = new Intent(android.content.Intent.ACTION_VIEW)
//                        .setData(Uri.parse("https://www.tutorialkart.com/"));
//                startActivity(openURL);
//            }
//        });

        //to create new account
        webCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                Intent openURL = new Intent(android.content.Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://www.redgifs.com/signup"));
                startActivity(openURL);
            }
        });

//        forgotPass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this,PWresetActivity.class));
//            }
//        });

        //forgot password
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openURL = new Intent(android.content.Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://www.redgifs.com/forgot-password"));
                startActivity(openURL);
            }
        });



    }



    public void signUser(String email, String password){

        progressDialog.setMessage("Verificating...");
        progressDialog.show();
        sendingAndReceivingCredential(email,password);





    }


    private void initializeGUI(){

        logo = findViewById(R.id.ivLogLogo);
        ivSignIn = findViewById(R.id.ivSignIn);
//        btnTwitter = findViewById(R.id.ivFacebook);
        email = findViewById(R.id.atvEmailLog);
        password = findViewById(R.id.atvPasswordLog);
        forgotPass = findViewById(R.id.tvForgotPass);
        signUp = findViewById(R.id.tvSignIn);
        btnSignIn = findViewById(R.id.btnSignIn);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

    }


    public boolean validateInput(String inemail, String inpassword){

        if(inemail.isEmpty()){
            email.setError("Email field is empty.");
            return false;
        }
        if(inpassword.isEmpty()){
            password.setError("Password is empty.");
            return false;
        }

        return true;
    }

    //get login and get token

    public void sendingAndReceivingCredential(String userName, String passWord){
        Call<ReceiverLoginCred> sendLoginCall = homePageFeedApi.sendLogin("password",userName, passWord);
        sendLoginCall.enqueue(new Callback<ReceiverLoginCred>() {
            @Override
            public void onResponse(Call<ReceiverLoginCred> call, Response<ReceiverLoginCred> response) {
                if(response.isSuccessful()){
                    Log.i("successfulll1", response.body().getAccessToken());
                    token = response.body().getAccessToken();
                    tokenType = response.body().getTokenType();

                    //creating new username password and email in firebase auth

                    firebaseAuth.createUserWithEmailAndPassword(userName+"@redvids.com",passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendUserDataToFirebaseDatabase(userName+"@redvids.com",passWord,token,tokenType);


                            }else if(task.getException().getClass().getSimpleName().equals("FirebaseAuthUserCollisionException")) {
                                //give output of the username that already exist
//                                usernameErrorMessage.setVisibility(View.VISIBLE);
//                                usernameErrorMessage.setText("username already exist");
                                //store in firebase auth as email and password
                                firebaseAuth.signInWithEmailAndPassword(userName+"@redvids.com", passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            progressDialog.dismiss();
                                            Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                            HomeActivity.afterLoginDefaultFramentChange("yes");
                                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(intent);

                                        }else{
                                            progressDialog.dismiss();
                                            Log.i("unsuccessfull", task.getException().getMessage());
                                        }

                                    }
                                });





                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });




                }else{
                    Log.i("successfulll2", String.valueOf(response.code()));
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Invalid email or password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ReceiverLoginCred> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            }
        });

    };




    //sending user data to firebase database from firebase AUth after registered
    private void sendUserDataToFirebaseDatabase(String inputUsernamme, String inputPassword, String token,String tokenType) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference users = firebaseDatabase.getReference().child("users").child(firebaseAuth.getUid());
        users.child("username").setValue(inputUsernamme);
        users.child("token").setValue(token);
        users.child("tokenType").setValue(tokenType);
        users.child("password").setValue(inputPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

                HomeActivity.afterLoginDefaultFramentChange("yes");
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

}
