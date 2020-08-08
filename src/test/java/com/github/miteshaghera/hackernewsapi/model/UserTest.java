package com.github.miteshaghera.hackernewsapi.model;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTest {

    private static final String USER_ID = "abc";
    private static final Integer DELAY_1 = 0;
    private static final Instant CREATED_1 = Instant.ofEpochMilli(100L);
    private static final Integer KARMA_1 = 1;
    private static final String ABOUT_1 = "About1";
    private static final Long[] SUBMITTED_1 = {2L, 4L};
    private static final Integer DELAY_2 = 2;
    private static final Instant CREATED_2 = Instant.ofEpochMilli(200L);
    private static final Integer KARMA_2 = 4;
    private static final String ABOUT_2 = "About2";
    private static final Long[] SUBMITTED_2 = {8L, 16L};

    private final User user = new User(USER_ID, DELAY_1, CREATED_1, KARMA_1, ABOUT_1, SUBMITTED_1);

    @Test
    @Order(1)
    void getId() {
        assertEquals(USER_ID, user.getId());
    }

    @Test
    @Order(2)
    void getDelay() {
        assertEquals(DELAY_1, user.getDelay());
    }

    @Test
    @Order(3)
    void setDelay() {
        user.setDelay(DELAY_2);
        assertEquals(DELAY_2, user.getDelay());
    }

    @Test
    @Order(4)
    void getCreated() {
        assertEquals(CREATED_1, user.getCreated());
    }

    @Test
    @Order(5)
    void setCreated() {
        user.setCreated(CREATED_2);
        assertEquals(CREATED_2, user.getCreated());
    }

    @Test
    @Order(6)
    void getKarma() {
        assertEquals(KARMA_1, user.getKarma());
    }

    @Test
    @Order(7)
    void setKarma() {
        user.setKarma(KARMA_2);
        assertEquals(KARMA_2, user.getKarma());
    }

    @Test
    @Order(8)
    void getAbout() {
        assertEquals(ABOUT_1, user.getAbout());
    }

    @Test
    @Order(9)
    void setAbout() {
        user.setAbout(ABOUT_2);
        assertEquals(ABOUT_2, user.getAbout());
    }

    @Test
    @Order(10)
    void getSubmitted() {
        assertArrayEquals(SUBMITTED_1, user.getSubmitted());
    }

    @Test
    @Order(11)
    void setSubmitted() {
        user.setSubmitted(SUBMITTED_2);
        assertArrayEquals(SUBMITTED_2, user.getSubmitted());
    }

    @Test
    @Order(12)
    void testEquals() {
        assertEquals(user, user);
        assertNotEquals(null, user);
        User user1 = new User(USER_ID);
        assertEquals(user1, user);
        User user2 = new User("def");
        assertNotEquals(user2, user);
    }

    @Test
    @Order(13)
    void testHashCode() {
    assertEquals(USER_ID.hashCode(), user.hashCode());
    }

    @Test
    @Order(14)
    void calculateAgeInYears() {
        Instant created = Instant.now();
        user.setCreated(created);
        assertEquals(0, user.calculateAgeInYears());
        created = created.minus(Duration.ofDays(365).getSeconds(), ChronoUnit.SECONDS);
        user.setCreated(created);
        assertEquals(1, user.calculateAgeInYears());
    }
}
