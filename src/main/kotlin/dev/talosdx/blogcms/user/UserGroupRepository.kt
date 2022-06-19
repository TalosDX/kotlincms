package dev.talosdx.blogcms.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserGroupRepository : JpaRepository<UserRole, Long> {
}
