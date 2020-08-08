package com.github.miteshaghera.hackernewsapi.repository.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractHttpRepository {
    public static final String DEFAULT_BASEURL = "https://hacker-news.firebaseio.com";

    protected final RestTemplate restTemplate;

    protected String baseUrl = DEFAULT_BASEURL;

    protected AbstractHttpRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public final String getBaseUrl() {
        return baseUrl;
    }

    public final void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
