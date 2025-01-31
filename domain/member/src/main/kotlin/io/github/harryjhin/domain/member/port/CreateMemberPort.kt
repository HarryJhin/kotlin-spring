package io.github.harryjhin.domain.member.port

import io.github.harryjhin.bootstrap.member.CreateMember
import io.github.harryjhin.bootstrap.member.SaveMemberRequestBuilder
import io.github.harryjhin.domain.member.extension.toMemberAuthenticationEntity
import io.github.harryjhin.domain.member.extension.toMemberEntity
import io.github.harryjhin.domain.member.property.PasswordProperties
import io.github.harryjhin.domain.member.repository.MemberAuthenticationJpaRepository
import io.github.harryjhin.domain.member.repository.MemberInfoJpaRepository
import io.github.harryjhin.common.member.toEncodedPassword
import io.github.harryjhin.common.id.toMemberId
import io.github.harryjhin.common.member.MemberCompat
import io.github.harryjhin.common.member.Member
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CreateMemberPort(
    private val passwordEncoder: PasswordEncoder,
    private val passwordProperties: PasswordProperties,
    private val memberInfoJpaRepository: MemberInfoJpaRepository,
    private val memberAuthenticationJpaRepository: MemberAuthenticationJpaRepository,
) : CreateMember {

    override fun invoke(request: CreateMember.Request): MemberCompat {
        val member = request.toMemberEntity()
            .run(memberInfoJpaRepository::save)
        val memberAuthentication = request.toMemberAuthenticationEntity(
            memberId = member.id.toMemberId(),
            strength = passwordProperties.strength,
            password = passwordEncoder.encode(request.rawPassword.value).toEncodedPassword()
        ).run(memberAuthenticationJpaRepository::save)

        return Member(
            memberId = member.id.toMemberId(),
            name = member.name,
            email = member.email,
            username = memberAuthentication.username,
            password = memberAuthentication.password
        )
    }

    override fun invoke(
        builder: SaveMemberRequestBuilder,
        buildToAction: SaveMemberRequestBuilder.() -> Unit
    ): MemberCompat {
        return builder.apply(buildToAction)
            .build()
            .run(::invoke)
    }
}