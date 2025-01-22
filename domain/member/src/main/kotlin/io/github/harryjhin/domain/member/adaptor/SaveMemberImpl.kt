package io.github.harryjhin.domain.member.adaptor

import io.github.harryjhin.bootstrap.member.SaveMember
import io.github.harryjhin.bootstrap.member.SaveMemberRequestBuilder
import io.github.harryjhin.domain.member.extension.toMemberAuthenticationEntity
import io.github.harryjhin.domain.member.extension.toMemberInfoEntity
import io.github.harryjhin.domain.member.property.PasswordProperties
import io.github.harryjhin.domain.member.repository.MemberAuthenticationRepository
import io.github.harryjhin.domain.member.repository.MemberInfoRepository
import io.github.harryjhin.common.member.toEncodedPassword
import io.github.harryjhin.common.id.toMemberId
import io.github.harryjhin.common.member.Member
import io.github.harryjhin.common.member.SimpleMember
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SaveMemberImpl(
    private val passwordEncoder: PasswordEncoder,
    private val memberInfoRepository: MemberInfoRepository,
    private val passwordProperties: PasswordProperties,
    private val memberAuthenticationRepository: MemberAuthenticationRepository,
) : SaveMember {

    override fun invoke(request: SaveMember.Request): Member {
        val member = request.toMemberInfoEntity()
            .run(memberInfoRepository::save)
        val memberAuthentication = request.toMemberAuthenticationEntity(
            memberId = member.id.toMemberId(),
            strength = passwordProperties.strength,
            password = passwordEncoder.encode(request.rawPassword.value).toEncodedPassword()
        ).run(memberAuthenticationRepository::save)

        return SimpleMember(
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
    ): Member {
        return builder.apply(buildToAction)
            .build()
            .run(::invoke)
    }
}