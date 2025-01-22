package io.github.harryjhin.domain.member.adaptor

import io.github.harryjhin.bootstrap.member.GetAllMember
import io.github.harryjhin.common.member.Member
import io.github.harryjhin.domain.member.projection.MemberProjection
import io.github.harryjhin.domain.member.repository.MemberRepository
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
@Conditional(GetAllMemberImpl.GetAllMemberCondition::class)
class GetAllMemberImpl(
    private val memberRepository: MemberRepository,
) : GetAllMember {

    override fun invoke(): List<Member> {
        return memberRepository.findAll()
            .map(MemberProjection::toSimpleMember)
    }

    override fun invoke(sort: Sort): List<Member> {
        return memberRepository.findAll(sort)
            .map(MemberProjection::toSimpleMember)
    }

    override fun invoke(pageable: Pageable): Page<Member> {
        return memberRepository.findAll(pageable)
            .map(MemberProjection::toSimpleMember)
    }

    class GetAllMemberCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return context.beanFactory!!.getBeanNamesForType(GetAllMember::class.java).isEmpty()
        }
    }
}