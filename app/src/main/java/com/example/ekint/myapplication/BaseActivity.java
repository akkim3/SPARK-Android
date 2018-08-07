package com.example.ekint.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * BaseActivity is the main java parent class of all activities.
 * It contains the fields and methods that are common to all child activities.
 * @author ekint
 * @version 1.0
 */

public class BaseActivity extends AppCompatActivity {

    static final String ENTRY_TRANSFER = "ENTRY_TRANSFER";

    /**
     * Initializes top app bar for activity.
     * @param enableHome boolean value to enable back button on top toolbar
     * @see android.support.v7.widget.Toolbar
     */
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
