package io.github.harryjhin.entity.core

import jakarta.persistence.Column
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PrePersist
import jakarta.persistence.Transient
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.proxy.HibernateProxy
import org.hibernate.type.SqlTypes
import org.springframework.data.domain.Persistable

/**
 * ### 엔티티 저장하기
 *
 * 엔티티 저장은 `CrudRepository.save(...)` 메서드를 사용하여 수행할 수 있습니다.
 * 이 메서드는 기본 JPA `EntityManager`를 사용하여 주어진 엔티티를 영속(persist)하거나 병합(merge)합니다.
 * 엔티티가 아직 영속되지 않은 경우 **Spring Data JPA**는 `entityManager.persist(...)` 메서드를 호출하여 엔티티를 저장합니다.
 * 그렇지 않으면 `entityManager.merge(...)` 메서드를 호출합니다.
 *
 * ### 엔티티 상태 탐지 전략
 *
 * **Spring Data JPA**는 엔티티가 새로운 것인지 아닌지를 감지하기 위해 다음과 같은 전략을 제공합니다:
 *
 * 1. `Version` 또는 `ID` 검사(기본값): 기본적으로 **Spring Data JPA**는 원시 타입이 아닌 `Version` 프로퍼티가 있는지 먼저 검사합니다.
 * 있는 경우 해당 속성 값이 `null`이면 엔티티가 새로 생성된 것으로 간주합니다.
 * 이러한 버전 속성이 없으면 **Spring Data JPA**는 지정된 엔티티의 `ID` 프로퍼티를 검사합니다.
 * `ID` 프로퍼티가 `null`이면 엔티티가 새로 생성된 것으로 간주합니다.
 * 그렇지 않으면 새로운 개체가 아닌 것으로 간주합니다.
 * 2. `Persistable` 구현: 엔티티가 `Persistable`를 구현하는 경우 **Spring Data JPA**는 엔티티의 `isNew()` 메서드에 의존하여 엔티티가 새로운 것인지 아닌지를 결정합니다.
 *
 * **Ref**: [Doc](https://docs.spring.io/spring-data/jpa/reference/jpa/entity-persistence.html)
 *
 * @see CreatableBaseEntity
 * @see ModifiableBaseEntity
 */
@MappedSuperclass
abstract class IdentifiableBaseEntity<ID> internal constructor() : Persistable<ID> {

    @Id
    @JdbcTypeCode(SqlTypes.BIGINT)
    @Suppress("PropertyName")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    protected var _id: ID? = null

    /**
     * ### 엔티티 식별자
     *
     * 엔티티 식별자는 엔티티의 주요 식별자(primary key)를 나타냅니다.
     * 엔티티 식별자는 엔티티가 영속성 컨텍스트에 저장되어 있는 경우에만 사용할 수 있습니다.
     * 엔티티가 아직 영속성 컨텍스트에 저장되어 있지 않은 경우 `EntityNotFoundException`이 발생합니다.
     *
     * @throws EntityNotFoundException 엔티티가 아직 영속성 컨텍스트에 저장되어 있지 않은 경우
     */
    @get:JvmName("getId2")
    val id: ID
        get() = _id ?: throw EntityNotFoundException("아직 영속되지 않은 엔티티입니다.")

    /**
     * ### 새로운 엔티티 여부
     *
     * 새 상태를 유지할 플래그입니다.
     * 영속되지 않도록 주의합니다.
     */
    @Transient
    private var _isNew: Boolean = true

    final override fun getId(): ID? = _id

    /**
     * ### 새로운 엔티티 여부 반환
     *
     * **Spring Data JPA** 리포지토리가 `EntityManager.persist(...)`를 호출할지,
     * `...merge(...)`를 호출할지 알 수 있도록 [isNew] 구현에서 [_isNew] 값을 반환합니다.
     *
     * @return 새로운 엔티티 여부
     */
    final override fun isNew(): Boolean = _isNew

    /**
     * ### 새로운 엔티티 여부 변경
     *
     * **Spring Data Jpa** 리포지토리에서 `save(...)` 또는 영속성(persistence) 공급자(provider)에 의한 인스턴스 생성 후에
     * [_isNew] 플래그가 기존 엔티티와 연결할 수 있도록 JPA 엔티티 수명 주기(lifecycle) 콜백을 사용하여 [_isNew] 값을 변경합니다.
     */
    @PrePersist
    @PostLoad
    private fun markNotNew() {
        _isNew = false
    }

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as IdentifiableBaseEntity<*>

        return _id == other._id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}
