package io.github.harryjhin.entity.core.converter

import io.github.harryjhin.common.member.Username
import io.github.harryjhin.common.member.toUsername
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class UsernameConverter : AttributeConverter<Username, String> {

    override fun convertToDatabaseColumn(attribute: Username?): String? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: String?): Username? {
        return dbData?.toUsername()
    }
}