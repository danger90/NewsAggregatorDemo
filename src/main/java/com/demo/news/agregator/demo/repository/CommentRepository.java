package com.demo.news.agregator.demo.repository;

import com.demo.news.agregator.demo.model.Comment;
import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUser(User user);
    List<Comment> findByNews(News news);
}
