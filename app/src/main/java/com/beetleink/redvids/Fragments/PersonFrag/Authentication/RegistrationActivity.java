package com.beetleink.redvids.Fragments.PersonFrag.Authentication;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    TextView usernameErrorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initializeGUI();
        emailSignProcessButtonPressed();
        onTextChangeListenerForUsername(inputRegistrationUsername);

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
        usernameErrorMessage = findViewById(R.id.usernameErrorMessage);

        signin = findViewById(R.id.tvSignIn);
        signup = findViewById(R.id.btnSignUp);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        googleSignInButton = findViewById(R.id.googleSignInButton);
        firebaseDatabase = FirebaseDatabase.getInstance();


    }

    //Firebase email signin process started or login process started.....
    void emailSignProcessButtonPressed(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //after Registration form validated
                if(Validator(inputRegistrationUsername, inputEmailRegistration, inputRegistrationPassword,inputRegistrationConfirmPassword)){


                    //Done Registration with firebase auth email
                    String newUsername = inputRegistrationUsername.getText().toString()+"@redvids.com";
                    registerFromFirebaseEmailPassword(newUsername,inputRegistrationPassword.getText().toString(),inputEmailRegistration.getText().toString());


                };
            }
        });

        //Login button Pressed!
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

            }
        });
    }

    //validate the user input that they fill in registration form...
     private Boolean Validator(FormEditText Username,FormEditText Email,
                               FormEditText Password,FormEditText ConfirmPassword){
         FormEditText[] allFields	= { Username, Email, Password, ConfirmPassword };
         boolean allValid = true;
         for (FormEditText field: allFields) {
             allValid = field.testValidity() && allValid;
         }

         if (!allValid) {
             // user didn't type as define
             return  false;

         }

         //password confirmation
         if(Password.getText().toString().equals(ConfirmPassword.getText().toString())){
             return  true;
         }else{
             //set error message
             inputRegistrationConfirmPassword.setError("Password is not matching");
             return  false;
         }







     }


    //From FirebaseAuthRegister Username and password in email auth
    private void registerFromFirebaseEmailPassword(final String inputUsernamme, final String inputPassword, final  String inputEmail) {

        Toast.makeText(getApplicationContext(), "Checking...", Toast.LENGTH_SHORT).show();


        //creating new username password and email in firebase auth

        firebaseAuth.createUserWithEmailAndPassword(inputUsernamme,inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    sendUserDataToFirebaseDatabase(inputUsernamme,inputPassword,inputEmail);

                }else if(task.getException().getClass().getSimpleName().equals("FirebaseAuthUserCollisionException")) {
                    //give output of the username that already exist
                    usernameErrorMessage.setVisibility(View.VISIBLE);
                    usernameErrorMessage.setText("username already exist");
                }else{
                    Toast.makeText(RegistrationActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }


            }
        });

}
    //sending user data to firebase database from firebase AUth after registered
    private void sendUserDataToFirebaseDatabase(String inputUsernamme, String inputPassword, String inputEmail) {

            firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference users = firebaseDatabase.getReference().child("users").child(firebaseAuth.getUid());
            users.child("username").setValue(inputUsernamme);
            users.child("email").setValue(inputEmail);
            users.child("password").setValue(inputPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Intent intent = new Intent(RegistrationActivity.this, EditAccount.class);
                    startActivity(intent);
                }
            });
        }

    //function to remove visibility when any key change in editText of username
    void onTextChangeListenerForUsername(FormEditText formEditText){
        formEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                usernameErrorMessage.setVisibility(View.GONE);

            }
        });
    }


}




