package com.example.ekint.spark_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ChildQuestion extends AppCompatActivity {
    @BindView(R.id.happyFAB)
    FloatingActionButton happyFAB;
    @BindView(R.id.happy2FAB)
    FloatingActionButton happy2FAB;
    @BindView(R.id.midFAB)
    FloatingActionButton midFAB;
    @BindView(R.id.sadFAB)
    FloatingActionButton sadFAB;
    @BindView(R.id.angryFAB)
    FloatingActionButton angryFAB;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_child_question);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.happyFAB, R.id.happy2FAB, R.id.midFAB, R.id.sadFAB, R.id.angryFAB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.happyFAB:
                Intent happyIntent = new Intent(ChildQuestion.this, PersonActivity.class);
                startActivity(happyIntent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                break;
            case R.id.happy2FAB:
                Intent happy2Intent = new Intent(ChildQuestion.this, PersonActivity.class);
                startActivity(happy2Intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                break;
            case R.id.midFAB:
                Intent midIntent = new Intent(ChildQuestion.this, PersonActivity.class);
                startActivity(midIntent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                break;
            case R.id.sadFAB:
                Intent sadIntent = new Intent(ChildQuestion.this, PersonActivity.class);
                startActivity(sadIntent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                break;
            case R.id.angryFAB:
                Intent angryIntent = new Intent(ChildQuestion.this, PersonActivity.class);
                startActivity(angryIntent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                break;
        }
    }
}
