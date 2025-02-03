package io.github.harryjhin.entity.core.converter

import io.github.harryjhin.common.i18n.Language
import kotlin.test.Test
import kotlin.test.assertEquals

class LanguageConverterTest {

    @Test
    fun convertToDatabaseColumn() {
        // Given
        val language = Language.KO

        // When
        val result: String? = LanguageConverter.convertToDatabaseColumn(language)
            .also(::println)

        // Then
        assertEquals(
            expected = "ko",
            actual = result
        )
    }

    @Test
    fun convertToEntityAttribute() {
        // Given
        val daData = "ko"

        // When
        val result: Language? = LanguageConverter.convertToEntityAttribute(daData)
            .also(::println)

        // Then
        assertEquals(
            expected = Language.KO,
            actual = result
        )
    }
}