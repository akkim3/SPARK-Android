package com.example.ekint.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * FeedFragment is the fragment that displays the q&a feed.
 * Fourth, Last in Bottom Navigation View
 * @author ekint
 * @version 1.0
 * Date: 8/6/18
 */
public class FeedFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView rvFeedEntries;
    private FeedRVAdapter feedAdapter;
    static List<FeedEntry> feedList;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "FeedFragment";
    static String feedCachedURL = "INVALIDATED";

    public List<FeedEntry> getFeedList() {
        return feedList;
    }

    public void setFeedList(List<FeedEntry> feedList) {
        this.feedList = feedList;
    }

    private String mParam1;
    private String mParam2;

    public FeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        Configuration config = getResources().getConfiguration();
//        Set up Recycler View
        rvFeedEntries = (RecyclerView) rootView.findViewById(R.id.rvFeedEntries);

        feedAdapter = new FeedRVAdapter(getContext(), feedList);
        rvFeedEntries.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//            GridLayoutManager glm = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        rvFeedEntries.setLayoutManager(llm);
        //Link listView to adapter
        rvFeedEntries.setAdapter(feedAdapter);

        return rootView;

    }

}
