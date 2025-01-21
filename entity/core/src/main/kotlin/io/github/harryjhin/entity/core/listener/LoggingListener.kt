package io.github.harryjhin.entity.core.listener

import io.github.harryjhin.entity.core.IdentifiableBaseEntity
import io.github.harryjhin.entity.log.EntityLifecycleType
import io.github.harryjhin.infra.event.Events
import jakarta.persistence.PostPersist
import jakarta.persistence.PostRemove
import jakarta.persistence.PostUpdate

class LoggingListener {

    @PostPersist
    fun postPersist(entity: IdentifiableBaseEntity) {
        Events.raise(
            LoggingEvent(
                type = EntityLifecycleType.CREATE,
                entity = entity
            )
        )
    }

    @PostUpdate
    fun postUpdate(entity: IdentifiableBaseEntity) {
        Events.raise(
            LoggingEvent(
                type = EntityLifecycleType.UPDATE,
                entity = entity
            )
        )
    }

    @PostRemove
    fun postRemove(entity: IdentifiableBaseEntity) {
        Events.raise(
            LoggingEvent(
                type = EntityLifecycleType.DELETE,
                entity = entity
            )
        )
    }
}