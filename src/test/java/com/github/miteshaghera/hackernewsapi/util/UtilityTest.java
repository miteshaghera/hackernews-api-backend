package com.github.miteshaghera.hackernewsapi.util;

import com.github.miteshaghera.hackernewsapi.model.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilityTest {

    @Test
    void sortByValue() {
        Map<Item, Integer> unsortedMap = IntStream.range(0, 10)
                .mapToObj(Item::new)
                .collect(Collectors.toMap(Function.identity(),
                        item -> Integer.valueOf(String.valueOf(item.getId()))));
        Map<Item, Integer> sorted = Utility.sortByValue(unsortedMap);
        List<Map.Entry<Item,Integer>> entries = new ArrayList<>(sorted.entrySet());
        assertTrue(entries.get(0).getValue() > entries.get(9).getValue());
        assertFalse(entries.get(5).getValue() < entries.get(6).getValue());
    }
}
