package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import io.github.harryjhin.entity.member.MemberInfoEntity
import io.github.harryjhin.entity.member.QMemberInfoEntity.memberInfoEntity
import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class MemberRepository(
    @PersistenceContext private val entityManager: EntityManager,
    private val jpaQueryFactory: JPAQueryFactory,
) : MemberQuerySyntax,
    MemberAuthenticationQuerySyntax {

    @Suppress("UNCHECKED_CAST")
    private val entityInformation: JpaEntityInformation<MemberInfoEntity, Long> =
        JpaEntityInformationSupport.getEntityInformation(MemberInfoEntity::class.java, entityManager)
        as JpaEntityInformation<MemberInfoEntity, Long>

    @Transactional
    fun save(entity: MemberInfoEntity): MemberInfoEntity {
        if (entityInformation.isNew(entity)) {
            entityManager.persist(entity)
            return entity
        } else {
            throw UnsupportedOperationException("entityManager.merge(entity) is not supported.")
        }
    }

    fun findById(memberId: MemberId): MemberInfoEntity? {
        return jpaQueryFactory.selectFrom(memberInfoEntity)
            .where(
                idEq(memberId)
            )
            .fetchOne()
    }

    fun findByUsername(username: Username): MemberInfoEntity? {
        return jpaQueryFactory.selectFrom(memberInfoEntity)
            .innerJoinMemberAuthentication()
            .where(
                usernameEq(username)
            )
            .fetchOne()
    }

    fun findByEmail(email: Email): MemberInfoEntity? {
        return jpaQueryFactory.selectFrom(memberInfoEntity)
            .where(
                emailEq(email)
            )
            .fetchOne()
    }

    fun findAll(): List<MemberInfoEntity> {
        return jpaQueryFactory.selectFrom(memberInfoEntity)
            .fetch()
    }

    fun existsById(memberId: MemberId): Boolean {
        return jpaQueryFactory.select(memberInfoEntity.id)
            .from(memberInfoEntity)
            .where(idEq(memberId))
            .fetchFirst() != null
    }

    @Transactional
    fun deleteById(memberId: MemberId): Long {
        return jpaQueryFactory.delete(memberInfoEntity)
            .where(idEq(memberId))
            .execute()
    }

    @Transactional
    fun delete(entity: MemberInfoEntity): Long {
        return jpaQueryFactory.delete(memberInfoEntity)
            .where(memberInfoEntity.id.eq(entity.id))
            .execute()
    }

    private fun idEq(memberId: MemberId?): BooleanExpression? {
        if (memberId == null) {
            return null
        }
        return memberInfoEntity.id.eq(memberId.value)
    }
}