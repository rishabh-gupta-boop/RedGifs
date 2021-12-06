package com.beetleink.redvids.Fragments.PersonFrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.beetleink.redvids.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class EditAccount extends AppCompatActivity implements  com.beetleink.redvids.Fragments.PersonFrag.Authentication.getGoogleSignInAccount {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);


    }


    @Override
    public GoogleSignInAccount account() {
        return account();
    }
}