package io.github.harryjhin.domain.member.adaptor

import io.github.harryjhin.domain.member.port.GetAllMember
import io.github.harryjhin.domain.member.port.GetMember
import io.github.harryjhin.domain.member.port.SaveMember
import io.github.harryjhin.domain.member.port.SaveMemberRequestBuilder
import io.github.harryjhin.domain.member.port.toMemberAuthenticationEntity
import io.github.harryjhin.domain.member.port.toMemberEntity
import io.github.harryjhin.domain.member.projection.CompositeMemberDto
import io.github.harryjhin.domain.member.property.PasswordProperties
import io.github.harryjhin.domain.member.repository.CompositeMemberRepository
import io.github.harryjhin.domain.member.repository.MemberAuthenticationRepository
import io.github.harryjhin.domain.member.repository.MemberRepository
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.member.toMemberId
import io.github.harryjhin.model.member.toEncodedPassword
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = false)
@ConditionalOnMissingBean(ApiMemberAdaptor::class, BatchMemberAdaptor::class, CmsMemberAdaptor::class)
class DefaultMemberAdaptor(
    private val passwordEncoder: PasswordEncoder,
    private val memberRepository: MemberRepository,
    private val passwordProperties: PasswordProperties,
    private val compositeMemberRepository: CompositeMemberRepository,
    private val memberAuthenticationRepository: MemberAuthenticationRepository,
) : SaveMember, GetMember, GetAllMember {

    @Transactional
    override fun invoke(request: SaveMember.Request): CompositeMemberDto {
        val member = request.toMemberEntity()
            .run(memberRepository::save)
        val memberAuthentication = request.toMemberAuthenticationEntity(
            memberId = member.id.toMemberId(),
            strength = passwordProperties.strength,
            password = passwordEncoder.encode(request.rawPassword.value).toEncodedPassword()
        ).run(memberAuthenticationRepository::save)

        return CompositeMemberDto(
            memberId = member.id,
            name = member.name,
            email = member.email,
            username = memberAuthentication.username,
            password = memberAuthentication.password,
        )
    }

    override fun invoke(
        builder: SaveMemberRequestBuilder,
        buildToAction: SaveMemberRequestBuilder.() -> Unit
    ): CompositeMemberDto {
        return builder.apply(buildToAction)
            .build()
            .run(::invoke)
    }

    override fun invoke(memberId: MemberId): CompositeMemberDto? {
        return compositeMemberRepository.findByMemberId(memberId)
    }

    override fun invoke(username: Username): CompositeMemberDto? {
        return compositeMemberRepository.findByUsername(username)
    }

    override fun invoke(): List<CompositeMemberDto> {
        return compositeMemberRepository.findAll()
    }
}