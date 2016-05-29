package com.lanou3g.an.carhome;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import java.io.Serializable;

/**
 * Entity mapped to table "COLLECTION".
 */
public class Collection implements Serializable{

    private Long id;
    private String title;
    private String url;
    private String imageUrl;

    public Collection() {
    }

    public Collection(Long id) {
        this.id = id;
    }

    public Collection(Long id, String title, String url, String imageUrl) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}