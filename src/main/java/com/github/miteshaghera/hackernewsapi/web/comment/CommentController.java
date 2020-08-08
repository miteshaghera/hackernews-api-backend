package com.github.miteshaghera.hackernewsapi.web.comment;

import com.github.miteshaghera.hackernewsapi.model.Comment;
import com.github.miteshaghera.hackernewsapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/comments")
    public List<Comment> searchComments(@RequestParam("storyId") long storyId) {
        return commentService.searchComments(storyId);
    }
}
