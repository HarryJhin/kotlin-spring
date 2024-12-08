package io.github.harryjhin.entity.core

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class ModifiableBaseEntity(
    modifiedAt: Instant = Instant.now(),
) : CreatableBaseEntity() {

    @LastModifiedDate
    @Comment("수정일시")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_AT", nullable = false)
    var modifiedAt: Instant = modifiedAt
        protected set
}
