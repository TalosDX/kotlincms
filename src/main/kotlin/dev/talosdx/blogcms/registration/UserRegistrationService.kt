package dev.talosdx.blogcms.registration

import dev.talosdx.blogcms.exception.AlreadyExistsException
import dev.talosdx.blogcms.mail.MailDto
import dev.talosdx.blogcms.mail.MailService
import dev.talosdx.blogcms.user.User
import dev.talosdx.blogcms.user.UserService
import mu.KotlinLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserRegistrationService(
    private val mailService: MailService,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
) {
    private val log = KotlinLogging.logger {}

    fun registerUser(userData: UserRegistrationDto, internalRegistration: Boolean = false) {
        if (userService.existsByUsernameIgnoreCase(userData.username)) {
            throw AlreadyExistsException("User with name ${userData.username} already registered")
        }

        userData.password = passwordEncoder.encode(userData.password)
        val user = userService.createUser(userData)

        if (internalRegistration) {
            user.isActivated = true
            user.activationCode = null
        } else {
            generateActivationCode(user)
            sendActivationCodeToEmail(user)
        }
    }

    fun generateActivationCode(user: User) {
        user.activationCode = UUID.randomUUID().toString()
    }

    fun sendActivationCodeToEmail(user: User) {
        mailService.sendMail(
            MailDto(
                mutableListOf(user.email),
                "noreply@talosdx.dev",
                "Registration on talosdx.dev",
                "Here your activation code: ${user.activationCode} \n" +
                        "Or y may click this link: <NO IMPLEMENTED>",
                mutableListOf(user)
            )
        )
    }
}