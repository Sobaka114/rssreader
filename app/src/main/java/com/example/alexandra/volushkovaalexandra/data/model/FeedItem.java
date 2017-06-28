package com.example.alexandra.volushkovaalexandra.data.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * data model for news item
 * Created by Alexandra on 09.06.2017.
 */

public class FeedItem implements Parcelable {
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

    protected FeedItem(Parcel in) {
        bmp = in.readParcelable(Bitmap.class.getClassLoader());
        title = in.readString();
        source = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        itemUrl = in.readString();
    }

    public static final Creator<FeedItem> CREATOR = new Creator<FeedItem>() {
        @Override
        public FeedItem createFromParcel(Parcel in) {
            return new FeedItem(in);
        }

        @Override
        public FeedItem[] newArray(int size) {
            return new FeedItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bmp, flags);
        dest.writeString(title);
        dest.writeString(source);
        dest.writeString(imageUrl);
        dest.writeString(description);
        dest.writeString(itemUrl);
    }
}
