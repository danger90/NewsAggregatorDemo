package com.demo.news.agregator.demo.config;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@PWA(name = "Demo Application", shortName = "Demo App", offlineResources = {"images/logo.png"})
@Theme(value = "my-demo", variant = Lumo.DARK)
public class AppShellConfig implements AppShellConfigurator {
}
