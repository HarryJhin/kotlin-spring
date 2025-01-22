package io.github.harryjhin.entity.core.converter

import io.github.harryjhin.common.email.Email
import io.github.harryjhin.common.email.toEmail
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class EmailConverter : AttributeConverter<Email, String> {

    override fun convertToDatabaseColumn(attribute: Email?): String? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: String?): Email? {
        return dbData?.toEmail()
    }
}