package com.github.miteshaghera.hackernewsapi.service.impl;

import com.github.miteshaghera.hackernewsapi.model.Item;
import com.github.miteshaghera.hackernewsapi.model.Story;
import com.github.miteshaghera.hackernewsapi.repository.ItemRepository;
import com.github.miteshaghera.hackernewsapi.repository.StoryRepository;
import com.github.miteshaghera.hackernewsapi.service.StoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {

    private static final Logger LOGGER = LogManager.getLogger(StoryServiceImpl.class);
    private final ItemRepository itemRepository;
    private final StoryRepository storyRepository;
    private int maxStoriesCount = MAX_STORIES_COUNT;

    @Autowired
    public StoryServiceImpl(ItemRepository itemRepository, StoryRepository storyRepository) {
        this.itemRepository = itemRepository;
        this.storyRepository = storyRepository;
    }

    @Override
    @Cacheable(cacheNames = "stories")
    public List<Story> bestStories() {
        return Arrays.stream(storyRepository.getTopStories())
                .parallel()
                .map(itemRepository::getItem)
                .sorted(Comparator.comparingLong(Item::getScore))
                .limit(maxStoriesCount)
                .map(Story::of)
                .collect(Collectors.toList());
    }

    public int getMaxStoriesCount() {
        return maxStoriesCount;
    }

    public void setMaxStoriesCount(int maxStoriesCount) {
        this.maxStoriesCount = maxStoriesCount;
    }
}
