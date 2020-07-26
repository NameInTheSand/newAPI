package com.example.newsapi;

import android.media.Image;

import androidx.room.Entity;



@Entity
class News {

    private int id;
    private String title;
    private String descriprion;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriprion() {
        return descriprion;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public News(String title, String descriprion, String url) {
        this.title = title;
        this.descriprion = descriprion;
        this.url = url;
    }
}
