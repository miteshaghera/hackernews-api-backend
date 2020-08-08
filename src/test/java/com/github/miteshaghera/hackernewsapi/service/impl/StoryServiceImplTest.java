package com.github.miteshaghera.hackernewsapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miteshaghera.hackernewsapi.model.Item;
import com.github.miteshaghera.hackernewsapi.model.Story;
import com.github.miteshaghera.hackernewsapi.repository.ItemRepository;
import com.github.miteshaghera.hackernewsapi.repository.StoryRepository;
import com.github.miteshaghera.hackernewsapi.repository.http.HttpItemRepository;
import com.github.miteshaghera.hackernewsapi.repository.http.HttpStoryRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StoryServiceImplTest {

    private static final int MAX_STORIES_COUNT = 3;

    @Mock
    private HttpStoryRepository storyRepository;

    @Mock
    private HttpItemRepository itemRepository;

    @InjectMocks
    private StoryServiceImpl storyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void getMaxStoriesCount() {
        assertEquals(StoryServiceImpl.MAX_STORIES_COUNT, storyService.getMaxStoriesCount());
    }

    @Test
    @Order(2)
    void setMaxStoriesCount() {
        storyService.setMaxStoriesCount(MAX_STORIES_COUNT);
        assertEquals(MAX_STORIES_COUNT, storyService.getMaxStoriesCount());
    }

    @Test
    @Order(3)
    void bestStories() {
        try {
            final Long[] topStories = objectMapper.readValue(new ClassPathResource("topstories.json").getInputStream(), Long[].class);
            final Item[] items = objectMapper.readValue(new ClassPathResource("stories.json").getInputStream(), Item[].class);
            Mockito.when(storyRepository.getTopStories()).thenAnswer(invocation -> topStories);
            Mockito.when(itemRepository.getItem(ArgumentMatchers.anyLong()))
                    .thenAnswer(invocationOnMock -> Arrays.stream(items)
                            .filter(item -> item.getId() == invocationOnMock.getArgument(0, Long.class))
                            .findFirst().orElse(null));

            List<Story> output = storyService.bestStories();
            assertEquals(MAX_STORIES_COUNT, output.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
