package dev.talosdx.blogcms.attachment

import dev.talosdx.blogcms.superclass.dto.BaseLongAuditDto
import java.time.LocalDateTime

data class AttachmentDto(
    val downloadUrl : String,
    override val createdDate: LocalDateTime,
    override var modifiedDate: LocalDateTime,
    override var id: Long? = null,
) : BaseLongAuditDto(id, createdDate, modifiedDate) {
}