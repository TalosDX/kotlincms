package dev.talosdx.blogcms.user

import dev.talosdx.blogcms.entity.BaseLongAuditEntity
import dev.talosdx.blogcms.permission.Permission
import org.springframework.context.annotation.Lazy
import javax.persistence.*

@Entity
@Table(name = "user_role")
class UserRole(
    @Column(name = "name", unique = false, nullable = false)
    val name: String,
    @Column(name = "description", unique = false, nullable = false)
    val description: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role_to_permissions",
        joinColumns = [JoinColumn(name = "user_role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    var permissions: MutableSet<Permission> = mutableSetOf(),
    @Lazy
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userRole")
    var users: MutableSet<User> = mutableSetOf(),
    id: Long? = null,
) : BaseLongAuditEntity(id) {
}