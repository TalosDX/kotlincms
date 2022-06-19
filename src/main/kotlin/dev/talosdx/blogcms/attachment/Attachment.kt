package dev.talosdx.blogcms.attachment

import dev.talosdx.blogcms.entity.BaseLongAuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "attachment")
class Attachment(
    @Column(name = "download_url", nullable = false)
    val downloadUrl : String,
    id: Long? = null,
) : BaseLongAuditEntity(id) {
}