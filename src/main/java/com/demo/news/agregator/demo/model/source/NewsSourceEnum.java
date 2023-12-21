package com.demo.news.agregator.demo.model.source;

import lombok.Getter;

@Getter
public enum NewsSourceEnum {
    YOU_COMBINATOR("https://news.ycombinator.com/"),
    UKR_NET_MAIN("https://www.ukr.net/news/main.html"),
    UNIAN_MAIN("https://www.unian.ua/detail/main_news");

    private final String url;

    NewsSourceEnum(String url) {
        this.url = url;
    }
}