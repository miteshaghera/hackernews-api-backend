package com.github.miteshaghera.hackernewsapi.web.story;

import com.github.miteshaghera.hackernewsapi.model.Story;
import com.github.miteshaghera.hackernewsapi.service.impl.StoryServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StoryControllerTest {

    @InjectMocks
    private StoryController storyController;

    @Mock
    private StoryServiceImpl storyService;

    @Test
    @Order(1)
    void bestStories() {
        List<Story> stories = List.of(new Story(1L), new Story(2L));
        Mockito.when(storyService.bestStories()).thenReturn(stories);
        List<Story> output = storyController.bestStories();
        assertEquals(stories, output);
    }

    @Test
    @Order(2)
    void pastStories() {
        List<Story> stories = List.of(new Story(1L), new Story(2L));
        List<Story> output = storyController.pastStories();
        assertEquals(stories, output);
    }
}