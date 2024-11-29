package io.github.harryjhin.entity.core

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class ModifiableBaseEntity<T> : CreatableBaseEntity<T>() {

    @LastModifiedDate
    @Comment("수정일시")
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
        protected set
}
