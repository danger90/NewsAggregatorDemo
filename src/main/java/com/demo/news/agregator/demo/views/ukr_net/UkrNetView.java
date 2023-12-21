package com.demo.news.agregator.demo.views.ukr_net;

import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.model.source.NewsSourceEnum;
import com.demo.news.agregator.demo.service.Impl.CommentServiceImpl;
import com.demo.news.agregator.demo.service.NewsService;
import com.demo.news.agregator.demo.views.MainLayout;
import com.demo.news.agregator.demo.views.comment.CommentView;
import com.demo.news.agregator.demo.views.dto.NewsDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@PageTitle("UkrNet")
@Route(value = "ukr-net", layout = MainLayout.class)
@AnonymousAllowed
public class UkrNetView extends Div implements AfterNavigationObserver {
    @Autowired
    NewsService newsService;
    @Autowired
    CommentServiceImpl commentService;
    Grid<NewsDTO> grid = new Grid<>();

    private final int itemsPerPage = 10;
    private int currentPage = 1;

    public UkrNetView() {
        addClassName("feed-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);

        add(grid, createButtons());
        grid.addComponentColumn(this::createNews);
    }

    private HorizontalLayout createButtons() {
        Button nextPageButton = new Button("Next Page", e -> nextPage());
        Button previousPageButton = new Button("Previous Page", e -> previousPage());

        HorizontalLayout buttonsBlock = new HorizontalLayout();
        buttonsBlock.add(nextPageButton);
        buttonsBlock.add(previousPageButton);

        return buttonsBlock;
    }

    private void refreshGrid() {
        List<NewsDTO> newsList = fetchData();
        grid.setItems(newsList);
    }

    private List<NewsDTO> fetchData() {
        int offset = (currentPage - 1) * itemsPerPage;
        return newsService.getAllNewsBySource(NewsSourceEnum.UKR_NET_MAIN)
                .stream()
                .skip(offset)
                .limit(itemsPerPage)
                .map(n -> createNewsDTO(n.getId(), n.getTitle(), n.getUrl()))
                .toList();
    }

    private void nextPage() {
        int totalItems = newsService.getAllNewsBySource(NewsSourceEnum.UKR_NET_MAIN).size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            refreshGrid();
        }
    }

    private void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            refreshGrid();
        }
    }

    private HorizontalLayout createNews(NewsDTO news) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Anchor feed = new Anchor();
        feed.addClassName("title");
        feed.setHref(news.getUrl());
        feed.setText(news.getTitle());
        header.add(feed);

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        Icon commentIcon = VaadinIcon.COMMENT.create();
        commentIcon.addClassName("icon");

        Optional<News> newsEntity = newsService.getNewsById(news.getId());

        int commentsSize = 0;
        if (newsEntity.isPresent()) {
            commentsSize = commentService.getAllCommentsByNews(newsEntity.get()).size();
        }

        Span commentsCount = new Span(String.valueOf(commentsSize));
        commentsCount.addClassName("comments");

        RouterLink routerLink = new RouterLink("Comments", CommentView.class);

        Map<String, List<String>> paramMap = new HashMap<>();
        paramMap.put("news_id", List.of(news.getId().toString()));
        QueryParameters queryParameters = new QueryParameters(paramMap);

        routerLink.setQueryParameters(queryParameters);

        Anchor comments = new Anchor();
        comments.addClassName("comments");
        comments.setHref(routerLink.getHref());
        comments.add(commentIcon);
        comments.add(commentsCount);

        actions.add(comments);

        description.add(header, actions);

        card.add(description);

        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        refreshGrid();
    }

    private NewsDTO createNewsDTO(Long id, String title, String url) {
        NewsDTO n = new NewsDTO();
        n.setId(id);
        n.setTitle(title);
        n.setUrl(url);

        return n;
    }
}
