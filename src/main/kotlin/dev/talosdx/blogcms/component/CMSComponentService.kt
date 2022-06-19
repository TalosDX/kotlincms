package dev.talosdx.blogcms.component

import dev.talosdx.blogcms.permission.PermissionService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CMSComponentService(
    val repository: CMSComponentRepository,
    val permissionService: PermissionService,
) {
    private val log = KotlinLogging.logger {}

    fun registerComponent(component: CMSComponent): CMSComponent? =
        if (!repository.existsById(component.id)) {
            permissionService.registerPermissions(component.permissions)
            val savedComponent = repository.save(component)
            savedComponent
        } else
            null

}