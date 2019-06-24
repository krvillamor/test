package com.test.project.vision.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.vision.pojo.*;
import com.test.project.vision.service.VisionService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VisionServiceImpl implements VisionService {
    private static final String API_KEY = "AIzaSyCR74UbH8HQrtGcIVQnz3iRBDw4AHZDr0E";
    private static final String API_URL = "https://content-vision.googleapis.com/v1/images:annotate";

    @Override
    public Sentiments getVisionSentiment(String imageUrl) throws Exception {
        List<Request> requests = new ArrayList<Request>();
        List<Feature> features = new ArrayList<Feature>();

        Image image = new Image(new Source(imageUrl));
        Feature feature = new Feature();
        feature.setType("FACE_DETECTION");
        feature.setMaxResults(1);
        features.add(feature);

        Request request = new Request();
        request.setFeatures(features);
        request.setImage(image);
        requests.add(request);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("key", API_KEY);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AnnotateImageRequest> httpEntity = new HttpEntity<>(new AnnotateImageRequest(requests), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.POST, httpEntity, String.class);

        if (responseEntity.getBody() != null) {
            Sentiments sentiments = new Sentiments();
            JsonNode node = new ObjectMapper().readTree(responseEntity.getBody()).get("responses");
            JsonNode joy = node.at("/0/faceAnnotations/0/joyLikelihood");
            JsonNode sorrow = node.at("/0/faceAnnotations/0/sorrowLikelihood");
            sentiments.setJoyLikelihood(joy.asText());
            sentiments.setSorrowLikelihood(sorrow.asText());
            return sentiments;
        }

        return null;
    }

}
