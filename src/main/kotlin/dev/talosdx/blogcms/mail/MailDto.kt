package dev.talosdx.blogcms.mail

import dev.talosdx.blogcms.entity.BaseLongAuditEntity
import dev.talosdx.blogcms.superclass.dto.BaseLongAuditDto
import dev.talosdx.blogcms.user.User
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.time.LocalDateTime

data class MailDto(
    val toEmails: MutableList<String>,
    val fromEmail: String,
    val header: String,
    val body: String,
    val toUsers: MutableList<User>,
    val fromUser: User?,
    var isSent: Boolean,
    override var id: Long? = null,
    override val createdDate: LocalDateTime = LocalDateTime.now(),
    override var modifiedDate: LocalDateTime = LocalDateTime.now(),
) : BaseLongAuditDto(id, createdDate, modifiedDate) {
    constructor(
        toEmails: MutableList<String>,
        fromEmail: String,
        header: String,
        body: String,
        toUsers: MutableList<User>
    ) : this(toEmails, fromEmail, header, body, toUsers, null, false)
}