package com.github.miteshaghera.hackernewsapi.service;

import com.github.miteshaghera.hackernewsapi.model.Story;

import java.util.List;

public interface StoryService {
    int MAX_STORIES_COUNT = 10;

    List<Story> bestStories();
}
