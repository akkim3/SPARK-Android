package com.example.ekint.myapplication;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by ekint on 8/5/2018.
 */

public class FeedParser {
    
    public String getTitle() {
        return title;
    }

    private String title;
    private ArrayList<FeedEntry> quoraEntries;
    private static final String TAG = "FeedParser";

    //Constructor
    public FeedParser() {
        //Constructor initializes ArrayList
        this.quoraEntries = new ArrayList<>();
    }

    //Getter for ArrayList
    public ArrayList<FeedEntry> getQuoraEntries() {
        return quoraEntries;
    }

    public boolean parse(String xmlData){
        boolean status = true;
        FeedEntry currentRecord = null;
        //Looking at tags inside entry or not?
        boolean inItem = false;
        boolean inTitle = false;
        boolean inDescription = false;
        String textValue = "";
        String descriptionValue = "";

        try{
            //Factory produces pull parser code for us
            //When don't actually know what class will be used to parse
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            //StringReader efficient way of processing Strings
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            //Keeps going till end of Document
            while(eventType != XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
//                        Log.d(TAG, "parse: Starting tag for " + tagName);
                        if("item".equalsIgnoreCase(tagName)){
                            inItem = true;
                            currentRecord = new FeedEntry();
                        }

                        if("title".equalsIgnoreCase(tagName) && inItem == false){
                            inTitle = true;
                        }

                        if("description".equalsIgnoreCase(tagName)){
                            Log.d(TAG, "parse: go inside description");
                            inDescription = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Ending tag for " + tagName);
                        if (inItem){

                            if(inDescription){
                                Log.d(TAG, "parse: inDescription");
                                Document doc = Jsoup.parse(textValue);
                                Elements test = doc.select("p");
                                descriptionValue = test.text();
                            }
                            //Write order to avoid null
                            if("item".equalsIgnoreCase(tagName)){
                                quoraEntries.add(currentRecord);
                                inItem = false;
                            } else if("title".equalsIgnoreCase(tagName)){
                                currentRecord.setTitle(textValue);
                            } else if ("creator".equalsIgnoreCase(tagName)){
                                currentRecord.setCreator(textValue);
                            } else if ("pubDate".equalsIgnoreCase(tagName)){
                                currentRecord.setDate(textValue);
                            } else if ("description".equalsIgnoreCase(tagName)){
                                Log.d(TAG, "parse: here" + descriptionValue);
                                currentRecord.setDescription(descriptionValue);
                            }
                        } else {
                            if ("title".equalsIgnoreCase(tagName)){
                                title = textValue;
                            }
                        }
                        break;
                    default:
                        //Nothing else to do
                }
                //Keep working till next interesting event happens
                eventType = xpp.next();
            }
            //Scrolling through ArrayList
//            for (FeedEntry app: applications){
//                Log.d(TAG, "parse: *****************");
//                Log.d(TAG, app.toString());
//            }

        } catch (Exception e){
            //Catch any exception
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
