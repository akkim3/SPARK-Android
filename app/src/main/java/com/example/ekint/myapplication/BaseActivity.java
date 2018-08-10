package com.example.ekint.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * BaseActivity is the main java parent class of all activities.
 * It contains the fields and methods that are common to all child activities.
 * @author ekint
 * @version 1.0
 */

public class BaseActivity extends AppCompatActivity {

    static final String ENTRY_TRANSFER = "ENTRY_TRANSFER";
    static final String feedURL = "http://fetchrss.com/rss/5b672e658a93f8503d8b4567218562324.xml";
    private static final String TAG = "BaseActivity";

    /**
     * Initializes top app bar for activity.
     * @param enableHome boolean value to enable back button on top toolbar
     * @see android.support.v7.widget.Toolbar
     */
    void activateToolbar(boolean enableHome){
        ActionBar ab = getSupportActionBar();
        if(ab == null){
            android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);

            if(toolbar != null){
                setSupportActionBar(toolbar);
                ab = getSupportActionBar();
                ab.setDisplayShowCustomEnabled(true);

            }

        }

        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(enableHome);
        }
    }

    public void downloadURL(String feedURL){
        if(!feedURL.equalsIgnoreCase(FeedFragment.feedCachedURL)) {
            Log.d(TAG, "downloadURL: starting Asynctask");
            DownloadData downloadData = new DownloadData();
            //args put in execute method passed in to doInBackground
            FeedFragment.feedCachedURL = feedURL;
            downloadData.execute(feedURL);

            Log.d(TAG, "downloadURL: done");
        } else {
            Log.d(TAG, "downloadURL: URL not changed");
        }
    }

    /**
     * DownloadData class is an Asynchronous Task that pulls data from an RSS feed
     * and sends it through a FeedParser
     */
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
            FeedFragment.feedList = parseFeed.getQuoraEntries();


//            Creating ArrayAdapter to create textViews for listView
//            1st parameter: context
//            2nd parameter: layout for resource argument, that's what its going to use to put
//            data in to
//            3rd parameter
//            ArrayAdapter<FeedEntry> arrayAdapter = new ArrayAdapter<FeedEntry>(
//                    MainActivity.this, R.layout.list_item, parseApplications.getApplications());

        }

        /**
         * Downloads string from RSS feed.
         * @param urlPath url of RSS feed
         * @return raw rss string, yet to be parsed
         */
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
