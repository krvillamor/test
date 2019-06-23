package com.test.project.yelp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.project.vision.pojo.Sentiments;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewBusiness {

    private String id;

    private String rating;

    private String text;

    private User user;

    private Sentiments sentiments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sentiments getSentiments() {
        return sentiments;
    }

    public void setSentiments(Sentiments sentiments) {
        this.sentiments = sentiments;
    }
}
