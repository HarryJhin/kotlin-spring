package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import io.github.harryjhin.entity.member.authentication.MemberAuthenticationEntity
import io.github.harryjhin.entity.member.authentication.QMemberAuthenticationEntity.memberAuthenticationEntity
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class MemberAuthenticationRepository(
    private val entityManager: EntityManager,
    private val jpaQueryFactory: JPAQueryFactory,
) : MemberAuthenticationQuerySyntax {

    private val entityInformation: JpaEntityInformation<MemberAuthenticationEntity, *> =
        JpaEntityInformationSupport.getEntityInformation(MemberAuthenticationEntity::class.java, entityManager)

    @Transactional
    fun save(entity: MemberAuthenticationEntity): MemberAuthenticationEntity {
        if (entityInformation.isNew(entity)) {
            entityManager.persist(entity)
            return entity
        } else {
            return entityManager.merge(entity)
        }
    }

    fun findById(id: Long): MemberAuthenticationEntity? {
        return jpaQueryFactory.selectFrom(memberAuthenticationEntity)
            .where(
                idEq(id)
            )
            .fetchOne()
    }

    private fun idEq(id: Long?): BooleanExpression? {
        if (id == null) {
            return null
        }
        return memberAuthenticationEntity.id.eq(id)
    }
}