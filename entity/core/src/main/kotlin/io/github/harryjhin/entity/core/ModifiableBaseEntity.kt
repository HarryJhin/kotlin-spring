package io.github.harryjhin.entity.core

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import jakarta.persistence.Version
import java.time.Instant
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

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

    @Version
    @Comment("버전")
    @Column(name = "VERSION", nullable = false)
    var version: Long = 0
        protected set

    /**
     * ### 버전 일치 여부
     *
     * 버전이 일치하는지 확인합니다.
     *
     * @param version 버전
     * @return 일치 여부
     */
    fun matchVersion(version: Long) = this.version == version
}