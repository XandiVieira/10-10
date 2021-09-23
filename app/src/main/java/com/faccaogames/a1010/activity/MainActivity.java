package com.faccaogames.a1010.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.faccaogames.a1010.R;
import com.faccaogames.a1010.model.User;
import com.faccaogames.a1010.util.Constants;
import com.faccaogames.a1010.util.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;

public class MainActivity extends Activity implements DefaultActivity {

    private Activity activity;
    private FirebaseUser firebaseUser;
    private User user;
    private DatabaseReference mUserDatabaseRef;
    private String firebaseInstanceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        startFirebaseInstances();
        setLayoutAttributes();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseInstallations.getInstance().getToken(true).addOnCompleteListener(task -> {
            if (task.getResult() != null) {
                firebaseInstanceId = task.getResult().getToken();
            }
        });
        if (firebaseUser == null) {
            goLoginScreen();
        } else if (user == null) {
            Util.setFbUser(firebaseUser);
        }
    }

    private void startFirebaseInstances() {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseRef = mFirebaseDatabase.getReference();
        mUserDatabaseRef = mDatabaseRef.child(Constants.DATABASE_REF_USER);
        Util.setmDatabaseRef(mDatabaseRef);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void setLayoutAttributes() {

    }
}