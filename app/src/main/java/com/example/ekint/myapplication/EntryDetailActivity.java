package com.example.ekint.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ekint on 8/3/2018.
 */

public class EntryDetailActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_detail);

        Intent intent = getIntent();
        Entry entry = (Entry) intent.getSerializableExtra(ENTRY_TRANSFER);
        if(entry != null){
            TextView entryTitle = (TextView) findViewById(R.id.tvTitleDetail);
            Resources resources = getResources();
            entryTitle.setText(entry.getTitle());

            TextView entryDate = (TextView) findViewById(R.id.tvDate);
            entryDate.setText(entry.getDate());
//            photoTags.setText("Tags: " + photo.getTags());

            TextView entryDescription = (TextView) findViewById(R.id.tvDescription);
            entryDescription.setText(entry.getDescription());

            ImageView entryImage = (ImageView) findViewById(R.id.ivImageDetail);
            entryImage.setImageResource(R.drawable.baseline_image_black_48dp);
        }
    };

}
