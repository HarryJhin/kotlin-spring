package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.member.Username
import io.github.harryjhin.domain.core.QuerydslRepositorySupport
import io.github.harryjhin.domain.member.projection.MemberProjection
import io.github.harryjhin.domain.member.projection.QMemberProjection
import io.github.harryjhin.entity.member.MemberEntity
import io.github.harryjhin.entity.member.QMemberAuthenticationEntity.memberAuthenticationEntity
import io.github.harryjhin.entity.member.QMemberEntity.memberEntity
import io.github.harryjhin.entity.member.innerJoinMemberAuthentication
import io.github.harryjhin.entity.member.leftJoinMemberAuthentication
import io.github.harryjhin.entity.member.usernameEq
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class MemberQuerydslRepository : QuerydslRepositorySupport(MemberEntity::class.java) {

    val memberProjection = QMemberProjection(
        memberEntity.id,
        memberEntity.name,
        memberEntity.email,
        memberAuthenticationEntity.username,
        memberAuthenticationEntity.password,
    )

    fun findById(memberId: MemberId): MemberProjection? {
        return jpaQueryFactory
            .select(memberProjection)
            .from(memberEntity)
            .innerJoinMemberAuthentication()
            .where(idEq(memberId))
            .fetchOne()
    }

    fun findByUsername(username: Username): MemberProjection? {
        return jpaQueryFactory
            .select(memberProjection)
            .from(memberEntity)
            .innerJoinMemberAuthentication()
            .where(usernameEq(username))
            .fetchOne()
    }

    fun findAll(): List<MemberProjection> {
        return jpaQueryFactory
            .select(memberProjection)
            .from(memberEntity)
            .leftJoinMemberAuthentication()
            .fetch()
    }

    fun findAll(sort: Sort): List<MemberProjection> {
        return jpaQueryFactory
            .select(memberProjection)
            .from(memberEntity)
            .leftJoinMemberAuthentication()
            .orderBy(sort)
            .fetch()
    }

    fun findAll(pageable: Pageable): Page<MemberProjection> {
        val contentQuery = jpaQueryFactory
            .select(memberProjection)
            .from(memberEntity)
            .leftJoinMemberAuthentication()

        val countQuery = jpaQueryFactory
            .select(memberEntity.id.count())
            .from(memberEntity)

        return paging(contentQuery, countQuery, pageable)
    }

    private fun idEq(memberId: MemberId?): BooleanExpression? {
        if (memberId == null) {
            return null
        }
        return memberEntity.id.eq(memberId.value)
    }
}