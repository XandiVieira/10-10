package com.faccaogames.a1010.activity;

import android.app.Activity;
import android.os.Bundle;

import com.faccaogames.a1010.R;

public class MainActivity extends Activity implements DefaultActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setLayoutAttributes() {

    }
}