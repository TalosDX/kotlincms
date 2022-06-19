package dev.talosdx.blogcms.superclass.dto

import java.time.LocalDateTime

abstract class BaseLongAuditDto(
    id: Long?,
    createdDate: LocalDateTime,
    modifiedDate: LocalDateTime,
) : BaseAuditDto<Long>(id, createdDate, modifiedDate)