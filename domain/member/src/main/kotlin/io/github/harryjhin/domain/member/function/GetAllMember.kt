package io.github.harryjhin.domain.member.function

import io.github.harryjhin.domain.member.model.Member
import io.github.harryjhin.domain.member.model.toMember
import io.github.harryjhin.domain.member.repository.MemberRepository
import io.github.harryjhin.entity.member.MemberEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class GetAllMember(
    private val memberRepository: MemberRepository,
) {

    operator fun invoke(): List<Member> {
        return memberRepository.findAll().map(MemberEntity::toMember)
    }
}
