package io.github.harryjhin.sign.service

import io.github.harryjhin.domain.member.port.SaveMember
import io.github.harryjhin.domain.member.projection.Member
import io.github.harryjhin.sign.request.SignUpRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpService(
    private val saveMember: SaveMember,
) {

    @Transactional
    fun execute(
        request: SignUpRequest,
    ): Member {
        return saveMember {
            this.name = request.name
            this.email = request.email
            this.rawPassword = request.rawPassword
        }
    }
}
