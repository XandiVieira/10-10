package com.faccaogames.a1010.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.faccaogames.a1010.model.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Util {

    public static User user;
    public static FirebaseUser fbUser;
    public static DatabaseReference mDatabaseRef;

    public Util() {
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Util.user = user;
    }

    public static void setFbUser(FirebaseUser fbUser) {
        Util.fbUser = fbUser;
    }

    public static DatabaseReference getmDatabaseRef() {
        return mDatabaseRef;
    }

    public static void setmDatabaseRef(DatabaseReference mDatabaseRef) {
        Util.mDatabaseRef = mDatabaseRef;
    }

    public static void cleanUtil() {
        setUser(null);
        setFbUser(null);
        setmDatabaseRef(null);
    }
}