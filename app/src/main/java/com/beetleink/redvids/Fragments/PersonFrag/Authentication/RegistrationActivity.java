package com.beetleink.redvids.Fragments.PersonFrag.Authentication;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.andreabaccega.widget.EditTextValidator;
import com.andreabaccega.widget.FormEditText;
import com.beetleink.redvids.Fragments.PersonFrag.EditAccount;
import com.beetleink.redvids.HomeActivity;
import com.beetleink.redvids.Fragments.GifyFrag.Adapter;
import com.beetleink.redvids.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shobhitpuri.custombuttons.GoogleSignInButton;


import static android.content.ContentValues.TAG;

public class RegistrationActivity extends AppCompatActivity {

    private ImageView logo;
    private FormEditText inputRegistrationUsername, inputEmailRegistration, inputRegistrationPassword,inputRegistrationConfirmPassword;
    private Button signup;
    private TextView signin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    GoogleSignInButton googleSignInButton;
    public static GoogleSignInAccount account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initializeGUI();
        emailSignProcessButtonPressed();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Adapter.viewHolder.releasePlayer();
        startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
    }

    private void initializeGUI(){

        logo = findViewById(R.id.ivRegLogo);
        inputRegistrationUsername = findViewById(R.id.inputRegistrationUsername);
        inputEmailRegistration =  findViewById(R.id.inputEmailRegistration);
        inputRegistrationPassword = findViewById(R.id.inputRegistrationPassword);
        inputRegistrationConfirmPassword = findViewById(R.id.inputRegistrationConfirmPassword);

        signin = findViewById(R.id.tvSignIn);
        signup = findViewById(R.id.btnSignUp);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        googleSignInButton = findViewById(R.id.googleSignInButton);


    }

    //Firebase email signin process started or login process started.....
    void emailSignProcessButtonPressed(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validator(inputRegistrationUsername, inputEmailRegistration, inputRegistrationPassword,inputRegistrationConfirmPassword);
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

            }
        });
    }

    //match confirm password and password text are matched in real time
     private Boolean Validator(FormEditText Username,FormEditText Email,
                               FormEditText Password,FormEditText ConfirmPassword){
         FormEditText[] allFields	= { Username, Email, Password, ConfirmPassword };
         boolean allValid = false;
         for (FormEditText field: allFields) {
             allValid = field.testValidity() && allValid;
         }

         if (allValid) {
             // YAY
             allValid= true;

         } else {
             // EditText are going to appear with an exclamation mark and an explicative message.
             allValid = false;


         }

         //password confirmation

         if(Password.getText().toString().equals(ConfirmPassword.getText().toString())){
             allValid=true;
         }else{
             //set error message
             inputRegistrationConfirmPassword.setError("Password is not matching");
             allValid=false;
         }

         Toast.makeText(this, "Registering...", Toast.LENGTH_SHORT).show();
         return allValid;


     }


}

