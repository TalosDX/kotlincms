package dev.talosdx.blogcms.superclass.dto

import java.time.LocalDateTime

abstract class BaseAuditDto<T>(
    override var id: T?,
    open val createdDate: LocalDateTime,
    open var modifiedDate: LocalDateTime
) : BaseDto<T>(id)