package io.github.harryjhin.entity.core.converter

import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.id.toMemberId
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class MemberIdConverter : AttributeConverter<MemberId, Long> {

    override fun convertToDatabaseColumn(attribute: MemberId?): Long? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: Long?): MemberId? {
        return dbData?.toMemberId()
    }
}