package io.github.harryjhin.common.i18n

import java.util.Locale

/**
 * @see io.github.harryjhin.entity.core.converter.LanguageConverter
 */
enum class Language(
    val locale: Locale
) {
    KO(locale = Locale.KOREAN),
    EN(locale = Locale.ENGLISH)
    ;

    override fun toString(): String {
        return locale.toLanguageTag()
    }
}