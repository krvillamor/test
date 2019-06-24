package com.test.project.vision.service;

import com.test.project.vision.pojo.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public interface VisionService {

     Sentiments getVisionSentiment(String imageUrl) throws Exception;
}
