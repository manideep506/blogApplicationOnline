package com.example.blogApplicationOnline.service;

import com.example.blogApplicationOnline.model.Comment;
import com.example.blogApplicationOnline.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdAndParentCommentIsNull(postId);
    }

    public Comment createComment(Comment comment) {

        return commentRepository.save(comment);
    }
}
