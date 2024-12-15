package io.github.harryjhin.domain.member.adaptor

import io.github.harryjhin.domain.member.dto.MemberDto
import io.github.harryjhin.domain.member.dto.toMember
import io.github.harryjhin.domain.member.port.GetAllMember
import io.github.harryjhin.domain.member.port.GetMember
import io.github.harryjhin.domain.member.port.SaveMember
import io.github.harryjhin.domain.member.port.SaveMemberRequestBuilder
import io.github.harryjhin.domain.member.port.toMemberEntity
import io.github.harryjhin.domain.member.repository.MemberRepository
import io.github.harryjhin.entity.member.MemberEntity
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = false)
class DefaultMemberAdaptor(
    private val memberRepository: MemberRepository,
) : SaveMember, GetMember, GetAllMember {

    @Transactional
    override fun invoke(request: SaveMember.Request): MemberDto {
        return request.toMemberEntity()
            .run(memberRepository::save)
            .toMember()
    }

    override fun invoke(
        builder: SaveMemberRequestBuilder,
        buildToAction: SaveMemberRequestBuilder.() -> Unit
    ): MemberDto {
        return builder.apply(buildToAction)
            .build()
            .run(::invoke)
    }

    override fun invoke(memberId: MemberId): MemberDto? {
        return memberRepository.findById(memberId)
            ?.toMember()
    }

    override fun invoke(username: Username): MemberDto? {
        return memberRepository.findByUsername(username)
            ?.toMember()
    }

    override fun invoke(): List<MemberDto> {
        return memberRepository.findAll()
            .map(MemberEntity::toMember)
    }
}