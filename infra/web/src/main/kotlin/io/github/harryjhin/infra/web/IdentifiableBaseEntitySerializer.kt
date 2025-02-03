package io.github.harryjhin.infra.web

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import io.github.harryjhin.entity.core.IdentifiableBaseEntity

object IdentifiableBaseEntitySerializer : JsonSerializer<IdentifiableBaseEntity>() {

    override fun serialize(
        value: IdentifiableBaseEntity?,
        gen: JsonGenerator,
        serializers: SerializerProvider,
    ) {
        throw IllegalStateException("entity must not be serialized")
    }
}