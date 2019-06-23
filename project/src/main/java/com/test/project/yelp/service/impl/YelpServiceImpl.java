package com.test.project.yelp.service.impl;

import com.test.project.BusinessReviewBean;
import com.test.project.vision.service.VisionService;
import com.test.project.yelp.pojo.Business;
import com.test.project.yelp.pojo.Review;
import com.test.project.yelp.pojo.ReviewBusiness;
import com.test.project.yelp.pojo.SearchBusiness;
import com.test.project.yelp.service.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class YelpServiceImpl implements YelpService {

    @Autowired
    VisionService visionService;

    private String API_KEY = "WMkUYlWBP6GBNwSDl8Ubxf6gb2yeXBgZzflIfMpGSSxA0q-fvxnYesKSph3fVIBYJo3lELWLsiWDysQpuc9GT6fd6WqAHxhKmFE9YnlrrwNWgTyaKDggz5pjE2EMXXYx";
    private String URL = "https://api.yelp.com/v3/businesses";
    private HttpEntity<String> addHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(API_KEY);
        return new HttpEntity<>("parameters", headers);
    }

    public List<SearchBusiness> getBusiness(String location, String term) throws Exception {
        UriComponentsBuilder builderlocation = UriComponentsBuilder.fromHttpUrl(URL + "/search")
                .queryParam("term", term)
                .queryParam("location",location);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Business> responseEntity = restTemplate.exchange(builderlocation.toUriString(),
                HttpMethod.GET, addHeaders(), Business.class);
        Business searchBusiness = responseEntity.getBody();
        List<SearchBusiness> searchBusinessList = searchBusiness.getBusinesses();
        return searchBusinessList;
    }

    public List<BusinessReviewBean> getReviews(List<SearchBusiness> searchBusinesses) throws Exception {


        List<BusinessReviewBean> businessReviewBeans = new ArrayList<BusinessReviewBean>();
        for(SearchBusiness searchBusiness : searchBusinesses) {
            List<ReviewBusiness> reviewBusinesses = new ArrayList<ReviewBusiness>();
            BusinessReviewBean businessReviewBean = new BusinessReviewBean();
            UriComponentsBuilder builderlocation = UriComponentsBuilder.fromHttpUrl(URL + "/" + searchBusiness.getId() + "/reviews");

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Review> responseEntity = restTemplate.exchange(builderlocation.toUriString(),
                    HttpMethod.GET, addHeaders(), Review.class);
            Review review  = responseEntity.getBody();
            for(ReviewBusiness reviewBusiness: review.getReviews()){
                if(reviewBusiness.getUser().getImage_url() != null) {
                    reviewBusiness.setSentiments(visionService.getSentiment(reviewBusiness.getUser().getImage_url()));
                }
            }
            reviewBusinesses.addAll(review.getReviews());
            businessReviewBean.setName(searchBusiness.getName());
            businessReviewBean.setId(searchBusiness.getId());
            businessReviewBean.setReviews(reviewBusinesses);
            businessReviewBeans.add(businessReviewBean);

        }

        return businessReviewBeans;
    }

    @Override
    public List<BusinessReviewBean> getBusinessReview(String term, String location) throws Exception {
        List<SearchBusiness> searchBusinesses =  getBusiness(location, term);
        List<BusinessReviewBean> reviewBusinesses = getReviews(searchBusinesses);

        return reviewBusinesses;
    }
}
