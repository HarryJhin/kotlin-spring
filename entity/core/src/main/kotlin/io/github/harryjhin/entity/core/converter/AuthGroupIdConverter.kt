package io.github.harryjhin.entity.core.converter

import io.github.harryjhin.common.id.AuthGroupId
import io.github.harryjhin.common.id.toAuthGroupId
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class AuthGroupIdConverter : AttributeConverter<AuthGroupId, Long> {

    override fun convertToDatabaseColumn(attribute: AuthGroupId?): Long? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: Long?): AuthGroupId? {
        return dbData?.toAuthGroupId()
    }
}