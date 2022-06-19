package dev.talosdx.blogcms.ui.forms

import com.vaadin.flow.component.UI
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.router.PageTitle
import dev.talosdx.blogcms.security.AuthController
import dev.talosdx.blogcms.ui.view.LoginView

@PageTitle("Main page")
class MainForm(
    authController: AuthController,
) : AppLayout() {

    init {
        val logo = H1("Kotlin Blog CMS")
            .apply {
                addClassName("logo")

            }

        val header = HorizontalLayout()
            .apply {
                add(logo)

                if (authController.isAuthenticated()) {
                    val logout = Button("Logout") {
                        val logoutUrl = authController.logout()
                        UI.getCurrent()
                            ?.page
                            ?.setLocation(logoutUrl)
                    }
                    add(logout)
                } else {
                    val login = Button("Login") {
                        UI.getCurrent().addModal(LoginView())
                    }
                    add(login)
                }
            }
        addToNavbar(header)
    }
}