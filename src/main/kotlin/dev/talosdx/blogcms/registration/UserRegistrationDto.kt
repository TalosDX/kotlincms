package dev.talosdx.blogcms.registration

import dev.talosdx.blogcms.user.User
import dev.talosdx.blogcms.user.UserRole
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class UserRegistrationDto(
    @field:NotBlank
    @field:Min(4)
    @field:Max(20)
    val username: String,
    @field:NotBlank
    @field:Min(4)
    @field:Max(63)
    var password: String,
    @field:Email
    @field:NotBlank
    val email: String,
)

fun UserRegistrationDto.toEntity(userRole: UserRole) = User(
    email,
    username,
    password,
    userRole,
)