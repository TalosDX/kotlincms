package dev.talosdx.blogcms.permission

import dev.talosdx.blogcms.system.SystemSettings
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PermissionRepository : JpaRepository<Permission, String> {
}