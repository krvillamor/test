package com.test.project.yelp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {

    private List<ReviewBusiness> reviews;

    public List<ReviewBusiness> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewBusiness> reviews) {
        this.reviews = reviews;
    }
}
