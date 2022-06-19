package dev.talosdx.blogcms.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseAuditEntity {

    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    open lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "modified_date", updatable = false, nullable = false)
    open lateinit var modifiedDate: LocalDateTime
}