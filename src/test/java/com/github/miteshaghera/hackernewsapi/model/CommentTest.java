package com.github.miteshaghera.hackernewsapi.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentTest {

    private static final long COMMENT_ID = 1L;

    private static final String COMMENT_TEXT1 = "Testing comment";

    private static final String USER_HANDLE1 = "abc";

    private static final long USER_AGE1 = 11L;

    private static final String COMMENT_TEXT2 = "Testing comment 2";

    private static final String USER_HANDLE2 = "def";

    private static final long USER_AGE2 = 21L;

    private final Comment comment = new Comment(COMMENT_ID, COMMENT_TEXT1, USER_HANDLE1, USER_AGE1);

    @Test
    @Order(1)
    void getId() {
        assertEquals(COMMENT_ID, comment.getId());
    }

    @Test
    @Order(2)
    void getText() {
        assertEquals(COMMENT_TEXT1, comment.getText());
    }

    @Test
    @Order(3)
    void setText() {
        comment.setText(COMMENT_TEXT2);
        assertEquals(COMMENT_TEXT2, comment.getText());
    }

    @Test
    @Order(4)
    void getUserHandle() {
        assertEquals(USER_HANDLE1, comment.getUserHandle());
    }

    @Test
    @Order(5)
    void setUserHandle() {
        comment.setUserHandle(USER_HANDLE2);
        assertEquals(USER_HANDLE2, comment.getUserHandle());
    }

    @Test
    @Order(6)
    void getUserAge() {
        assertEquals(USER_AGE1, comment.getUserAge());
    }

    @Test
    @Order(7)
    void setUserAge() {
        comment.setUserAge(USER_AGE2);
        assertEquals(USER_AGE2, comment.getUserAge());
    }

    @Test
    @Order(8)
    void testEquals() {
        assertEquals(comment, comment);
        assertNotEquals(null, comment);
        Comment comment1 = new Comment(COMMENT_ID, COMMENT_TEXT2, USER_HANDLE2, USER_AGE2);;
        assertEquals(comment1, comment);
        Comment comment2 = new Comment(2L, COMMENT_TEXT2, USER_HANDLE2, USER_AGE2);;
        assertNotEquals(comment2, comment);
    }

    @Test
    @Order(9)
    void testHashCode() {
        assertEquals(COMMENT_ID ^ (COMMENT_ID >>> 32), comment.hashCode());
    }
}
