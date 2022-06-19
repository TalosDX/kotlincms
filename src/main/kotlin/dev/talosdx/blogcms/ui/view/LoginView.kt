package dev.talosdx.blogcms.ui.view

import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.login.LoginForm
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode.CENTER
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import org.springframework.security.access.prepost.PreAuthorize

@Route("login")
@PreAuthorize("hasPermission(#)")
@PageTitle("Login")
class LoginView : VerticalLayout(), BeforeEnterObserver {
    private val loginForm = LoginForm()

    init {
        addClassName("login-view")
        setSizeFull()

        justifyContentMode = CENTER
        alignItems = Alignment.CENTER

        loginForm.action = "login"

        add(H1("Test Application"), loginForm)
    }

    override fun beforeEnter(event: BeforeEnterEvent) {
        if (event.location.queryParameters.parameters.containsKey("error")) {
            loginForm.isError = true
        }
    }
}