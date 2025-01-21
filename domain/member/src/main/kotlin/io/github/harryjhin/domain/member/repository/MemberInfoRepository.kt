package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import io.github.harryjhin.domain.core.QuerydslRepository
import io.github.harryjhin.domain.member.syntax.MemberAuthenticationQuerySyntax
import io.github.harryjhin.domain.member.syntax.MemberInfoQuerySyntax
import io.github.harryjhin.entity.member.MemberInfoEntity
import io.github.harryjhin.entity.member.QMemberInfoEntity.memberInfoEntity
import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class MemberInfoRepository : QuerydslRepository<MemberInfoEntity, Long>(MemberInfoEntity::class.java),
    MemberInfoQuerySyntax,
    MemberAuthenticationQuerySyntax {

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

    private fun idEq(memberId: MemberId?): BooleanExpression? {
        if (memberId == null) {
            return null
        }
        return memberInfoEntity.id.eq(memberId.value)
    }
}