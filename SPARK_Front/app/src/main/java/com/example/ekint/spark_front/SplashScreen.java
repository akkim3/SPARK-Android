package com.example.ekint.spark_front;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2500;
    private TextView tvApp;
    private ImageView ivLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvApp = (TextView) findViewById(R.id.tvApp);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.transition1);
        tvApp.startAnimation(anim1);
        ivLogo.startAnimation(anim1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashScreen.this, PersonActivity.class);
                startActivity(homeIntent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

}
