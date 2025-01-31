package io.github.harryjhin.domain.member.port

import io.github.harryjhin.bootstrap.member.ReadMember
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.member.MemberCompat
import io.github.harryjhin.common.member.Username
import io.github.harryjhin.domain.member.repository.MemberQuerydslRepository
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
@Conditional(ReadMemberPort.GetMemberCondition::class)
class ReadMemberPort(
    private val memberQuerydslRepository: MemberQuerydslRepository,
) : ReadMember {

    override operator fun invoke(memberId: MemberId): MemberCompat? {
        return memberQuerydslRepository.findById(memberId)
            ?.toMember()
    }

    override operator fun invoke(username: Username): MemberCompat? {
        return memberQuerydslRepository.findByUsername(username)
            ?.toMember()
    }

    class GetMemberCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return context.beanFactory!!.getBeanNamesForType(ReadMember::class.java).isEmpty()
        }
    }
}