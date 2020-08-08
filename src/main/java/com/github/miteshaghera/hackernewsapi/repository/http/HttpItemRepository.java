package com.github.miteshaghera.hackernewsapi.repository.http;

import com.github.miteshaghera.hackernewsapi.model.Item;
import com.github.miteshaghera.hackernewsapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static com.github.miteshaghera.hackernewsapi.util.Constants.SLASH;

@Repository
public class HttpItemRepository extends AbstractHttpRepository implements ItemRepository {

    public static final String DEFAULT_ITEM_URI = "/v0/item";

    private String itemUri = DEFAULT_ITEM_URI;

    @Autowired
    public HttpItemRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Item getItem(Long itemId) {
        return restTemplate.getForObject(getItemUri(itemId), Item.class);
    }

    private String getItemUri(long itemId) {
        return baseUrl + SLASH + itemUri + SLASH + itemId + ".json";
    }


    public String getItemUri() {
        return itemUri;
    }

    public void setItemUri(String itemUri) {
        this.itemUri = itemUri;
    }
}
