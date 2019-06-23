package com.test.project.yelp;


import com.test.project.yelp.service.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class YelpController {

    @Autowired
    private YelpService yelpService;

    @RequestMapping("/search")
    public Object search(@RequestParam String term, @RequestParam(name = "location", defaultValue = "Manila") String location) throws Exception {
       return yelpService.getBusinessReview("Manila", "jolibee");
    }


}



