package io.github.harryjhin.entity.core

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class ReadEntity {

    @CreatedDate
    @Comment("생성일시")
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null
        protected set

    @CreatedBy
    @Comment("생성자")
    @Column(name = "created_by")
    var createdBy: Long? = null
        protected set
}