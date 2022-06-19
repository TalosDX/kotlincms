package dev.talosdx.blogcms.converter

import javax.persistence.AttributeConverter
import javax.persistence.Converter

/**
 * Конвертер плохо протестирован, может работать не очень
 */
@Converter
class StringListConverter : AttributeConverter<List<String>, String> {
    companion object {
        private const val SPLIT_CHAR = ";"
    }

    override fun convertToDatabaseColumn(attribute: List<String>?) =
        attribute?.joinToString { SPLIT_CHAR } ?: ""


    override fun convertToEntityAttribute(dbData: String?) =
        dbData?.split(SPLIT_CHAR)?.map { it.trim() } ?: emptyList()
}
