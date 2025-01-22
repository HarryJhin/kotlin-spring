package io.github.harryjhin.domain.member.syntax

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQuery
import io.github.harryjhin.entity.member.QMemberInfoEntity.memberInfoEntity
import io.github.harryjhin.entity.member.QMemberAuthenticationEntity.memberAuthenticationEntity
import io.github.harryjhin.common.email.Email
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface MemberInfoQuerySyntax {

    fun <T> JPAQuery<T>.innerJoinMemberAuthentication(): JPAQuery<T> {
        return this.innerJoin(memberAuthenticationEntity)
            .on(memberInfoEntity.id.eq(memberAuthenticationEntity.id))
    }

    fun emailEq(email: Email?): BooleanExpression? {
        if (email == null) {
            return null
        }
        return memberInfoEntity.email.eq(email.value)
    }
}