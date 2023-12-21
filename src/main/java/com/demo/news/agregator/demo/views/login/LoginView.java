package com.demo.news.agregator.demo.views.login;

import com.demo.news.agregator.demo.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
@Uses(Icon.class)
@AnonymousAllowed
public class LoginView extends  VerticalLayout {

    public LoginView() {
        LoginForm login = new LoginForm();
        login.setAction("login");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(login);
    }
}
