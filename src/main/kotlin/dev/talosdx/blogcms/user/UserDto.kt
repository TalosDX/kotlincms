package dev.talosdx.blogcms.user

import dev.talosdx.blogcms.superclass.dto.BaseLongAuditDto
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.time.LocalDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class UserDto(
    @field:NotBlank
    @field:Min(4)
    @field:Max(20)
    val username: String,
    @field:NotBlank
    @field:Min(4)
    @field:Max(63)
    val password: String,
    @field:Email
    @field:NotBlank
    val email: String,
    val registrationDate: LocalDateTime,
    val isActivated: Boolean,
    val activationCode: String? = null,
    override var id: Long? = null,
    override val createdDate: LocalDateTime,
    override var modifiedDate: LocalDateTime,
) : BaseLongAuditDto(id, createdDate, modifiedDate)