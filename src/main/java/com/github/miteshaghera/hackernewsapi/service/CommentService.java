package com.github.miteshaghera.hackernewsapi.service;

import com.github.miteshaghera.hackernewsapi.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> searchComments(long storyId);
}
