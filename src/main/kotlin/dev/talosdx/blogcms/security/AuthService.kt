package dev.talosdx.blogcms.security

import com.vaadin.flow.server.VaadinServletRequest
import dev.talosdx.blogcms.user.UserService
import mu.KotlinLogging
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


private const val LOGOUT_SUCCESS_URL = "/"

@Service
@Transactional
class AuthService(
    private val userService: UserService,
) : UserDetailsService {
    private val log = KotlinLogging.logger {}


    override fun loadUserByUsername(username: String?) = userService
        .loadUserByUsername(username)
        .toUserDetails()

    fun isAuthenticated(): Boolean {
        val context = SecurityContextHolder.getContext()
        val principal = context?.authentication?.principal ?: return false

        return principal is UserDetails
    }

    fun logout(): String {
        SecurityContextLogoutHandler()
            .logout(
                VaadinServletRequest.getCurrent().httpServletRequest,
                null,
                null
            )
        return LOGOUT_SUCCESS_URL
    }
}