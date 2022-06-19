package dev.talosdx.blogcms.permission

import mu.KotlinLogging
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable

@Service
class PermissionService(
    val repository: PermissionRepository,
) : PermissionEvaluator {
    private val log = KotlinLogging.logger {}

    @Transactional
    fun registerPermission(permission: Permission): Permission? {
        if (!repository.existsById(permission.id)) {
            return repository.save(permission)
        }
        return null
    }
    @Transactional
    fun registerPermissions(allComponentPermissions: MutableSet<Permission>) {
        allComponentPermissions.forEach { registerPermission(it) }
        repository.flush()
    }
    @Transactional
    fun updatePermission(id: String, nameIn: String? = null, descIn: String? = null): Boolean {
        if (nameIn != null || descIn != null) {
            val permission = repository.getById(id)

            nameIn?.let { permission.name = it }
            descIn?.let { permission.description = it }
            return true
        }
        return false
    }

    override fun hasPermission(
        authentication: Authentication,
        targetDomainObject: Any?,
        permission: Any
    ): Boolean {
        if (authentication.principal == null) return false

        val user = authentication.principal

        if (user is UserDetails) {
            val authorities = user.authorities as Collection<*>
            return authorities.contains(permission) && !authorities.contains("-$permission")
        }
        return false
    }


    override fun hasPermission(
        authentication: Authentication,
        targetId: Serializable?,
        targetType: String,
        permission: Any
    ) = hasPermission(authentication, targetId, permission)
}