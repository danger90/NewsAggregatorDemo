package com.demo.news.agregator.demo.job.impl;


import com.demo.news.agregator.demo.job.NewsParseTask;
import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.model.source.NewsSourceEnum;
import com.demo.news.agregator.demo.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UnianNewsParseImpl implements NewsParseTask {

    @Autowired
    NewsService newsService;

    @Scheduled(fixedDelay = 10000)
    public void parseNews() {
        try {
            Document doc = Jsoup.connect(NewsSourceEnum.UNIAN_MAIN.getUrl())
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();
            Elements news = doc.getElementsByClass("list-thumbs__title");
            for (Element el: news) {
                String title = el.ownText();
                String newsLink = el.attr("href");
                if (!newsService.isExist(title)) {
                    News obj = new News();
                    obj.setTitle(title);
                    obj.setUrl(newsLink);
                    obj.setSource(NewsSourceEnum.UNIAN_MAIN);
                    newsService.save(obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
