package io.github.harryjhin.entity.core.converter

import io.github.harryjhin.common.name.Name
import io.github.harryjhin.common.name.toName
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class NameConverter : AttributeConverter<Name, String> {

    override fun convertToDatabaseColumn(attribute: Name?): String? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: String?): Name? {
        return dbData?.toName()
    }
}