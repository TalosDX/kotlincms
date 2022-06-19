package dev.talosdx.blogcms.user

import dev.talosdx.blogcms.registration.UserRegistrationDto
import dev.talosdx.blogcms.registration.toEntity
import dev.talosdx.blogcms.system.SystemSettingsService
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val userGroupRepository: UserGroupRepository,
    private val systemSettings: SystemSettingsService,
) {
    private val log = KotlinLogging.logger {}

    fun existsByUsernameIgnoreCase(username: String) =
        userRepository.existsByUsernameIgnoreCase(username)

    fun createUser(user: UserRegistrationDto) =
        userRepository.save(user.toEntity(getDefaultUserGroup()))

    fun updateUser(user: User) {
        if (user.id != null)
            userRepository.save(user)
    }

    fun blockUser(
        blockingReasonIn: String,
        userId: Long? = null,
        userIn: User? = null,
        blockingUntilDateIn: LocalDateTime? = null
    ) = if (userId != null || userIn?.id != null) {
        val id: Long = (userId ?: userIn?.id) as Long
        userRepository.getById(id).apply {
            isBlocked = true
            blockingDate = LocalDateTime.now()
            blockingUntilDate = blockingUntilDateIn
            blockingReason = blockingReasonIn
        }
        true
    } else false

    fun getDefaultUserGroup() = userGroupRepository.getById(systemSettings.getDefaultUserGroupId())

    fun loadUserByUsername(username: String?): User {
        log.trace { "loadUserByUsername: $username" }
        if (username == null)
            throw UsernameNotFoundException("Field username is empty")

        val user = (userRepository.findByUsernameIgnoreCase(username)
            ?: throw UsernameNotFoundException("User not found"))

        log.trace { "loadUserByUsername: $username, user: $user" }
        return user
    }

    fun createGroup(userRole: UserRole) : UserRole = userGroupRepository.save(userRole)
}