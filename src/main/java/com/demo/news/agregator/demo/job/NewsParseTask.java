package com.demo.news.agregator.demo.job;

import org.springframework.stereotype.Component;

@Component
public interface NewsParseTask {

    public void parseNews();
}
