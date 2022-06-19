package dev.talosdx.blogcms.i18n

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class I18nService(
    val repository: I18nRepository,
    val languageService: LanguageService,
) {
    private val log = KotlinLogging.logger {}


    fun getTranslation(languageId: String, key: String) =
        repository.findOnlyTranslation(languageId, key)

    fun getEntityTranslation(languageId: String, key: String) =
        repository.findByKeyAndLanguageId(languageId, key)

    fun addTranslation(i18n: I18n) =
        if (i18n.id == null && !repository.existsByKeyAndLanguageId(i18n.language.id, i18n.key))
            repository.save(i18n)
        else
            null

    fun updateTranslation(languageId: String, key: String, translation: String) =
        repository.findByKeyAndLanguageId(languageId, key)?.let {
            it.translation = translation
            repository.save(it)
        }

    fun updateTranslation(i18n: I18n) =
        repository.save(i18n)

}