package dev.talosdx.blogcms.ui.view

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.auth.AnonymousAllowed
import dev.talosdx.blogcms.ui.forms.MainForm
import javax.annotation.security.PermitAll

@Route("", layout = MainForm::class)
@PageTitle("Home Page")
@AnonymousAllowed
class MainView : VerticalLayout() {
}