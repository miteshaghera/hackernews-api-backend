package com.github.miteshaghera.hackernewsapi.repository.http;

import com.github.miteshaghera.hackernewsapi.model.User;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

import static com.github.miteshaghera.hackernewsapi.util.Constants.SLASH;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpUserRepositoryTest {

    private static final String USER_ID = "abc";
    private static final String USER_URI = "UserUri";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HttpUserRepository httpUserRepository;

    @Test
    @Order(1)
    void getUser() {
        User input = new User(USER_ID, 10, Instant.now(), 1, "I'm abc", new Long[]{1L, 2L});
        Mockito.when(restTemplate.getForObject(getUserUri(USER_ID), User.class)).thenReturn(input);
        User output = httpUserRepository.getUser(USER_ID);
        assertEquals(input, output);
    }

    private String getUserUri(String user) {
        return httpUserRepository.getBaseUrl() + SLASH + httpUserRepository.getUserUri() + SLASH + user + ".json";
    }

    @Test
    @Order(2)
    void getUserUri() {
        assertEquals(HttpUserRepository.DEFAULT_USER_URI, httpUserRepository.getUserUri());
    }

    @Test
    @Order(3)
    void setUserUri() {
        httpUserRepository.setUserUri(USER_URI);
        assertEquals(USER_URI, httpUserRepository.getUserUri());
    }
}
