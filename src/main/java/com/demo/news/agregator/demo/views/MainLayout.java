package com.demo.news.agregator.demo.views;


import com.demo.news.agregator.demo.model.source.RolesEnum;
import com.demo.news.agregator.demo.views.about.AboutView;
import com.demo.news.agregator.demo.views.logout.LogoutView;
import com.demo.news.agregator.demo.views.login.LoginView;
import com.demo.news.agregator.demo.views.register.RegisterView;
import com.demo.news.agregator.demo.views.ukr_net.UkrNetView;
import com.demo.news.agregator.demo.views.unian.UnianView;
import com.demo.news.agregator.demo.views.y_combinator.YCombinatorView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {
    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, Component icon, Class<? extends Component> view) {


            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames(Display.FLEX, Gap.XSMALL, Height.MEDIUM, AlignItems.CENTER, Padding.Horizontal.SMALL,
                    TextColor.BODY);
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames(FontWeight.MEDIUM, FontSize.MEDIUM, Whitespace.NOWRAP);

            if (icon != null) {
                link.add(icon);
            }
            link.add(text);

            add(link);
        }

        public Class<?> getView() {
            return view;
        }

    }

    public MainLayout() {

        addToNavbar(createHeaderContent());
        setDrawerOpened(false);
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames(BoxSizing.BORDER, Display.FLEX, FlexDirection.COLUMN, Width.FULL);

        Div layout = new Div();
        layout.addClassNames(Display.FLEX, AlignItems.CENTER, Padding.Horizontal.LARGE);

        H1 appName = new H1("News aggregator");
        appName.addClassNames(Margin.Vertical.MEDIUM, Margin.End.AUTO, FontSize.LARGE);
        layout.add(appName);

        Nav nav = new Nav();
        nav.addClassNames(Display.FLEX, Overflow.AUTO, Padding.Horizontal.MEDIUM, Padding.Vertical.XSMALL);

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames(Display.FLEX, Gap.SMALL, ListStyleType.NONE, Margin.NONE, Padding.NONE);
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);
        }

        header.add(layout, nav);
        return header;
    }

    private MenuItemInfo[] createMenuItems() {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().toString();

        if (!role.equals(RolesEnum.ANONYMOUS.getRole())) {
            return new MenuItemInfo[]{ //
                    new MenuItemInfo("You Combinator", LineAwesomeIcon.NEWSPAPER.create(), YCombinatorView.class),
                    new MenuItemInfo("Ukr Net", LineAwesomeIcon.NEWSPAPER.create(), UkrNetView.class),
                    new MenuItemInfo("Unian", LineAwesomeIcon.NEWSPAPER.create(), UnianView.class),
                    new MenuItemInfo("About", LineAwesomeIcon.INFO_SOLID.create(), AboutView.class),
                    new MenuItemInfo("Logout", LineAwesomeIcon.PENCIL_RULER_SOLID.create(), LogoutView.class),
            };
        }

        return new MenuItemInfo[]{
                new MenuItemInfo("You Combinator", LineAwesomeIcon.NEWSPAPER.create(), YCombinatorView.class),
                new MenuItemInfo("Ukr Net", LineAwesomeIcon.NEWSPAPER.create(), UkrNetView.class),
                new MenuItemInfo("Unian", LineAwesomeIcon.NEWSPAPER.create(), UnianView.class),
                new MenuItemInfo("About", LineAwesomeIcon.INFO_SOLID.create(), AboutView.class),
                new MenuItemInfo("Registration", LineAwesomeIcon.USER_SOLID.create(), RegisterView.class),
                new MenuItemInfo("Login", LineAwesomeIcon.USER_SOLID.create(), LoginView.class),
        };
    }

}
