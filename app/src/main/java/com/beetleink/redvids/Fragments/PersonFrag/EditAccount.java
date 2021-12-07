package com.beetleink.redvids.Fragments.PersonFrag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beetleink.redvids.Fragments.PersonFrag.Authentication.RegistrationActivity;
import com.beetleink.redvids.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditAccount extends AppCompatActivity {
    Intent intent;
    FirebaseAuth firebaseAuth;
    EditText editUserName;
    MaterialButton confirmNewUserNameButton;
    TextView changeProfilePics;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        firebaseAuth = FirebaseAuth.getInstance();
        editUserName = findViewById(R.id.editUserName);
        confirmNewUserNameButton = findViewById(R.id.confirmNewUserNameButton);
        changeProfilePics = findViewById(R.id.changeProfilePics);
        firebaseDatabase = FirebaseDatabase.getInstance();
//        autofillEditText();
    }

//    void autofillEditText(){
//        editUserName.setText(firebaseAuth.getCurrentUser().get.toString());
//
//    }





    //connect google account details to the firebase auth
    private  void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Log.i("somethi0ngWentWrong2", "Successful");


                        } else {
                            Toast.makeText(EditAccount.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            Log.i("somethi0ngWentWrong3", task.getException().toString());

                        }
                    }
                });
    }
}