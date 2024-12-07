package io.github.harryjhin.domain.member.function

import io.github.harryjhin.domain.member.model.Member
import io.github.harryjhin.domain.member.exception.NoSuchMemberException
import io.github.harryjhin.domain.member.extension.toMember
import io.github.harryjhin.domain.member.repository.MemberRepository
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class GetMemberUseCase(
    private val memberRepository: MemberRepository,
) {

    operator fun invoke(id: MemberId): Member {
        return memberRepository.findByIdOrNull(id.value)
            ?.toMember()
            ?: throw NoSuchMemberException("회원 `${id}`가 존재하지 않습니다.")
    }

    operator fun invoke(username: Username): Member? {
        return memberRepository.findByUsername(username.value)
            ?.toMember()
    }
}
