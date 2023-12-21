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
import java.util.Objects;

@Component
public class YCombinatorNewsParseImpl implements NewsParseTask {

    @Autowired
    NewsService newsService;

    @Scheduled(fixedDelay = 20000)
    public void parseNews() {
        try {
            Document doc = Jsoup.connect(NewsSourceEnum.YOU_COMBINATOR.getUrl())
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();
            Elements news = doc.getElementsByClass("titleline");
            for (Element el: news) {
                String title = Objects.requireNonNull(el.firstElementChild()).ownText();
                String newsLink = Objects.requireNonNull(el.firstElementChild()).attr("href");
                if (!newsService.isExist(title)) {
                    News obj = new News();
                    obj.setTitle(title);
                    obj.setUrl(newsLink);
                    obj.setSource(NewsSourceEnum.YOU_COMBINATOR);
                    newsService.save(obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
