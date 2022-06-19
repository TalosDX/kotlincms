package dev.talosdx.blogcms.i18n

import mu.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LanguageService(
    private val repository: LanguageRepository
) {
    private val log = KotlinLogging.logger {}


    fun getLanguage(languageId: String) =
        repository.findByIdOrNull(languageId)

    fun addLanguage(language: Language) =
        if (!repository.existsById(language.id))
            repository.save(language)
        else
            null

    fun updateLanguage(language: Language) =
        repository.save(language)

    fun findDefaultLanguage() : Language {
        return repository.findDefaultLanguage()
    }
}
