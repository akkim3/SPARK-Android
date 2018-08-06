package com.example.ekint.myapplication;

import java.io.Serializable;

/**
 * Created by ekint on 8/5/2018.
 */

public class FeedEntry implements Serializable{

//    Fields
    private String title;
    private String creator;
    private String date;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  "title='" + title + '\n' +
                ", creator='" + creator + '\n' +
                ", date='" + date + '\n' +
                ", description='" + description + '\n';
    }
}
