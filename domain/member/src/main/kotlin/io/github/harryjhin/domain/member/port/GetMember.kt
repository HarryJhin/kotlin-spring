package io.github.harryjhin.domain.member.port

import io.github.harryjhin.domain.member.dto.MemberDto
import io.github.harryjhin.domain.member.projection.CompositeMemberDto
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username

interface GetMember {

    operator fun invoke(memberId: MemberId): CompositeMemberDto?

    operator fun invoke(username: Username): CompositeMemberDto?
}