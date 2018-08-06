package com.example.ekint.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by ekint on 8/3/2018.
 */

public class BaseActivity extends AppCompatActivity {

    static final String ENTRY_TRANSFER = "ENTRY_TRANSFER";

    void activateToolbar(boolean enableHome){
        ActionBar ab = getSupportActionBar();
        if(ab == null){
            android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);

            if(toolbar != null){
                setSupportActionBar(toolbar);
                ab = getSupportActionBar();
                ab.setDisplayShowCustomEnabled(true);

            }

        }

        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(enableHome);
        }
    }
}
