package com.example.ekint.spark_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PersonActivity extends AppCompatActivity {

    @BindView(R.id.llChild)
    LinearLayout Child;
    @BindView(R.id.llParent)
    LinearLayout llParent;
    @BindView(R.id.llDoctor)
    LinearLayout llDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.llChild, R.id.llParent, R.id.llDoctor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llChild:
                Intent homeIntent = new Intent(PersonActivity.this, ChildQuestion.class);
                startActivity(homeIntent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                break;
            case R.id.llParent:
                Intent homeIntent2 = new Intent(PersonActivity.this, NavigationActivity.class);
                startActivity(homeIntent2);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                break;
            case R.id.llDoctor:
                Intent homeIntent3 = new Intent(PersonActivity.this, NavigationActivity.class);
                startActivity(homeIntent3);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
        }
    }
}