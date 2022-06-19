package dev.talosdx.blogcms.content

import dev.talosdx.blogcms.attachment.Attachment
import dev.talosdx.blogcms.superclass.dto.BaseLongAuditDto
import dev.talosdx.blogcms.user.UserDto
import java.time.LocalDateTime

class ContentDto(
    val name : String,
    val shortDescription: String,
    val text: String,
    val author: UserDto,
    val contentType: ContentType = ContentType.MATERIAL,
    val attachment: Attachment? = null,
    override val createdDate: LocalDateTime,
    override var modifiedDate: LocalDateTime,
    id : Long? = null,
) : BaseLongAuditDto(id, createdDate, modifiedDate) {
}