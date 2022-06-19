package dev.talosdx.blogcms.mail

fun MailDto.toEntity() = Mail(
    toEmails,
    fromEmail,
    header,
    body,
    toUsers,
    fromUser,
    isSent
)
fun Mail.toDto() = MailDto(
    toEmails,
    fromEmail,
    header,
    body,
    toUsers,
    fromUser,
    isSent
)
