package io.github.harryjhin.domain.member.function

import io.github.harryjhin.domain.member.model.Member
import io.github.harryjhin.domain.member.model.toMember
import io.github.harryjhin.domain.member.property.PasswordProperties
import io.github.harryjhin.domain.member.repository.MemberRepository
import io.github.harryjhin.domain.member.repository.PasswordRepository
import io.github.harryjhin.entity.member.MemberEntity
import io.github.harryjhin.entity.password.PasswordEntity
import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.password.EncodedPassword
import io.github.harryjhin.model.password.RawPassword
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SaveMemberUseCase(
    private val getMember: GetMemberUseCase,
    private val passwordEncoder: PasswordEncoder,
    private val memberRepository: MemberRepository,
    private val passwordRepository: PasswordRepository,
    private val passwordProperties: PasswordProperties,
) {

    operator fun invoke(
        username: Username = Username("tester"),
        rawPassword: RawPassword = RawPassword("password"),
        email: Email = Email("tester@gmail.com"),
    ): Member {
        requireNonExist(username) { "username 중복입니다. 다른 username을 입력해주세요." }

        val member = MemberEntity {
            this.username = username
            this.email = email
        }.run(memberRepository::save)

        PasswordEntity {
            this.memberId = MemberId(member.id)
            this.strength = passwordProperties.strength
            this.encodedPassword = rawPassword.toEncodedPassword()
        }.run(passwordRepository::save)

        return member.toMember()
    }

    private fun requireNonExist(
        username: Username,
        lazyMessage: () -> Any,
    ) {
        if (getMember(username) != null) {
            throw IllegalArgumentException(lazyMessage().toString())
        }
    }

    private fun RawPassword.toEncodedPassword(): EncodedPassword = EncodedPassword(passwordEncoder.encode(value))
}
