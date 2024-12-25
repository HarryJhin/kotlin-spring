package io.github.harryjhin.entity.log

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(
    name = "LOG",
    indexes = [
        Index(name = "IDX_LOG_1", columnList = "TABLE_NAME,ENTITY_ID"),
        Index(name = "IDX_LOG_2", columnList = "TYPE"),
    ]
)
class LogEntity internal constructor(
    builder: LogEntityBuilder,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false, length = 20)
    var type: EntityLifecycleType = builder.type!!
        protected set

    @Column(name = "TABLE_NAME", nullable = false, length = 50)
    var tableName: String = builder.tableName!!
        protected set

    @Column(name = "ENTITY_ID", nullable = false)
    var entityId: Long = builder.entityId!!
        protected set

    @Column(name = "MESSAGE", nullable = false, columnDefinition = "TEXT")
    var message: String = builder.message!!
        protected set

    @Column(name = "CREATED_AT", nullable = false)
    var createdAt: Instant = Instant.now()
        protected set
}

class LogEntityBuilder internal constructor(
    var type: EntityLifecycleType? = null,
    var tableName: String? = null,
    var entityId: Long? = null,
    var message: String? = null,
) {

    internal fun build(): LogEntity {
        requireNotNull(type) { "log.type is required" }
        requireNotNull(tableName) { "log.tableName is required" }
        requireNotNull(entityId) { "log.entityId is required" }
        requireNotNull(message) { "log.message is required" }
        return LogEntity(this)
    }
}

fun LogEntity(
    builder: LogEntityBuilder = LogEntityBuilder(),
    buildToAction: LogEntityBuilder.() -> Unit = {},
): LogEntity {
    return builder.apply(buildToAction).build()
}