package io.github.harryjhin.domain.member.port

import io.github.harryjhin.domain.member.projection.CompositeMemberDto

interface GetAllMember {

    operator fun invoke(): List<CompositeMemberDto>
}