package dev.talosdx.blogcms.i18n

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LanguageRepository : JpaRepository<Language, String> {


    fun findDefaultLanguage(): Language {
        return findByIsDefaultIsTrueAndIsEnabledIsTrue()
    }

    @Suppress("SpringDataMethodInconsistencyInspection")
    fun findByIsDefaultIsTrueAndIsEnabledIsTrue() : Language
}