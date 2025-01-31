package io.github.harryjhin.entity.member

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQuery
import io.github.harryjhin.common.email.Email
import io.github.harryjhin.common.member.Username
import io.github.harryjhin.entity.member.QMemberAuthenticationEntity.memberAuthenticationEntity
import io.github.harryjhin.entity.member.QMemberEntity.memberEntity

/**
 * ```sql
 * inner join member_authentication
 * on member.id = member_authentication.id
 * ```
 */
fun <T> JPAQuery<T>.innerJoinMemberAuthentication(): JPAQuery<T> {
    return this.innerJoin(memberAuthenticationEntity)
        .on(memberEntity.id.eq(memberAuthenticationEntity.id))
}

/**
 * ```sql
 * left join member_authentication
 * on member.id = member_authentication.id
 * ```
 */
fun <T> JPAQuery<T>.leftJoinMemberAuthentication(): JPAQuery<T> {
    return this.leftJoin(memberAuthenticationEntity)
        .on(memberEntity.id.eq(memberAuthenticationEntity.id))
}

/**
 * ```sql
 * where member.email = :email
 * ```
 */
fun emailEq(email: Email?): BooleanExpression? {
    if (email == null) {
        return null
    }
    return memberEntity.email.eq(email.value)
}

/**
 * ```sql
 * where member.username = :username
 * ```
 */
fun usernameEq(username: Username?): BooleanExpression? {
    if (username == null) {
        return null
    }
    return memberAuthenticationEntity.username.eq(username.value)
}