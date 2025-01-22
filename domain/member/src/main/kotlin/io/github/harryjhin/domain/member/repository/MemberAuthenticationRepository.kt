package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import io.github.harryjhin.domain.core.QuerydslRepository
import io.github.harryjhin.domain.member.syntax.MemberAuthenticationQuerySyntax
import io.github.harryjhin.entity.member.MemberAuthenticationEntity
import io.github.harryjhin.entity.member.QMemberAuthenticationEntity.memberAuthenticationEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class MemberAuthenticationRepository :
    QuerydslRepository<MemberAuthenticationEntity, Long>(MemberAuthenticationEntity::class.java),
    MemberAuthenticationQuerySyntax {

    private fun idEq(id: Long?): BooleanExpression? {
        if (id == null) {
            return null
        }
        return memberAuthenticationEntity.id.eq(id)
    }
}