package com.example.ekint.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * @author ayushs
 * @version 1.0
 */
public class SubJournalTimeline extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ScrollView svEntries;
    private EntryMainRVAdapter entryAdapter;
    private List<Entry> entryList;
    ArrayList<Date> dateList;
    private LinearLayout lvSvEntries;


    public SubJournalTimeline() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubJournalTimeline.
     */
    // TODO: Rename and change types and number of parameters
    public static SubJournalTimeline newInstance(String param1, String param2) {
        SubJournalTimeline fragment = new SubJournalTimeline();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sub_journal_timeline, container, false);


        Configuration config = getResources().getConfiguration();
        initializeData();

        /*entryAdapter = new EntryMainRVAdapter(getContext(), entryList);
//        Set up Recycler View

        rvEntries = (RecyclerView) rootView.findViewById(R.id.rvEntriesJournalTimeline);
        rvEntries.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//            GridLayoutManager glm = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        rvEntries.setLayoutManager(llm);
//            rvEntries.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), rvEntries));
        rvEntries.setAdapter(entryAdapter);
        */

        svEntries = (ScrollView) rootView.findViewById(R.id.svTimeline);

        lvSvEntries = (LinearLayout) rootView.findViewById(R.id.lvSvTimeline);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        for (Entry e : entryList) {
            TextView valueTV = new TextView(getContext());
            valueTV.setText(e.getDate().toString());
            valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            lvSvEntries.addView(valueTV);

            CardView card = new CardView(getContext());

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            card.setLayoutParams(params);

            // Set CardView corner radius
            card.setRadius(9);

            // Set cardView content padding
            card.setContentPadding(15, 15, 15, 15);

            // Set a background color for CardView
            card.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));

            // Set the CardView maximum elevation
            card.setMaxCardElevation(15);

            // Set CardView elevation
            card.setCardElevation(9);

            // Initialize a new TextView to put in CardView
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(params);
            tv.setText(e.getTitle());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
            tv.setTextColor(Color.RED);

            // Put the TextView in CardView
            card.addView(tv);
            lvSvEntries.addView(card);
        }
        return rootView;

    }

    private void initializeData() {
        entryList = new ArrayList<Entry>();
        entryList.add(new Entry("Son throwing tantrums", "null", new Date(2018, 7, 23)));
        entryList.add(new Entry("Crying", "null", new Date(2017, 7, 4)));
        entryList.add(new Entry("Can't sleep", "null", new Date(2018, 8, 7)));
        entryList.add(new Entry("Eating random things", "null", new Date(2018, 8, 3)));
        entryList.add(new Entry("rip me", "null", new Date(2017, 12, 31)));
        entryList.add(new Entry("too loud", "null", new Date(2018, 8, 1)));

        Collections.sort(entryList);

        HashSet<Date> dates = new HashSet<>();
        for (Entry e : entryList)
            dates.add(e.getDate());

        dateList = new ArrayList<>();
        Iterator<Date> setIter = dates.iterator();
        while(setIter.hasNext())
            dateList.add(setIter.next());



    }
}
