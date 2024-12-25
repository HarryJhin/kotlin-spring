package io.github.harryjhin.entity.core.listener

import io.github.harryjhin.entity.core.IdentifiableBaseEntity
import io.github.harryjhin.entity.log.EntityLifecycleType
import org.springframework.context.ApplicationEvent

class LoggingEvent(
    val type: EntityLifecycleType,
    entity: IdentifiableBaseEntity,
) : ApplicationEvent(entity) {

    override fun getSource(): IdentifiableBaseEntity {
        return super.getSource() as IdentifiableBaseEntity
    }
}