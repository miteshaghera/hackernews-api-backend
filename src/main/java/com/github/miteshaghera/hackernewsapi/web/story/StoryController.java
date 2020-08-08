package com.github.miteshaghera.hackernewsapi.web.story;

import com.github.miteshaghera.hackernewsapi.model.Item;
import com.github.miteshaghera.hackernewsapi.model.Story;
import com.github.miteshaghera.hackernewsapi.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoryController {
    private final StoryService storyService;

    private List<Story> pastStories;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @RequestMapping("/best-stories")
    public List<Story> bestStories() {
        return storyService.bestStories();
    }

    @RequestMapping("/past-stories")
    public List<Item> pastStories() {
        return List.of();
    }
}
