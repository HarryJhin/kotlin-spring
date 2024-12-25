package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import io.github.harryjhin.domain.member.projection.CompositeMemberDto
import io.github.harryjhin.domain.member.projection.QCompositeMemberDto
import io.github.harryjhin.entity.member.QMemberInfoEntity.memberInfoEntity
import io.github.harryjhin.entity.member.authentication.QMemberAuthenticationEntity.memberAuthenticationEntity
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class CompositeMemberRepository(
    private val jpaQueryFactory: JPAQueryFactory,
) : MemberQuerySyntax,
    MemberAuthenticationQuerySyntax {

    fun findByMemberId(memberId: MemberId): CompositeMemberDto? {
        return jpaQueryFactory
            .select(
                QCompositeMemberDto(
                    memberInfoEntity.id,
                    memberInfoEntity.name,
                    memberInfoEntity.email,
                    memberAuthenticationEntity.username,
                    memberAuthenticationEntity.password,
                )
            ).from(memberInfoEntity)
            .innerJoinMemberAuthentication()
            .where(
                idEq(memberId)
            )
            .fetchOne()
    }

    fun findByUsername(username: Username): CompositeMemberDto? {
        return jpaQueryFactory
            .select(
                QCompositeMemberDto(
                    memberInfoEntity.id,
                    memberInfoEntity.name,
                    memberInfoEntity.email,
                    memberAuthenticationEntity.username,
                    memberAuthenticationEntity.password,
                )
            ).from(memberInfoEntity)
            .innerJoinMemberAuthentication()
            .where(
                usernameEq(username)
            )
            .fetchOne()
    }

    fun findAll(): List<CompositeMemberDto> {
        return jpaQueryFactory
            .select(
                QCompositeMemberDto(
                    memberInfoEntity.id,
                    memberInfoEntity.name,
                    memberInfoEntity.email,
                    memberAuthenticationEntity.username,
                    memberAuthenticationEntity.password,
                )
            ).from(memberInfoEntity)
            .innerJoinMemberAuthentication()
            .fetch()
    }

    private fun idEq(memberId: MemberId?): BooleanExpression? {
        if (memberId == null) {
            return null
        }
        return memberInfoEntity.id.eq(memberId.value)
    }
}