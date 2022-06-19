package dev.talosdx.blogcms.user

fun User.toDto() = UserDto(
    username,
    password,
    email,
    registrationDate,
    isActivated,
    activationCode,
    id,
    registrationDate,
    modifiedDate
)

fun UserDto.toEntity() : User = User(
    username,
    password,
    email,
    userRole.toDto()
)