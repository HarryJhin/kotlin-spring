package io.github.harryjhin.entity.core

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class ROEntity {

    @[Column(name = "created_at") CreatedDate]
    var createdAt: LocalDateTime? = null
        protected set

    @[Column(name = "created_by") CreatedBy]
    var createdBy: Long? = null
        protected set
}