package io.github.harryjhin.domain.member.adaptor

import io.github.harryjhin.domain.member.port.GetMember
import io.github.harryjhin.domain.member.repository.MemberInfoRepository
import io.github.harryjhin.entity.member.MemberInfoEntity
import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
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
    private val memberInfoRepository: MemberInfoRepository,
) : GetMember {

    override fun invoke(memberId: MemberId): MemberInfoEntity? {
        return memberInfoRepository.findById(memberId.value)
    }

    override fun invoke(username: Username): MemberInfoEntity? {
        return memberInfoRepository.findByUsername(username)
    }

    override fun invoke(email: Email): MemberInfoEntity? {
        return memberInfoRepository.findByEmail(email)
    }

    class GetMemberCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return context.beanFactory!!.getBeanNamesForType(GetMember::class.java).isEmpty()
        }
    }
}