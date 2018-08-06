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


public class FeedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView rvFeedEntries;
    private FeedRVAdapter feedAdapter;
    private List<FeedEntry> feedList;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String feedURL = "http://fetchrss.com/rss/5b672e658a93f8503d8b4567218562324.xml";
    private static final String TAG = "FeedFragment";
    private String feedCachedURL = "INVALIDATED";

    // TODO: Rename and change types of parameters
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
        downloadURL(feedURL);

        feedAdapter = new FeedRVAdapter(getContext(), feedList);
        rvFeedEntries.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//            GridLayoutManager glm = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        rvFeedEntries.setLayoutManager(llm);
        //Link listView to adapter
        rvFeedEntries.setAdapter(feedAdapter);

        return rootView;

    }

    private void downloadURL(String feedURL){
        if(!feedURL.equalsIgnoreCase(feedCachedURL)) {
            Log.d(TAG, "downloadURL: starting Asynctask");
            DownloadData downloadData = new DownloadData();
            //args put in execute method passed in to doInBackground
            feedCachedURL = feedURL;
            downloadData.execute(feedURL);

            Log.d(TAG, "downloadURL: done");
        } else {
            Log.d(TAG, "downloadURL: URL not changed");
        }
    }

    private class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadData";
        //Have to run doInBackground method, actually does processing
        @Override
        //... means put in multiple (variable #) strings
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: starts with " + strings[0]);
            String rssFeed = downloadXML(strings[0]);
            if (rssFeed == null){
                Log.e(TAG, "doInBackground: Error downloading");
            }
            //This return is parameter in onPostExecute method
            return rssFeed;
        }

        //onPostExecute: what to do when finished, String returned from doInBackground passed into
        @Override
        protected void onPostExecute(String s) {
//            Log.d(TAG, "onPostExecute: parameter is " + s);
            super.onPostExecute(s);
            FeedParser parseFeed = new FeedParser();
            parseFeed.parse(s);
            feedList = parseFeed.getQuoraEntries();


//            Creating ArrayAdapter to create textViews for listView
//            1st parameter: context
//            2nd parameter: layout for resource argument, that's what its going to use to put
//            data in to
//            3rd parameter
//            ArrayAdapter<FeedEntry> arrayAdapter = new ArrayAdapter<FeedEntry>(
//                    MainActivity.this, R.layout.list_item, parseApplications.getApplications());

        }

        private String downloadXML(String urlPath){
            StringBuilder xmlResult = new StringBuilder();
            //Use try catch when getting info from external source
            try {
                URL url = new URL(urlPath);
                //Access site
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                Log.d(TAG, "downloadXML: The response code was " + response);
                /*InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //Buffers input stream reader
                BufferedReader reader = new BufferedReader(inputStreamReader);*/
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                int charsRead;
                char[] inputBuffer = new char[500];

                //Keeps going round till end of InputStream is reached
                while(true){
                    charsRead = reader.read(inputBuffer);
                    if (charsRead < 0){
                        //End of stream of data
                        break;
                    }
                    if (charsRead > 0){
                        xmlResult.append(String.copyValueOf(inputBuffer,0,charsRead));
                    }
                }
                reader.close();
                return xmlResult.toString();
            } catch (MalformedURLException e){
                Log.e(TAG, "downloadXML: Invalid URL " + e.getMessage());
            } catch (IOException e){
                Log.e(TAG, "downloadXML: IO Exception reading data " + e.getMessage());
            } catch (SecurityException e){
                Log.e(TAG, "downloadXML: Security Exception. Needs Permission? " + e.getMessage());
//                e.printStackTrace();
            }
            return null;
        }
    }
}
