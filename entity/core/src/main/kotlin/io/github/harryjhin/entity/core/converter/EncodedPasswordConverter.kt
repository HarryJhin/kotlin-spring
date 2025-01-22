package io.github.harryjhin.entity.core.converter

import io.github.harryjhin.common.member.EncodedPassword
import io.github.harryjhin.common.member.toEncodedPassword
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class EncodedPasswordConverter : AttributeConverter<EncodedPassword, String> {

    override fun convertToDatabaseColumn(attribute: EncodedPassword?): String? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: String?): EncodedPassword? {
        return dbData?.toEncodedPassword()
    }
}