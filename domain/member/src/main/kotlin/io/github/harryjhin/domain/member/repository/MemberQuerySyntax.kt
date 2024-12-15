package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import io.github.harryjhin.entity.member.QMemberEntity.memberEntity
import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface MemberQuerySyntax {

    fun idEq(memberId: MemberId?): BooleanExpression? {
        if (memberId == null) {
            return null
        }
        return memberEntity.id.eq(memberId.value)
    }

    fun usernameEq(username: Username?): BooleanExpression? {
        if (username == null) {
            return null
        }
        return memberEntity.username.eq(username.value)
    }

    fun emailEq(email: Email?): BooleanExpression? {
        if (email == null) {
            return null
        }
        return memberEntity.email.eq(email.value)
    }
}