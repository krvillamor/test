package com.test.project.yelp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Business {

    private String total;
    private List<SearchBusiness> businesses;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<SearchBusiness> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<SearchBusiness> businesses) {
        this.businesses = businesses;
    }
}
