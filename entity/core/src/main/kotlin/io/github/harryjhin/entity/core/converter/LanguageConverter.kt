package io.github.harryjhin.entity.core.converter

import io.github.harryjhin.common.i18n.Language
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
object LanguageConverter : AttributeConverter<Language, String> {

    /**
     * @sample io.github.harryjhin.entity.core.converter.LanguageConverterTest.convertToDatabaseColumn
     */
    override fun convertToDatabaseColumn(attribute: Language?): String? {
        return attribute?.toString()
    }

    /**
     * @sample io.github.harryjhin.entity.core.converter.LanguageConverterTest.convertToEntityAttribute
     */
    override fun convertToEntityAttribute(dbData: String?): Language? {
        return dbData?.let { Language.entries.firstOrNull { language -> language.toString() == it } }
    }
}