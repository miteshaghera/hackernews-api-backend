package com.github.miteshaghera.hackernewsapi.repository.http;

import com.github.miteshaghera.hackernewsapi.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static com.github.miteshaghera.hackernewsapi.util.Constants.SLASH;

@Repository
public class HttpStoryRepository extends AbstractHttpRepository implements StoryRepository {

    public static final String DEFAULT_TOP_STORIES_URI = "/v0/topstories";

    private String topStoriesUri = DEFAULT_TOP_STORIES_URI;

    @Autowired
    public HttpStoryRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Long[] getTopStories() {
        return restTemplate.getForObject(topStoriesUri(), Long[].class);
    }

    private String topStoriesUri() {
        return baseUrl + SLASH + topStoriesUri + ".json";
    }


    public String getTopStoriesUri() {
        return topStoriesUri;
    }

    public void setTopStoriesUri(String topStoriesUri) {
        this.topStoriesUri = topStoriesUri;
    }
}
