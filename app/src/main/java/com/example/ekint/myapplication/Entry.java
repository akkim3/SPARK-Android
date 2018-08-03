package com.example.ekint.myapplication;

/**
 * Created by ekint on 8/3/2018.
 */

public class Entry {
    private String title;
    private String date;
    private String time;
    private String description;
    private String image;

    public Entry(String title, String date, String time, String description, String image) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
