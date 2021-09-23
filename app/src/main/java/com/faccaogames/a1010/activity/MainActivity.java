package com.faccaogames.a1010.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.faccaogames.a1010.R;
import com.faccaogames.a1010.model.User;
import com.faccaogames.a1010.util.Constants;
import com.faccaogames.a1010.util.Util;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.installations.FirebaseInstallations;

public class MainActivity extends Activity implements DefaultActivity {

    private FirebaseUser firebaseUser;
    private User user;
    private DatabaseReference mUserDatabaseRef;
    private String firebaseInstanceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            getUserDataFromFirebase();
        }
    }

    private void getUserDataFromFirebase() {
        mUserDatabaseRef.child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUserDatabaseRef.child(firebaseUser.getUid()).removeEventListener(this);
                mUserDatabaseRef.removeEventListener(this);
                user = dataSnapshot.getValue(User.class);
                if (user == null || user.getId() == null) {
                    createUser();
                }
                Util.setUser(user);
                updateToken();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateToken() {
        String uid = FirebaseAuth.getInstance().getUid();
        if (uid != null) {
            mUserDatabaseRef.child(Util.getUser().getId()).child(Constants.TOKEN).setValue(firebaseInstanceId);
        }
    }

    private void createUser() {
        String userPhotoPath = null;
        if (firebaseUser.getPhotoUrl() != null) {
            userPhotoPath = firebaseUser.getPhotoUrl().toString();
        }
        user = new User(firebaseUser.getUid(), firebaseInstanceId, firebaseUser.getDisplayName(), userPhotoPath, false, 100, null);

        Util.setUser(user);
        mUserDatabaseRef.child(firebaseUser.getUid()).setValue(user);
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