package io.github.harryjhin.domain.member.function

import io.github.harryjhin.domain.member.model.Password
import io.github.harryjhin.domain.member.model.toModel
import io.github.harryjhin.domain.member.repository.MemberRepository
import io.github.harryjhin.domain.member.repository.PasswordRepository
import io.github.harryjhin.model.member.MemberId
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class GetPasswordUseCase(
    private val memberRepository: MemberRepository,
    private val passwordRepository: PasswordRepository,
) {

    operator fun invoke(memberId: MemberId): Password? {
        requireExist(memberId) { "존재하지 않는 회원입니다. member.id: $memberId" }

        return passwordRepository.findByMemberId(memberId.value)
            ?.toModel()
    }

    private fun requireExist(
        memberId: MemberId,
        lazyMessage: () -> Any,
    ) {
        if (!memberRepository.existsById(memberId.value)) {
            throw IllegalArgumentException(lazyMessage().toString())
        }
    }
}
