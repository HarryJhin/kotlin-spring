package io.github.harryjhin.sign.service

import io.github.harryjhin.domain.member.function.SaveMemberUseCase
import io.github.harryjhin.sign.request.SignUpRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpService(
    private val saveMember: SaveMemberUseCase,
) {

    @Transactional
    fun execute(
        request: SignUpRequest,
    ) {
        saveMember(
            username = request.username,
            rawPassword = request.rawPassword,
            email = request.email,
        )
    }
}
