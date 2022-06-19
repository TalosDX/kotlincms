package dev.talosdx.blogcms.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.util.ProxyUtils
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseLongAuditEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
) : BaseAuditEntity() {
    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseLongAuditEntity

        return this.id != null && this.id == other.id
    }

    override fun hashCode() = javaClass.hashCode()

    override fun toString(): String {
        return "${this.javaClass.simpleName}(id=$id)"
    }
}