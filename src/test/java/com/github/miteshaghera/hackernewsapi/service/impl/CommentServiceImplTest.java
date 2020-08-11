package com.github.miteshaghera.hackernewsapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miteshaghera.hackernewsapi.exception.NoCommentFoundException;
import com.github.miteshaghera.hackernewsapi.exception.StoryNotFoundException;
import com.github.miteshaghera.hackernewsapi.model.Comment;
import com.github.miteshaghera.hackernewsapi.model.Item;
import com.github.miteshaghera.hackernewsapi.model.Story;
import com.github.miteshaghera.hackernewsapi.model.User;
import com.github.miteshaghera.hackernewsapi.repository.http.HttpItemRepository;
import com.github.miteshaghera.hackernewsapi.repository.http.HttpUserRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentServiceImplTest {

    private static final int MAX_COMMENT_COUNT = 3;

    @Mock
    private HttpUserRepository userRepository;

    @Mock
    private HttpItemRepository itemRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @Order(1)
    void getMaxCommentCount() {
        assertEquals(CommentServiceImpl.DEFAULT_MAX_COMMENT_COUNT, commentService.getMaxCommentCount());
    }

    @Test
    @Order(2)
    void setMaxCommentCount() {
        commentService.setMaxCommentCount(MAX_COMMENT_COUNT);
        assertEquals(MAX_COMMENT_COUNT, commentService.getMaxCommentCount());
    }

    @Test
    @Order(3)
    void searchComments() {
        try {
            final Item[] items = objectMapper.readValue(new ClassPathResource("items.json").getInputStream(), Item[].class);
            final User[] users = objectMapper.readValue(new ClassPathResource("users.json").getInputStream(), User[].class);
            long storyId = 121003L;
            Mockito.when(itemRepository.getItem(ArgumentMatchers.anyLong())).thenAnswer(invocation ->
                    Arrays.stream(items).filter(item -> invocation.getArgument(0, Long.class) == item.getId())
                            .findFirst().orElse(null));
            Mockito.when(userRepository.getUser(ArgumentMatchers.anyString())).thenAnswer(invocation ->
                    Arrays.stream(users).filter(user -> invocation.getArgument(0, String.class).equals(user.getId()))
                            .findFirst().orElse(null));
            List<Comment> output = commentService.searchComments(storyId);
            assertTrue(output.stream().allMatch(comment ->
                    Arrays.stream(items).anyMatch(item -> item.getId() == comment.getId())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void searchCommentsWithError() {
        long storyId = 121L;
        Mockito.when(itemRepository.getItem(storyId)).thenReturn(null);
        assertThrows(StoryNotFoundException.class, () -> commentService.searchComments(storyId));
    }

    @Test
    @Order(5)
    void searchCommentsWithError2() {
        long storyId = 1211L;
        Mockito.when(itemRepository.getItem(storyId)).thenReturn(new Item(1211L));
        assertThrows(NoCommentFoundException.class, () -> commentService.searchComments(storyId));
    }
}
