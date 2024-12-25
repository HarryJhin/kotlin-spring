package io.github.harryjhin.entity.core.listener

import io.github.harryjhin.entity.log.LogEntity
import jakarta.persistence.Column
import jakarta.persistence.EntityManager
import jakarta.persistence.Table
import kotlin.reflect.full.memberProperties
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class LoggingEventHandler(
    private val entityManager: EntityManager,
) {

    @EventListener
    fun handle(event: LoggingEvent) {
        val kClass = event.source::class
        LogEntity {
            this.type = event.type
            this.entityId = event.source.id
            this.tableName = kClass.annotations.find { it is Table }.let { it as Table }.name.lowercase()
            this.message = kClass.memberProperties.joinToString(
                separator = ", ",
                prefix = "{",
                postfix = "}"
            ) { property ->
                val name = property.annotations.find { it is Column }?.let { it as Column }?.name?.lowercase()
                    ?: property.name.asSnakeCase()
                val value = property.getter.call(event.source)
                    ?: ""
                "\"$name\": \"$value\""
            }
        }.run(entityManager::persist)
    }
}

private fun String.asSnakeCase(): String {
    return this.replace(Regex("([a-z])([A-Z]+)"), "$1_$2").lowercase()
}