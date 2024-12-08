package io.github.harryjhin.entity.core

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

/**
 * ### 생성 가능한 기본 엔티티
 *
 * `CreatableBaseEntity`는 엔티티의 생성일시를 포함하는 추상 클래스입니다.
 * 모든 엔티티는 생성일시를 가지며, 이 클래스를 상속받아 사용합니다.
 * 만약, 수정일시를 포함하고 싶다면 [ModifiableBaseEntity]를 사용합니다.
 *
 * @property T 엔티티 식별자의 타입 (예: Long, UUID 등)
 * @property _createdAt 내부에서 관리하는 영속성 생성일시
 * @property createdAt 외부에서 접근 가능한 생성일시
 *
 * @see ModifiableBaseEntity
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class CreatableBaseEntity(
    createdAt: Instant = Instant.now(),
) : IdentifiableBaseEntity() {

    @CreatedDate
    @Comment("생성일시")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    var createdAt: Instant = createdAt
        protected set
}
