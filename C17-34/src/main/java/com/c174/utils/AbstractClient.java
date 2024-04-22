package com.c174.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


public abstract class  AbstractClient {

    @Value("https://enterprice-api.onrender.com")
    protected String baseURl;

    protected final RestTemplate restTemplate;

    protected AbstractClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected HttpHeaders buildAuthToken(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "aplication/json");
        headers.set("Autorization", "Bearer" + token);
        return headers;
    }
}
