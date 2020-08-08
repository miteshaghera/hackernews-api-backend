package com.github.miteshaghera.hackernewsapi.model;

import com.github.miteshaghera.hackernewsapi.util.Constants;
import org.junit.jupiter.api.*;

import java.time.Instant;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StoryTest {

    private static final Instant INSTANT_1 = Instant.ofEpochMilli(100L);
    private static final ZonedDateTime TIME_1 = ZonedDateTime.ofInstant(INSTANT_1, Constants.UTC);
    private static final String TITLE_1 = "Title1";
    private static final String BY_1 = "By1";
    private static final long SCORE_1 = 10L;
    private static final String URL_1 = "URL1";
    private static final long STORY_ID = 1L;
    private static final String TITLE_2 = "Title2";
    private static final String URL_2 = "URL2";
    private static final Long SCORE_2 = 20L;
    private static final String BY_2 = "By2";
    private Story story;

    @Test
    @Order(1)
    void of() {
        Item item = new Item(STORY_ID);
        item.setUrl(URL_1);
        item.setScore(SCORE_1);
        item.setBy(BY_1);
        item.setTitle(TITLE_1);
        item.setTime(INSTANT_1);
        story = Story.of(item);
        assertEquals(URL_1, story.getUrl());
        assertEquals(SCORE_1, story.getScore());
        assertEquals(BY_1, story.getBy());
        assertEquals(TITLE_1, story.getTitle());
        assertEquals(TIME_1, story.getTime());
    }

    @Test
    @Order(2)
    void getId() {
        assertEquals(STORY_ID, story.getId());
    }

    @Test
    @Order(3)
    void getTitle() {
        assertEquals(TITLE_1, story.getTitle());
    }

    @Test
    @Order(4)
    void setTitle() {
        story.setTitle(TITLE_2);
        assertEquals(TITLE_2, story.getTitle());
    }

    @Test
    @Order(5)
    void getUrl() {
        assertEquals(URL_1, story.getUrl());
    }

    @Test
    @Order(6)
    void setUrl() {
        story.setUrl(URL_2);
        assertEquals(URL_2, story.getUrl());
    }

    @Test
    @Order(7)
    void getScore() {
        assertEquals(SCORE_1, story.getScore());
    }

    @Test
    @Order(8)
    void setScore() {
        story.setScore(SCORE_2);
        assertEquals(SCORE_2, story.getScore());
    }

    @Test
    @Order(9)
    void getTime() {
        assertEquals(TIME_1, story.getTime());
    }

    @Test
    @Order(10)
    void setTime() {
        ZonedDateTime now = ZonedDateTime.now();
        story.setTime(now);
        assertEquals(now, story.getTime());
    }

    @Test
    @Order(11)
    void getBy() {
        assertEquals(BY_1, story.getBy());
    }

    @Test
    @Order(12)
    void setBy() {
        story.setBy(BY_2);
        assertEquals(BY_2, story.getBy());
    }

    @Test
    @Order(13)
    void testEquals() {
        assertEquals(story, story);
        assertNotEquals(null, story);
        Story story1 = new Story(STORY_ID);
        assertEquals(story1, story);
        Story story2 = new Story(2L);
        assertNotEquals(story2, story);
    }

    @Test
    @Order(14)
    void testHashCode() {
        assertEquals(STORY_ID ^ (STORY_ID >>> 32), story.hashCode());
    }
}
