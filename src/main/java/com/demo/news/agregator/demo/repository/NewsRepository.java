package com.demo.news.agregator.demo.repository;

import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.model.source.NewsSourceEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findBySource(NewsSourceEnum source);
}
