package io.github.harryjhin.domain.member.port

import io.github.harryjhin.bootstrap.member.ReadAllMember
import io.github.harryjhin.common.member.MemberCompat
import io.github.harryjhin.domain.member.projection.MemberProjection
import io.github.harryjhin.domain.member.repository.MemberQuerydslRepository
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
@Conditional(ReadAllMemberPort.GetAllMemberCondition::class)
class ReadAllMemberPort(
    private val memberQuerydslRepository: MemberQuerydslRepository,
) : ReadAllMember {

    override fun invoke(): List<MemberCompat> {
        return memberQuerydslRepository.findAll()
    }

    override fun invoke(sort: Sort): List<MemberCompat> {
        return memberQuerydslRepository.findAll(sort)
    }

    override fun invoke(pageable: Pageable): Page<MemberCompat> {
        return memberQuerydslRepository.findAll(pageable)
            .map(MemberProjection::toMember)
    }

    class GetAllMemberCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return context.beanFactory!!.getBeanNamesForType(ReadAllMember::class.java).isEmpty()
        }
    }
}