package io.github.harryjhin.sign.service

import io.github.harryjhin.bootstrap.member.CreateMember
import io.github.harryjhin.common.member.MemberCompat
import io.github.harryjhin.sign.request.SignUpRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpService(
    private val createMember: CreateMember,
) {

    @Transactional
    fun execute(
        request: SignUpRequest,
    ): MemberCompat {
        return createMember {
            this.name = request.name
            this.email = request.email
            this.rawPassword = request.rawPassword
        }
    }
}
