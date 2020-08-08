package com.github.miteshaghera.hackernewsapi.service.impl;

import com.github.miteshaghera.hackernewsapi.repository.ItemRepository;
import com.github.miteshaghera.hackernewsapi.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentServiceImplTest {

    private static final int MAX_COMMENT_COUNT = 3;
    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private CommentServiceImpl commentService;


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
    }
}
