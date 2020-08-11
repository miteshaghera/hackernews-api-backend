package com.github.miteshaghera.hackernewsapi.service.impl;

import com.github.miteshaghera.hackernewsapi.exception.NoCommentFoundException;
import com.github.miteshaghera.hackernewsapi.exception.StoryNotFoundException;
import com.github.miteshaghera.hackernewsapi.model.Comment;
import com.github.miteshaghera.hackernewsapi.model.Item;
import com.github.miteshaghera.hackernewsapi.model.User;
import com.github.miteshaghera.hackernewsapi.repository.ItemRepository;
import com.github.miteshaghera.hackernewsapi.repository.UserRepository;
import com.github.miteshaghera.hackernewsapi.service.CommentService;
import com.github.miteshaghera.hackernewsapi.util.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LogManager.getLogger(CommentServiceImpl.class);
    public static final int DEFAULT_MAX_COMMENT_COUNT = 10;

    private final Predicate<Item> storyFilter = item -> Item.ItemType.STORY.equals(item.getType());

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;
    private int maxCommentCount = DEFAULT_MAX_COMMENT_COUNT;

    @Autowired
    public CommentServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(value = "comments", key = "#storyId")
    public List<Comment> searchComments(long storyId) {
        Item story = itemRepository.getItem(storyId);
        if (story == null) {
            throw new StoryNotFoundException();
        } else if (story.getKids() == null) {
            throw new NoCommentFoundException();
        } else {
            Map<Item, Integer> map = new HashMap<>();
            for (Long parentId : story.getKids()) {
                Item item = itemRepository.getItem(parentId);
                map.put(item, countComments(item));
            }

            map = Utility.sortByValue(map);
            List<Item> topCommentItems = map.keySet().stream().limit(maxCommentCount).collect(Collectors.toList());

            return topCommentItems.stream()
                    .parallel()
                    .map(item -> {
                        User user = userRepository.getUser(item.getBy());
                        return new Comment(item.getId(), item.getText(), user.getId(), user.calculateAgeInYears());
                    })
                    .collect(Collectors.toList());
        }
    }

    private Integer countComments(Item item) {
        if (item.getKids() == null || item.getKids().length == 0) {
            return 1;
        } else {
            int count = 0;
            for (Long kid : item.getKids()) {
                count += countComments(itemRepository.getItem(kid));
            }
            return count + 1;
        }
    }

    public int getMaxCommentCount() {
        return maxCommentCount;
    }

    public void setMaxCommentCount(int maxCommentCount) {
        this.maxCommentCount = maxCommentCount;
    }
}
