package io.github.harryjhin.entity.core

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Comment
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class ModifiableBaseEntity(
    modifiedAt: LocalDateTime = LocalDateTime.now(),
) : CreatableBaseEntity() {

    @LastModifiedDate
    @Comment("수정일시")
    @Column(name = "MODIFIED_AT")
    @JdbcTypeCode(SqlTypes.LOCAL_DATE_TIME)
    var updatedAt: LocalDateTime = modifiedAt
        protected set
}
