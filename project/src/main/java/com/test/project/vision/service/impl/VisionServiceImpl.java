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
    public Sentiments getSentiment(String path) throws Exception {
        List<Request> requests = new ArrayList<>();
        List<Feature> features = new ArrayList<>();

        Image image = new Image(new Source(path));
        Feature feature = new Feature(FeatureTypes.FACE_DETECTION, 1);
        features.add(feature);

        Request request = new Request(image, features);
        requests.add(request);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL + "?key=" + API_KEY);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AnnotateImageRequest> httpEntity = new HttpEntity<>(new AnnotateImageRequest(requests), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, httpEntity, String.class);

        if (!Objects.requireNonNull(responseEntity.getBody()).isEmpty()) {
            JsonNode node = new ObjectMapper().readTree(responseEntity.getBody()).get("responses");
            JsonNode joy = node.at("/0/faceAnnotations/0/joyLikelihood");
            JsonNode sorrow = node.at("/0/faceAnnotations/0/sorrowLikelihood");

            return new Sentiments(joy.asText(), sorrow.asText());
        }

        return null;
    }

}
