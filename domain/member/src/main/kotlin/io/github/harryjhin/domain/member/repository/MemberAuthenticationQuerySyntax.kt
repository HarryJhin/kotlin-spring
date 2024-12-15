package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import io.github.harryjhin.entity.member.authentication.QMemberAuthenticationEntity.memberAuthenticationEntity
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface MemberAuthenticationQuerySyntax {

    fun memberIdEq(memberId: MemberId?): BooleanExpression? {
        if (memberId == null) {
            return null
        }
        return memberAuthenticationEntity.memberId.eq(memberId.value)
    }

    fun usernameEq(username: Username?): BooleanExpression? {
        if (username == null) {
            return null
        }
        return memberAuthenticationEntity.username.eq(username.value)
    }
}