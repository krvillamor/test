package com.test.project.yelp.service;

import com.test.project.BusinessReviewBean;
import org.springframework.stereotype.Service;

import java.util.List;


public interface YelpService {

     List<BusinessReviewBean> getBusinessReview(String term, String location) throws Exception;


}
