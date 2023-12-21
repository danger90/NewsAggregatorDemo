package com.demo.news.agregator.demo.views.comment;

import com.demo.news.agregator.demo.config.UserDetails;
import com.demo.news.agregator.demo.model.Comment;
import com.demo.news.agregator.demo.model.News;
import com.demo.news.agregator.demo.model.User;
import com.demo.news.agregator.demo.model.source.NewsSourceEnum;
import com.demo.news.agregator.demo.model.source.RolesEnum;
import com.demo.news.agregator.demo.service.Impl.CommentServiceImpl;
import com.demo.news.agregator.demo.service.Impl.NewsServiceImpl;
import com.demo.news.agregator.demo.service.Impl.UserServiceImpl;
import com.demo.news.agregator.demo.views.MainLayout;
import com.demo.news.agregator.demo.views.dto.CommentDTO;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@PageTitle("Comment")
@Route(value = "comment_view", layout = MainLayout.class)
@AnonymousAllowed
public class CommentView extends HorizontalLayout implements HasUrlParameter<String>, AfterNavigationObserver {

    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private NewsServiceImpl newsService;
    private TextArea textAreaComment;
    private Button commentButton;
    Grid<CommentDTO> grid = new Grid<>();
    private final int itemsPerPage = 10;
    private int currentPage = 1;
    private Long newsId;

    public CommentView() {
        addClassName("feed-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);

        add(grid);
        grid.addComponentColumn(this::createComment);
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().toString();

        if (!role.equals(RolesEnum.ANONYMOUS.getRole())) {
            textAreaComment = new TextArea("Comment");
            commentButton = new Button("Send");

            commentButton.addClickListener(e -> {
                buttonAction(textAreaComment.getValue());
                Notification.show("Comment saved " + textAreaComment.getValue());
            });
            commentButton.addClickShortcut(Key.ENTER);

            setMargin(true);
            setVerticalComponentAlignment(Alignment.END, textAreaComment, commentButton);

            add(textAreaComment, commentButton);
        }

        add(createPaginationButtons());

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {

        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();

        Map<String, List<String>> parametersMap = queryParameters
                .getParameters();

        if (!parametersMap.get("news_id").isEmpty()) {
            String newsIdParam = parametersMap.get("news_id").iterator().next();
            newsId = Long.parseLong(newsIdParam);
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        refreshGrid();
    }

    private void buttonAction(String text) {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof UserDetails)) {
            return;
        }

        String userName = ((UserDetails) principal).getUsername();

        Optional<User> user = userService.getUserByName(userName);
        Optional<News> news = newsService.getNewsById(newsId);

        if (user.isPresent() && news.isPresent()) {
            addComment(text, user.get(), news.get());
        }
    }

    private void addComment(String text, User user, News news) {

        Comment comment = new Comment();
        comment.setText(text);
        comment.setNews(news);
        comment.setUser(user);
        comment.setVisible(true);

        commentService.save(comment);
    }



    private HorizontalLayout createPaginationButtons() {
        Button nextPageButton = new Button("Next Page", e -> nextPage());
        Button previousPageButton = new Button("Previous Page", e -> previousPage());

        HorizontalLayout buttonsBlock = new HorizontalLayout();
        buttonsBlock.add(nextPageButton);
        buttonsBlock.add(previousPageButton);

        return buttonsBlock;
    }

    private void refreshGrid() {
        List<CommentDTO> commentList = fetchData();
        grid.setItems(commentList);
    }

    private List<CommentDTO> fetchData() {
        int offset = (currentPage - 1) * itemsPerPage;
        Optional<News> news = newsService.getNewsById(newsId);

        return news.map(value -> commentService.getAllCommentsByNews(value)
                .stream()
                .skip(offset)
                .limit(itemsPerPage)
                .map(c -> createCommentDTO(c.getId(), c.getText(), c.getUser().getName()))
                .toList()).orElse(null);
    }

    private void nextPage() {
        int totalItems = newsService.getAllNewsBySource(NewsSourceEnum.YOU_COMBINATOR).size();
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

    private CommentDTO createCommentDTO(Long id, String text, String userName) {
        CommentDTO c = new CommentDTO();

        c.setId(id);
        c.setText(text);
        c.setUserName(userName);

        return c;
    }

    private HorizontalLayout createComment(CommentDTO comment) {
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

        Span userNameTitleSpan = new Span();
        userNameTitleSpan.setTitle("user-name-title");
        userNameTitleSpan.setText("User: ");

        header.add(userNameTitleSpan);

        Span userNameSpan = new Span();
        userNameSpan.setTitle("user-name");
        userNameSpan.setText(comment.getUserName());

        header.add(userNameSpan);

        HorizontalLayout commentBody = new HorizontalLayout();
        commentBody.addClassName("comment");
        commentBody.setSpacing(false);
        commentBody.getThemeList().add("spacing-s");

        Span textTitleSpan = new Span();
        textTitleSpan.setTitle("comment-title");
        textTitleSpan.setText("Comment: ");
        commentBody.add(textTitleSpan);

        Span textSpan = new Span();
        textSpan.setTitle("comment");
        textSpan.setText(comment.getText());
        commentBody.add(textSpan);

        description.add(header, commentBody);

        card.add(description);

        return card;
    }
}
