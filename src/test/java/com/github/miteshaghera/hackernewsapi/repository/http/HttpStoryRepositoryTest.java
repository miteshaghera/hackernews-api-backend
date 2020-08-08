package com.github.miteshaghera.hackernewsapi.repository.http;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static com.github.miteshaghera.hackernewsapi.util.Constants.SLASH;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpStoryRepositoryTest {

    private static final Long[] TOP_STORIES = {1L, 2L, 3L};
    private static final String NEW_TOP_STORIES_URI = "NewTopStoryUri";
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HttpStoryRepository httpStoryRepository;

    @Test
    @Order(1)
    void getTopStories() {
        Mockito.when(restTemplate.getForObject(topStoriesUri(), Long[].class)).thenReturn(TOP_STORIES);
        assertArrayEquals(TOP_STORIES, httpStoryRepository.getTopStories());
    }

    private String topStoriesUri() {
        return httpStoryRepository.getBaseUrl() + SLASH + httpStoryRepository.getTopStoriesUri() + ".json";
    }

    @Test
    @Order(2)
    void getTopStoriesUri() {
        assertEquals(HttpStoryRepository.DEFAULT_TOP_STORIES_URI, httpStoryRepository.getTopStoriesUri());
    }

    @Test
    @Order(3)
    void setTopStoriesUri() {
        httpStoryRepository.setTopStoriesUri(NEW_TOP_STORIES_URI);
        assertEquals(NEW_TOP_STORIES_URI, httpStoryRepository.getTopStoriesUri());
    }
}
