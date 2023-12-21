package com.demo.news.agregator.demo.service;

import com.demo.news.agregator.demo.model.Comment;
import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public void save(Comment comment);
    public List<Comment> getAllComments();
    public List<Comment> getAllCommentsByUser(User user);
    public List<Comment> getAllCommentsByNews(News news);

}
