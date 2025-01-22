package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.member.Username
import io.github.harryjhin.domain.core.QuerydslRepositorySupport
import io.github.harryjhin.domain.member.projection.MemberProjection
import io.github.harryjhin.domain.member.projection.QMemberProjection
import io.github.harryjhin.domain.member.syntax.MemberAuthenticationQuerySyntax
import io.github.harryjhin.domain.member.syntax.MemberInfoQuerySyntax
import io.github.harryjhin.entity.member.MemberInfoEntity
import io.github.harryjhin.entity.member.QMemberAuthenticationEntity.memberAuthenticationEntity
import io.github.harryjhin.entity.member.QMemberInfoEntity.memberInfoEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class MemberRepository : QuerydslRepositorySupport(MemberInfoEntity::class.java),
    MemberInfoQuerySyntax,
    MemberAuthenticationQuerySyntax {

    val memberProjection = QMemberProjection(
        memberInfoEntity.id,
        memberInfoEntity.name,
        memberInfoEntity.email,
        memberAuthenticationEntity.username,
        memberAuthenticationEntity.password,
    )

    fun findById(memberId: MemberId): MemberProjection? {
        return jpaQueryFactory
            .select(memberProjection)
            .from(memberInfoEntity)
            .innerJoinMemberAuthentication()
            .where(idEq(memberId))
            .fetchOne()
    }

    fun findByUsername(username: Username): MemberProjection? {
        return jpaQueryFactory
            .select(memberProjection)
            .from(memberInfoEntity)
            .innerJoinMemberAuthentication()
            .where(usernameEq(username))
            .fetchOne()
    }

    fun findAll(): List<MemberProjection> {
        return jpaQueryFactory
            .select(memberProjection)
            .from(memberInfoEntity)
            .innerJoinMemberAuthentication()
            .fetch()
    }

    fun findAll(sort: Sort): List<MemberProjection> {
        return jpaQueryFactory
            .select(memberProjection)
            .from(memberInfoEntity)
            .innerJoinMemberAuthentication()
            .order(sort)
            .fetch()
    }

    fun findAll(pageable: Pageable): Page<MemberProjection> {
        val contentQuery = jpaQueryFactory
            .select(memberProjection)
            .from(memberInfoEntity)
            .innerJoinMemberAuthentication()

        val countQuery = jpaQueryFactory
            .select(memberInfoEntity.id.count())
            .from(memberInfoEntity)

        return paging(contentQuery, countQuery, pageable)
    }

    private fun idEq(memberId: MemberId?): BooleanExpression? {
        if (memberId == null) {
            return null
        }
        return memberInfoEntity.id.eq(memberId.value)
    }
}