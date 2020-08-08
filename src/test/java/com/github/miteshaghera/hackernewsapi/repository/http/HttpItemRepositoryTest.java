package com.github.miteshaghera.hackernewsapi.repository.http;

import com.github.miteshaghera.hackernewsapi.model.Item;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

import static com.github.miteshaghera.hackernewsapi.util.Constants.SLASH;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpItemRepositoryTest {

    private static final String NEW_BASE_URL = "NewBaseUrl";
    private static final String NEW_ITEM_URL = "NewItemUrl";
    private static final long ITEM_ID = 1L;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HttpItemRepository httpItemRepository;

    @Test
    @Order(1)
    void getItem() {
        Item input = new Item(ITEM_ID, false, Item.ItemType.COMMENT, "By1", Instant.now(), "abcdef", false, null, null, null, "Url1", 100L, "Comment title", null, 0L);
        Mockito.when(restTemplate.getForObject(getItemUri(ITEM_ID), Item.class)).thenReturn(input);
        Item output = httpItemRepository.getItem(ITEM_ID);
        assertEquals(input, output);
    }

    private String getItemUri(long itemId) {
        return httpItemRepository.getBaseUrl() + SLASH + httpItemRepository.getItemUri() + SLASH + itemId + ".json";
    }

    @Test
    @Order(2)
    void getBaseUrl() {
        assertEquals(AbstractHttpRepository.DEFAULT_BASEURL, httpItemRepository.getBaseUrl());
    }

    @Test
    @Order(3)
    void setBaseUrl() {
        httpItemRepository.setBaseUrl(NEW_BASE_URL);
        assertEquals(NEW_BASE_URL, httpItemRepository.getBaseUrl());
    }

    @Test
    @Order(4)
    void getItemUri() {
        assertEquals(HttpItemRepository.DEFAULT_ITEM_URI, httpItemRepository.getItemUri());
    }

    @Test
    @Order(5)
    void setItemUri() {
        httpItemRepository.setItemUri(NEW_ITEM_URL);
        assertEquals(NEW_ITEM_URL, httpItemRepository.getItemUri());
    }
}
