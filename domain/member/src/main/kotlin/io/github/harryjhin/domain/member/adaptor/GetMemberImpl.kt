package io.github.harryjhin.domain.member.adaptor

import io.github.harryjhin.bootstrap.member.GetMember
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.member.Member
import io.github.harryjhin.common.member.Username
import io.github.harryjhin.domain.member.repository.MemberRepository
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
@Conditional(GetMemberImpl.GetMemberCondition::class)
class GetMemberImpl(
    private val memberRepository: MemberRepository,
) : GetMember {

    override operator fun invoke(memberId: MemberId): Member? {
        return memberRepository.findById(memberId)
            ?.toSimpleMember()
    }

    override operator fun invoke(username: Username): Member? {
        return memberRepository.findByUsername(username)
            ?.toSimpleMember()
    }

    class GetMemberCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return context.beanFactory!!.getBeanNamesForType(GetMember::class.java).isEmpty()
        }
    }
}