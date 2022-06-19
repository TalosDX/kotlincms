package dev.talosdx.blogcms.i18n

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface I18nRepository : JpaRepository<I18n, String> {

    @Query("SELECT i.translation from I18n i WHERE i.language=?1 AND i.key=?2")
    fun findOnlyTranslation(language: String, key: String): String?

    fun findByKeyAndLanguageId(language: String, key: String): I18n?

    fun existsByKeyAndLanguageId(language: String, key: String): Boolean
}