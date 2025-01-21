package io.github.harryjhin.domain.member.repository

import com.querydsl.core.types.dsl.BooleanExpression
import io.github.harryjhin.domain.member.syntax.MemberAuthenticationQuerySyntax
import io.github.harryjhin.domain.member.syntax.MemberInfoQuerySyntax
import io.github.harryjhin.entity.member.QMemberInfoEntity.memberInfoEntity
import io.github.harryjhin.model.member.MemberId
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class MemberRepository(
) : MemberInfoQuerySyntax,
    MemberAuthenticationQuerySyntax {

    private fun idEq(memberId: MemberId?): BooleanExpression? {
        if (memberId == null) {
            return null
        }
        return memberInfoEntity.id.eq(memberId.value)
    }
}