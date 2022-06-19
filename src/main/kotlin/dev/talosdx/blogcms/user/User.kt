package dev.talosdx.blogcms.user

import dev.talosdx.blogcms.entity.BaseLongAuditEntity
import dev.talosdx.blogcms.permission.Permission
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.*

@Entity
//устал править проблемы с конфликтами ключевых слов
@Table(name = "client")
class User(
    @Column(name = "email", unique = true, nullable = false)
    var email: String,

    @Column(name = "username", unique = true, nullable = false)
    var username: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Lazy
    @ManyToOne(fetch = FetchType.LAZY)
    var userRole: UserRole,

    @Column(name = "registration_date", nullable = false)
    var registrationDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "is_activated", nullable = false)
    var isActivated: Boolean = false,

    @Column(name = "activation_code", nullable = true)
    var activationCode: String? = null,

    @Column(name = "is_blocked", nullable = false)
    var isBlocked: Boolean = false,

    @Column(name = "blocking_Date", nullable = true)
    var blockingDate: LocalDateTime? = null,

    @Column(name = "blocking_until_Date", nullable = true)
    var blockingUntilDate: LocalDateTime? = null,

    var blockingReason: String? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_to_permissions",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    val permissions: MutableSet<Permission> = mutableSetOf(),

    id: Long? = null,

) : BaseLongAuditEntity(id) {


    fun getAllPermissions() = setOf(userRole.permissions, permissions)
        .flatten()

    fun toUserDetails(): UserDetails = object : UserDetails {
        override fun getAuthorities() = getAllPermissions()
            .map { it.toGrandAuthority() }

        override fun getPassword() = this@User.password

        override fun getUsername() = this@User.username

        override fun isAccountNonExpired() = true

        override fun isAccountNonLocked() = !this@User.isBlocked

        override fun isCredentialsNonExpired() = true

        override fun isEnabled() = this@User.isActivated
    }
}