package io.github.harryjhin.domain.member.port

import io.github.harryjhin.domain.member.dto.MemberDto

interface GetAllMember {

    operator fun invoke(): List<MemberDto>
}