package com.example.ekint.myapplication;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ekint
 * @author ayushs
 * @version 1.0
 */

public class Entry implements Comparable<Entry>{
    private String title;
    private Date date;
    private String time;
    private String description;
    private String image;

    public Entry(String title, Date date, String time, String description, String image) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.description = description;
        this.image = image;
    }

    public Entry(String title, String image){
        this.title = title;
        this.image = image;
    }

    public Entry(String title, String image, Date date){
        this.title = title;
        this.image = image;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + title + '\'' +
                ", mDate='" + date + '\'' +
                ", mTime='" + time + '\'' +
                ", mDescription='" + description + '\'' +
                ", mImage='" + image + '\'' +
                '}';
    }

    @Override
    public int compareTo(Entry o) {
        return getDate().compareTo(o.getDate());
    }
}
