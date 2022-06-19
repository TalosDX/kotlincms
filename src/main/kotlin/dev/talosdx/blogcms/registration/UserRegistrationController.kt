package dev.talosdx.blogcms.registration

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api/v1/user-registration"])
class UserRegistrationController(
    val service: UserRegistrationService,
) {

    @PostMapping
    fun registerUser(@RequestBody userData: UserRegistrationDto) {
        service.registerUser(userData)
    }
}