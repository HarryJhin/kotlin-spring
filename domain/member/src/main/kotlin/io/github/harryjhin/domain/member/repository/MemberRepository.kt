package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import io.github.harryjhin.entity.member.MemberEntity
import io.github.harryjhin.entity.member.QMemberEntity.memberEntity
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
    private val entityInformation: JpaEntityInformation<MemberEntity, Long> =
        JpaEntityInformationSupport.getEntityInformation(MemberEntity::class.java, entityManager)
        as JpaEntityInformation<MemberEntity, Long>

    @Transactional
    fun save(entity: MemberEntity): MemberEntity {
        if (entityInformation.isNew(entity)) {
            entityManager.persist(entity)
            return entity
        } else {
            throw UnsupportedOperationException("entityManager.merge(entity) is not supported.")
        }
    }

    fun findById(memberId: MemberId): MemberEntity? {
        return jpaQueryFactory.selectFrom(memberEntity)
            .where(
                idEq(memberId)
            )
            .fetchOne()
    }

    fun findByUsername(username: Username): MemberEntity? {
        return jpaQueryFactory.selectFrom(memberEntity)
            .innerJoinMemberAuthentication()
            .where(
                usernameEq(username)
            )
            .fetchOne()
    }

    fun findByEmail(email: Email): MemberEntity? {
        return jpaQueryFactory.selectFrom(memberEntity)
            .where(
                emailEq(email)
            )
            .fetchOne()
    }

    fun findAll(): List<MemberEntity> {
        return jpaQueryFactory.selectFrom(memberEntity)
            .fetch()
    }

    fun existsById(memberId: MemberId): Boolean {
        return jpaQueryFactory.select(memberEntity.id)
            .from(memberEntity)
            .where(idEq(memberId))
            .fetchFirst() != null
    }

    @Transactional
    fun deleteById(memberId: MemberId): Long {
        return jpaQueryFactory.delete(memberEntity)
            .where(idEq(memberId))
            .execute()
    }

    @Transactional
    fun delete(entity: MemberEntity): Long {
        return jpaQueryFactory.delete(memberEntity)
            .where(memberEntity.id.eq(entity.id))
            .execute()
    }

    private fun idEq(memberId: MemberId?): BooleanExpression? {
        if (memberId == null) {
            return null
        }
        return memberEntity.id.eq(memberId.value)
    }
}