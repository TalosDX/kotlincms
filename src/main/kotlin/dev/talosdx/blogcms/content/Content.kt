package dev.talosdx.blogcms.content

import dev.talosdx.blogcms.entity.BaseLongAuditEntity
import dev.talosdx.blogcms.attachment.Attachment
import dev.talosdx.blogcms.user.User
import javax.persistence.*

@Entity
@Table(name = "content")
class Content(
    var name : String,
    var shortDescription: String,
    var text: String,
    @OneToOne
    val author: User,
    @Enumerated(EnumType.STRING)
    var contentType: ContentType = ContentType.MATERIAL,
    @OneToOne
    var attachment: Attachment? = null,
    id : Long? = null,
) : BaseLongAuditEntity(id) {
}


