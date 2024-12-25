package io.github.harryjhin.entity.core.listener

import io.github.harryjhin.entity.core.IdentifiableBaseEntity
import io.github.harryjhin.entity.log.EntityLifecycleType
import jakarta.persistence.PostPersist
import jakarta.persistence.PostRemove
import jakarta.persistence.PostUpdate
import org.springframework.context.ApplicationEventPublisher

class LoggingListener(
    private val publisher: ApplicationEventPublisher
) {

    @PostPersist
    fun postPersist(entity: IdentifiableBaseEntity) {
        publisher.publishEvent(
            LoggingEvent(
                type = EntityLifecycleType.CREATE,
                entity = entity
            )
        )
    }

    @PostUpdate
    fun postUpdate(entity: IdentifiableBaseEntity) {
        publisher.publishEvent(
            LoggingEvent(
                type = EntityLifecycleType.UPDATE,
                entity = entity
            )
        )
    }

    @PostRemove
    fun postRemove(entity: IdentifiableBaseEntity) {
        publisher.publishEvent(
            LoggingEvent(
                type = EntityLifecycleType.DELETE,
                entity = entity
            )
        )
    }
}