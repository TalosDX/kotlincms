package dev.talosdx.blogcms.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun existsByUsernameIgnoreCase(username : String) : Boolean

    fun findByUsernameIgnoreCase(username: String) : User?
}