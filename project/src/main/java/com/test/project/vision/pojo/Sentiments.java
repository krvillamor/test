package com.test.project.vision.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sentiments {

    private String joyLikelihood;
    private String sorrowLikelihood;

    public Sentiments(String joyLikelihood, String sorrowLikelihood) {
        this.joyLikelihood = joyLikelihood;
        this.sorrowLikelihood = sorrowLikelihood;
    }

    public String getJoyLikelihood() {
        return joyLikelihood;
    }

    public void setJoyLikelihood(String joyLikelihood) {
        this.joyLikelihood = joyLikelihood;
    }

    public String getSorrowLikelihood() {
        return sorrowLikelihood;
    }

    public void setSorrowLikelihood(String sorrowLikelihood) {
        this.sorrowLikelihood = sorrowLikelihood;
    }
}
