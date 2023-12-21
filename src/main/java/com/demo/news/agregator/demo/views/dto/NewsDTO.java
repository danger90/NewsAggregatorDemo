package com.demo.news.agregator.demo.views.dto;

import lombok.Data;

@Data
public class NewsDTO {

    private Long id;
    private String title;
    private String url;

    public NewsDTO() {
    }
}
