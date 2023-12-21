package com.demo.news.agregator.demo.views.register;

import com.demo.news.agregator.demo.model.User;
import com.demo.news.agregator.demo.model.source.RolesEnum;
import com.demo.news.agregator.demo.service.Impl.UserServiceImpl;
import com.demo.news.agregator.demo.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;

@PageTitle("Register")
@Route(value = "register", layout = MainLayout.class)
@Uses(Icon.class)
@AnonymousAllowed
public class RegisterView extends Composite<VerticalLayout> {

    @Autowired
    private UserServiceImpl userService;

    private boolean isAcceptedWithTerms = false;

    public RegisterView() {
        H3 h3 = new H3();
        TextField textField = new TextField();
        PasswordField textField2 = new PasswordField();
        Checkbox checkbox = new Checkbox();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();


        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        h3.setText("Registration");
        h3.setWidth("max-content");
        textField.setLabel("Username");
        textField.setWidth("300px");
        textField2.setLabel("Password");
        textField2.setWidth("300px");

        checkbox.setLabel("Aceppted with terms");
        checkbox.setWidth("200px");
        buttonPrimary.setText("Register");
        buttonPrimary.setWidth("300px");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancel");
        buttonSecondary.setWidth("300px");
        getContent().add(h3);
        getContent().add(textField);
        getContent().add(textField2);
        getContent().add(checkbox);
        getContent().add(buttonPrimary);
        getContent().add(buttonSecondary);


        buttonPrimary.isEnabled();

        checkbox.addClickListener(e -> isAcceptedWithTerms = !isAcceptedWithTerms );

        buttonPrimary.addClickListener(e -> {
                    String username = textField.getValue();
                    String password = textField2.getValue();

                    if (!isAcceptedWithTerms) {
                        Notification.show("Please agree with terms");
                        return;
                    }

                    try {
                        if (userService.isExist(username)) {
                            Notification.show("Name - \"" + username + "\" already exist");
                            return;
                        }

                        if (username.isEmpty()) {
                            Notification.show("Username is empty");
                            return;
                        }

                        if (password.isEmpty()) {
                            Notification.show("Password is empty");
                            return;
                        }

                        addUser(username, password);
                        Notification.show("User - \"" + textField.getValue() + "\" created");
                    } catch (AuthenticationException er) {
                        Notification.show("Registration failed. Please check your credentials.");
                    }
                }
        );
    }

    private void addUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRoles(RolesEnum.USER.getRole());

        userService.addUser(user);
    }
}
