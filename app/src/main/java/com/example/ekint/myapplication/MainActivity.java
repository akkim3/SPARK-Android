package com.example.ekint.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

/**
 * The Main Activity that houses all fragments seen on main BNV
 * @author ekint
 * @version 1.0
 * Date: 8/3/18
 */
public class MainActivity extends BaseActivity implements RecyclerItemClickListener.OnRecyclerClickListener{

    private List<Entry> entryList;
    private ImageView toolbarLogo;
    private android.support.v7.widget.Toolbar toolbar;
    private RecyclerView rvEntries;
    private RecyclerView rvEntriesLand;
    private FeedRVAdapter entryAdapter;
    private BottomNavigationView bnvView;
    private AHBottomNavigation ahBottomNavigation;
    private NoSwipePager viewPager;
    private BottomBarAdapter pagerAdapter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_coord);

        activateToolbar(false);

        Configuration config = getResources().getConfiguration();
        initializeData();

        downloadURL(feedURL);
        setupViewPager();
        setupBNV();

    }

    /**
     * Creates overflow menu icon on upper-right hand corner of toolbar
     * @param menu menu input, not used
     * @return     true for success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    /**
     * Normal click listener for card views in main fragment recycler view
     * @param view     view to listen for
     * @param position position of item in recycler view
     */
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(MainActivity.this, "Normal tap at position " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EntryDetailActivity.class);
        intent.putExtra(ENTRY_TRANSFER, entryAdapter.getEntry(position));
        startActivity(intent);
    }

    /**
     * Long click listener for card views in main fragment recycler view
     * last time checked, did not work
     * @param view     view to listen for
     * @param position position of item in recycler view
     */
    @Override
    public void onItemLongClick(View view, int position) {
        Log.d(TAG, "onItemLongClick: starts");
//        Toast.makeText(MainActivity.this, "Long tap at position " + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * Temporary method to add fake entries to the entryList arrayList
     * contents feed into recycler view
     */
    private void initializeData(){
        entryList = new ArrayList<Entry>();
        entryList.add(new Entry("Son throwing tantrums", "null"));
        entryList.add(new Entry("Crying", "null"));
        entryList.add(new Entry("Can't sleep", "null"));
        entryList.add(new Entry("Eating random things", "null"));
        entryList.add(new Entry("rip me", "null"));
        entryList.add(new Entry("too loud", "null"));

    }

    /**
     * Method sets up bottom navigation view for activity
     */
    private void setupBNV(){
        ahBottomNavigation = (AHBottomNavigation) findViewById(R.id.ah_bottom_navigation);
        addBottomNavigationItems();
        ahBottomNavigation.setCurrentItem(0);

        ahBottomNavigation.setDefaultBackgroundColor(Color.WHITE);
        ahBottomNavigation.setAccentColor(fetchColor(R.color.accent));
        ahBottomNavigation.setInactiveColor(fetchColor(R.color.colorBottomNavigationInactive));
//        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        ahBottomNavigation.setBehaviorTranslationEnabled(true);

        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (!wasSelected){
                    viewPager.setCurrentItem(position);
                }
                return true;
            }
        });
    }

    /**
     * Adds each item on bottom navigation view, icons
     */
    private void addBottomNavigationItems(){
        AHBottomNavigationItem itemHome = new AHBottomNavigationItem(R.string.text_home, R.drawable.baseline_home_black_48dp, R.color.accent);
        AHBottomNavigationItem itemJournal = new AHBottomNavigationItem(R.string.text_journal, R.drawable.baseline_book_black_48dp, R.color.accent);
        AHBottomNavigationItem itemChart = new AHBottomNavigationItem(R.string.text_chart, R.drawable.baseline_show_chart_black_48dp, R.color.accent);
        AHBottomNavigationItem itemFeed = new AHBottomNavigationItem(R.string.text_feed, R.drawable.baseline_question_answer_black_48dp, R.color.accent);

        ahBottomNavigation.addItem(itemHome);
        ahBottomNavigation.addItem(itemJournal);
        ahBottomNavigation.addItem(itemChart);
        ahBottomNavigation.addItem(itemFeed);
    }

    /**
     * Sets up view pager for bottom navigation view
     */
    private void setupViewPager() {
        viewPager = (NoSwipePager) findViewById(R.id.view_pager);
        viewPager.setPagingEnabled(false);
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        pagerAdapter.addFragments(createFragment(R.color.accent));
        pagerAdapter.addFragments(createJournalFragment(R.color.accent));
        pagerAdapter.addFragments(createFragment(R.color.accent));
        pagerAdapter.addFragments(createFeedFragment(R.color.accent));

        viewPager.setAdapter(pagerAdapter);
    }

    @NonNull
    private MainFragment createFragment(int color) {
        MainFragment fragment = new MainFragment();
        fragment.setArguments(passFragmentArguments(fetchColor(color)));
        return fragment;
    }

    @NonNull
    private JournalFragment createJournalFragment(int color) {
        JournalFragment fragment = new JournalFragment();
        fragment.setArguments(passFragmentArguments(fetchColor(color)));
        return fragment;
    }

    @NonNull
    private FeedFragment createFeedFragment(int color) {
        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(passFragmentArguments(fetchColor(color)));
        return fragment;
    }

    @NonNull
    private Bundle passFragmentArguments(int color) {
        Bundle bundle = new Bundle();
        bundle.putInt("color", color);
        return bundle;
    }

    /**
     * Gets specific color given resource id
     * @param color resource id of needed color, ex. R.color._
     * @return      integer that corresponds to respective color
     */
    private int fetchColor(@ColorRes int color){
        return ContextCompat.getColor(this, color);
    }
}
