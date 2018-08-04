package com.example.ekint.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements RecyclerItemClickListener.OnRecyclerClickListener{

    private List<Entry> entryList;
    private android.support.v7.widget.Toolbar toolbar;
    private RecyclerView rvEntries;
    private RecyclerView rvEntriesLand;
    private EntryMainRVAdapter entryAdapter;
    private BottomNavigationView bnvView;
    private static final String TAG = "MainActivity";

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

        Configuration config = getResources().getConfiguration();
        initializeData();

        entryAdapter = new EntryMainRVAdapter(this, entryList);
//        Set up Recycler View
        if(config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvEntries = (RecyclerView) findViewById(R.id.rvEntriesLand);
            rvEntries.setHasFixedSize(true);

//            LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            GridLayoutManager glm = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
            rvEntries.setLayoutManager(glm);

            rvEntries.addOnItemTouchListener(new RecyclerItemClickListener(this, rvEntries, this));
            rvEntries.setAdapter(entryAdapter);
        } else if (config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            rvEntriesLand = (RecyclerView) findViewById(R.id.rvEntriesLand);
            rvEntriesLand.setHasFixedSize(true);

            LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//            GridLayoutManager glm = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
            rvEntriesLand.setLayoutManager(llm);
            rvEntriesLand.setAdapter(entryAdapter);
        }

        bnvView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bnvView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_addentry:
                                Intent addEntry = new Intent(MainActivity.this, AddEntry.class);
                                startActivity(addEntry);
                                break;
                            case R.id.action_journal:
                                break;
                            case R.id.action_chart:
                                break;
                        }
                        return false;
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(MainActivity.this, "Normal tap at position " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EntryDetailActivity.class);
        intent.putExtra(ENTRY_TRANSFER, entryAdapter.getEntry(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Log.d(TAG, "onItemLongClick: starts");
//        Toast.makeText(MainActivity.this, "Long tap at position " + position, Toast.LENGTH_SHORT).show();
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
