package io.github.harryjhin.entity.core

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Transient
import org.hibernate.annotations.Comment
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class ModifiableBaseEntity<T> : CreatableBaseEntity<T>() {

    @LastModifiedDate
    @Comment("수정일시")
    @Column(name = "UPDATED_AT")
    @Suppress("PropertyName")
    @JdbcTypeCode(SqlTypes.LOCAL_DATE_TIME)
    protected var _updatedAt: LocalDateTime? = null

    @get:Transient
    val updatedAt: LocalDateTime
        get() = _updatedAt ?: throw EntityNotFoundException("아직 영속되지 않은 엔티티입니다.")
}
