package com.demo.news.agregator.demo.service.Impl;

import com.demo.news.agregator.demo.model.Comment;
import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.model.User;
import com.demo.news.agregator.demo.repository.CommentRepository;
import com.demo.news.agregator.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository repository;

    @Override
    public void save(Comment comment) {
        repository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return repository.findAll();
    }

    public List<Comment> getAllCommentsByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public List<Comment> getAllCommentsByNews(News news) {
        return repository.findByNews(news);
    }
}
