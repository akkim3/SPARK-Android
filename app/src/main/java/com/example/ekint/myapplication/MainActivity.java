package com.example.ekint.myapplication;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Entry> entryList;
    private android.support.v7.widget.Toolbar toolbar;
    private RecyclerView rvEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
//        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
//        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout

        rvEntries = (RecyclerView) findViewById(R.id.rvEntries);
        rvEntries.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvEntries.setLayoutManager(llm);

        initializeData();
        EntryMainRVAdapter entryAdapter = new EntryMainRVAdapter(this, entryList);
        rvEntries.setAdapter(entryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    private void initializeData(){
        entryList = new ArrayList<Entry>();
        entryList.add(new Entry("Son throwing tantrums", "null"));
        entryList.add(new Entry("Crying", "null"));
        entryList.add(new Entry("Can't sleep", "null"));
        entryList.add(new Entry("Eating random things", "null"));
        entryList.add(new Entry("rip me", "null"));
        entryList.add(new Entry("too loud", "null"));

    }
}
