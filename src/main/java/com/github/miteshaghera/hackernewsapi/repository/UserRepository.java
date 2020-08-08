package com.github.miteshaghera.hackernewsapi.repository;

import com.github.miteshaghera.hackernewsapi.model.User;

public interface UserRepository {
    User getUser(String id);
}
