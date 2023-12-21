package com.demo.news.agregator.demo.model;

import com.demo.news.agregator.demo.model.source.NewsSourceEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Enumerated(EnumType.ORDINAL)
    private NewsSourceEnum source;
    private String url;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "news")
    private List<Comment> comments;
}