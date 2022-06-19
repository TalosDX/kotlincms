package dev.talosdx.blogcms.system

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SystemSettingsService(
    val repository: SystemSettingsRepository,
) {
    private val log = KotlinLogging.logger {}


    fun getSettingById(id: String) = repository.findValueById(id)

    fun getSettingsByIds(ids: Iterable<String>) = repository.findValuesByIds(ids)

    fun save(settings: SystemSettings) = repository.save(settings)


    fun putNewSetting(id: String, value: String) {
        if (!repository.existsById(id)) {
            repository.save(SystemSettings(id, value))
        }
    }

    fun getDefaultUserGroupId(): Long = repository.getById("system.usergroup.defaultGroupId")
        .value
        .toLong()
}