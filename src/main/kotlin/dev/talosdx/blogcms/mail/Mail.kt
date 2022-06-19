package dev.talosdx.blogcms.mail

import dev.talosdx.blogcms.converter.StringListConverter
import dev.talosdx.blogcms.entity.BaseLongAuditEntity
import dev.talosdx.blogcms.user.User
import org.springframework.context.annotation.Lazy
import javax.persistence.*

@Entity
@Table(name = "mail")
class Mail(
    @Column(name = "to_emails", nullable = false)
    @Convert(converter = StringListConverter::class)
    val toEmails: MutableList<String>,
    @Column(name = "fromEmail", nullable = false)
    val fromEmail: String,
    @Column(name = "header", nullable = false)
    val header: String,
    @Column(name = "body", nullable = false)
    val body: String,
    @Lazy
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "mail_id")
    val toUsers: MutableList<User>,
    @Lazy
    @OneToOne(fetch = FetchType.LAZY)
    val fromUser: User?,
    @Column(name = "is_sent", nullable = false)
    var isSent: Boolean,
    id: Long? = null,
) : BaseLongAuditEntity(id)



