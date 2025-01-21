package io.github.harryjhin.entity.core.converter

import io.github.harryjhin.model.member.PasswordStrength
import io.github.harryjhin.model.member.toPasswordStrength
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class PasswordStrengthConverter : AttributeConverter<PasswordStrength, Int> {

    override fun convertToDatabaseColumn(attribute: PasswordStrength?): Int? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: Int?): PasswordStrength? {
        return dbData?.toPasswordStrength()
    }
}