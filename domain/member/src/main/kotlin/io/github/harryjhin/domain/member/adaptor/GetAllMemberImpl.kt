package io.github.harryjhin.domain.member.adaptor

import io.github.harryjhin.domain.member.port.GetAllMember
import io.github.harryjhin.domain.member.repository.MemberInfoRepository
import io.github.harryjhin.entity.member.MemberInfoEntity
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
@Conditional(GetAllMemberImpl.GetAllMemberCondition::class)
class GetAllMemberImpl(
    private val memberInfoRepository: MemberInfoRepository,
) : GetAllMember {

    override fun invoke(): List<MemberInfoEntity> {
        return memberInfoRepository.findAll()
    }

    class GetAllMemberCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return context.beanFactory!!.getBeanNamesForType(GetAllMember::class.java).isEmpty()
        }
    }
}