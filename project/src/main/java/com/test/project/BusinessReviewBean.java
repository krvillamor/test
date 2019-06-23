package com.test.project;


import com.test.project.yelp.pojo.ReviewBusiness;

import java.util.List;

public class BusinessReviewBean {

    private String id;

    private String name;

    private List<ReviewBusiness> reviews;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReviewBusiness> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewBusiness> reviews) {
        this.reviews = reviews;
    }
}
