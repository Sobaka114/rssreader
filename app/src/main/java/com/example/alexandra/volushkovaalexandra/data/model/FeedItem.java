package com.example.alexandra.volushkovaalexandra.data.model;

import android.graphics.Bitmap;

/**
 * data model for news item
 * Created by Alexandra on 09.06.2017.
 */

public class FeedItem {
    private final Bitmap bmp;
    private String title;
    private String source;
    private String imageUrl;
    private String description;
    private String itemUrl;

    public FeedItem(String title, String link, String source, String description, String imageLink, Bitmap bmp) {
        this.title = title;
        this.itemUrl = link;
        this.description = description;
        this.source = source;
        this.imageUrl = imageLink;
        this.bmp = bmp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public Bitmap getBmp() {
        return bmp;
    }
}
