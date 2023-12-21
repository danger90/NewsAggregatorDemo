package com.demo.news.agregator.demo.service.Impl;

import java.util.List;
import java.util.Optional;

import com.demo.news.agregator.demo.model.source.NewsSourceEnum;
import com.demo.news.agregator.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository repository;

    @Override
    public void save(News news) {
        repository.save(news);
    }

    @Override
    public boolean isExist(String newsTitle) {
        List<News> allNews = repository.findAll();
        for (News n: allNews) {
            if (n.getTitle().equals(newsTitle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<News> getAllNews() {
        return repository.findAll();
    }

    public Optional<News> getNewsById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<News> getAllNewsBySource(NewsSourceEnum source) {
        return repository.findBySource(source);
    }

}
