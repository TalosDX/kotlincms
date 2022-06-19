package dev.talosdx.blogcms.i18n

import dev.talosdx.blogcms.entity.BaseLongAuditEntity
import javax.persistence.*

@Entity
@Table(name = "i18n")
class I18n(
    id: Long? = null,
    @Column(name = "key", unique = false, nullable = false)
    var key: String,
    @ManyToOne(fetch = FetchType.EAGER)
    var language: Language,
    @Column(name = "translation", unique = false, nullable = false)
    var translation: String,
) : BaseLongAuditEntity(id) {
}



