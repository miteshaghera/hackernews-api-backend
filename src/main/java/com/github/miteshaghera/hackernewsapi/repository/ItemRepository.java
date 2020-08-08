package com.github.miteshaghera.hackernewsapi.repository;

import com.github.miteshaghera.hackernewsapi.model.Item;

public interface ItemRepository {

    Item getItem(Long itemId);
}
