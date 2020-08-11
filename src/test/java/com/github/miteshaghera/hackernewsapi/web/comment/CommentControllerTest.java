package com.github.miteshaghera.hackernewsapi.web.comment;

import com.github.miteshaghera.hackernewsapi.model.Comment;
import com.github.miteshaghera.hackernewsapi.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentControllerTest {

    @Mock
    private CommentServiceImpl commentService;

    @InjectMocks
    private CommentController commentController;

    @Test
    void searchComments() {
        List<Comment> comments = List.of(new Comment(2L), new Comment(3L));
        Mockito.when(commentService.searchComments(1L)).thenReturn(comments);
        List<Comment> output = commentController.searchComments(1L);
        assertEquals(comments, output);
    }
}