package com.demo.news.agregator.demo.views.logout;

import com.demo.news.agregator.demo.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.PermitAll;

@PageTitle("Logout")
@Route(value = "logout", layout = MainLayout.class)
@Uses(Icon.class)
@PermitAll
public class LogoutView extends Composite<VerticalLayout> {

    public LogoutView(AuthenticationContext authenticationContext) {
        authenticationContext.logout();
    }
}
