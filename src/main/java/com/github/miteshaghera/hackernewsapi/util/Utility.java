package com.github.miteshaghera.hackernewsapi.util;

import com.github.miteshaghera.hackernewsapi.model.Item;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Utility {

    private Utility() {
    }

    public static Map<Item, Integer> sortByValue(Map<Item, Integer> unsortedMap) {
        List<Map.Entry<Item, Integer>> entries = new LinkedList<>(unsortedMap.entrySet());

        entries.sort(Map.Entry.<Item, Integer>comparingByValue().reversed());

        return entries.stream()
                .collect(LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll);
    }
}
