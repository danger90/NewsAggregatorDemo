package com.demo.news.agregator.demo.service;

import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.model.source.NewsSourceEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NewsService {
    public void save(News news);
    public boolean isExist(String newsTitle);
    public List<News> getAllNews();
    public Optional<News> getNewsById(Long id);
    public List<News> getAllNewsBySource(NewsSourceEnum $source);
}
