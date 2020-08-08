package com.github.miteshaghera.hackernewsapi.repository.http;

import com.github.miteshaghera.hackernewsapi.model.User;
import com.github.miteshaghera.hackernewsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static com.github.miteshaghera.hackernewsapi.util.Constants.SLASH;

@Repository
public class HttpUserRepository extends AbstractHttpRepository implements UserRepository {

    public static final String DEFAULT_USER_URI = "/v0/user";

    private String userUri = DEFAULT_USER_URI;

    @Autowired
    public HttpUserRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    private String getUserUri(String user) {
        return baseUrl + SLASH + userUri + SLASH + user + ".json";
    }

    public String getUserUri() {
        return userUri;
    }

    public void setUserUri(String userUri) {
        this.userUri = userUri;
    }

    @Override
    public User getUser(String id) {
        return restTemplate.getForObject(getUserUri(id), User.class);
    }
}
