package com.test.project.vision.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnnotateImageRequest {

    private List<Request> requests;

    public AnnotateImageRequest(List<Request> requests) {
        this.requests = requests;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
