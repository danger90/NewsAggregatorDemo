package com.demo.news.agregator.demo.controller;

import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping(value = "/news")
    public List<News> getNews() {
        return newsService.getAllNews();
    }
}
