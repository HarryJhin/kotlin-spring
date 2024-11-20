package io.github.harryjhin.entity.core

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class RWEntity : ROEntity() {

    @[Column(name = "updated_at") LastModifiedDate]
    var updatedAt: LocalDateTime? = null

    @[Column(name = "updated_by") LastModifiedBy]
    var updatedBy: Long? = null
}