package com.github.miteshaghera.hackernewsapi.model;

import org.junit.jupiter.api.*;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemTest {

    private static final long ITEM_ID = 1L;
    private static final String BY_1 = "By1";
    private static final String BY_2 = "By2";
    private static final long DESCENDANTS = 2L;
    private static final Instant TIME_1 = Instant.ofEpochMilli(100L);
    private static final Instant TIME_2 = Instant.ofEpochMilli(200L);
    private static final String TEXT_1 = "Text1";
    private static final Long PARENT_1 = 10L;
    private static final Long POLL_1 = 20L;
    private static final Long[] KIDS_1 = {40L, 80L};
    private static final String URL_1 = "URL1";
    private static final Long SCORE_1 = 160L;
    private static final String TITLE_1 = "Title1";
    private static final Long[] PARTS_1 = {320L, 640L};
    private static final String TEXT_2 = "Text2";
    private static final Long PARENT_2 = 1280L;
    private static final Long POLL_2 = 2560L;
    private static final Long[] KIDS_2 = {5120L, 10240L};
    private static final String URL_2 = "URL2";
    private static final Long SCORE_2 = 20480L;
    private static final String TITLE_2 = "Title2";
    private static final Long[] PARTS_2 = {41920L};
    private static final Long DESCENDANTS_2 = 100L;
    private final Item item = new Item(ITEM_ID, true, Item.ItemType.COMMENT, BY_1, TIME_1, TEXT_1, false,
            PARENT_1, POLL_1, KIDS_1, URL_1, SCORE_1, TITLE_1, PARTS_1, DESCENDANTS);

    @Test
    @Order(1)
    void getId() {
        assertEquals(ITEM_ID, item.getId());
    }

    @Test
    @Order(2)
    void isDeleted() {
        assertTrue(item.isDeleted());
    }

    @Test
    @Order(3)
    void setDeleted() {
        item.setDeleted(false);
        assertFalse(item.isDeleted());
    }

    @Test
    @Order(4)
    void getType() {
        assertEquals(Item.ItemType.COMMENT, item.getType());
    }

    @Test
    @Order(5)
    void setType() {
        item.setType(Item.ItemType.STORY);
        assertEquals(Item.ItemType.STORY, item.getType());
    }

    @Test
    @Order(6)
    void getBy() {
        assertEquals(BY_1, item.getBy());
    }

    @Test
    @Order(7)
    void setBy() {
        item.setBy(BY_2);
        assertEquals(BY_2, item.getBy());
    }

    @Test
    @Order(8)
    void getTime() {
        assertEquals(TIME_1, item.getTime());
    }

    @Test
    @Order(9)
    void setTime() {
        item.setTime(TIME_2);
        assertEquals(TIME_2, item.getTime());
    }

    @Test
    @Order(10)
    void getText() {
        assertEquals(TEXT_1, item.getText());
    }

    @Test
    @Order(11)
    void setText() {
        item.setText(TEXT_2);
        assertEquals(TEXT_2, item.getText());
    }

    @Test
    @Order(12)
    void isDead() {
        assertFalse(item.isDead());
    }

    @Test
    @Order(13)
    void setDead() {
        item.setDead(true);
        assertTrue(item.isDead());
    }

    @Test
    @Order(14)
    void getParent() {
        assertEquals(PARENT_1, item.getParent());
    }

    @Test
    @Order(15)
    void setParent() {
        item.setParent(PARENT_2);
        assertEquals(PARENT_2, item.getParent());
    }

    @Test
    @Order(16)
    void getPoll() {
        assertEquals(POLL_1, item.getPoll());
    }

    @Test
    @Order(17)
    void setPoll() {
        item.setPoll(POLL_2);
        assertEquals(POLL_2, item.getPoll());
    }

    @Test
    @Order(18)
    void getKids() {
        assertArrayEquals(KIDS_1, item.getKids());
    }

    @Test
    @Order(19)
    void setKids() {
        item.setKids(KIDS_2);
        assertArrayEquals(KIDS_2, item.getKids());
    }

    @Test
    @Order(20)
    void getUrl() {
        assertEquals(URL_1, item.getUrl());
    }

    @Test
    @Order(21)
    void setUrl() {
        item.setUrl(URL_2);
        assertEquals(URL_2, item.getUrl());
    }

    @Test
    @Order(22)
    void getScore() {
        assertEquals(SCORE_1, item.getScore());
    }

    @Test
    @Order(23)
    void setScore() {
        item.setScore(SCORE_2);
        assertEquals(SCORE_2, item.getScore());
    }

    @Test
    @Order(24)
    void getTitle() {
        assertEquals(TITLE_1, item.getTitle());
    }

    @Test
    @Order(25)
    void setTitle() {
        item.setTitle(TITLE_2);
        assertEquals(TITLE_2, item.getTitle());
    }

    @Test
    @Order(26)
    void getParts() {
        assertArrayEquals(PARTS_1, item.getParts());
    }

    @Test
    @Order(27)
    void setParts() {
        item.setParts(PARTS_2);
        assertArrayEquals(PARTS_2, item.getParts());
    }

    @Test
    @Order(28)
    void getDescendants() {
        assertEquals(DESCENDANTS, item.getDescendants());
    }

    @Test
    @Order(29)
    void setDescendants() {
        item.setDescendants(DESCENDANTS_2);
        assertEquals(DESCENDANTS_2, item.getDescendants());
    }

    @Test
    @Order(30)
    void testEquals() {
        assertEquals(item, item);
        assertNotEquals(null, item);
        Item item1 = new Item(ITEM_ID);
        assertEquals(item1, item);
        Item item2 = new Item(2L);
        assertNotEquals(item2, item);
    }

    @Test
    @Order(31)
    void testHashCode() {
        assertEquals(ITEM_ID ^ (ITEM_ID >>> 32), item.hashCode());
    }

    @Test
    @Order(32)
    void itemTypeParse() {
        Optional<Item.ItemType> story = Item.ItemType.parse("story");
        assertTrue(story.isPresent());
        assertEquals(Item.ItemType.STORY, story.get());
    }

    @Test
    @Order(33)
    void itemTypeGetType() {
        assertEquals("poll", Item.ItemType.POLL.getType());
    }
}
