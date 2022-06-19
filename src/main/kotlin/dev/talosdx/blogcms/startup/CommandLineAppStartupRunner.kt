package dev.talosdx.blogcms.startup

import dev.talosdx.blogcms.component.CMSComponent
import dev.talosdx.blogcms.component.CMSComponentService
import dev.talosdx.blogcms.i18n.Language
import dev.talosdx.blogcms.i18n.LanguageService
import dev.talosdx.blogcms.permission.Permission
import dev.talosdx.blogcms.registration.UserRegistrationDto
import dev.talosdx.blogcms.registration.UserRegistrationService
import dev.talosdx.blogcms.system.SystemSettingsService
import dev.talosdx.blogcms.user.UserRole
import dev.talosdx.blogcms.user.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
@Transactional
class CommandLineAppStartupRunner(
    val userRegistrationService: UserRegistrationService,
    val userService: UserService,
    val componentService: CMSComponentService,
    val languageService: LanguageService,
    val systemSettingsService: SystemSettingsService,
) : CommandLineRunner {


    @Throws(Exception::class)
    override fun run(vararg args: String) {
        val defaultUserRole = userService.createGroup(UserRole("users", "группа пользователей по умолчанию"))

        systemSettingsService.putNewSetting("system.usergroup.defaultGroupId", defaultUserRole.id!!.toString())

        componentService.registerComponent(CMSComponent("system.user")
            .apply {
                permissions.addAll(
                    setOf(
                        Permission("user.auth"),
                        Permission("user.create"),
                        Permission("user.update"),
                        Permission("user.delete"),
                        Permission("user.ban"),
                    )
                )
            }
        )

        Locale.getISOLanguages()
            .map { Language(it) }
            .forEach { languageService.addLanguage(it) }




        if (!userService.existsByUsernameIgnoreCase("admin")) {
            userRegistrationService.registerUser(
                UserRegistrationDto(
                    "admin",
                    "admin",
                    "admin@localhost"
                ), true
            )
        }
    }

}