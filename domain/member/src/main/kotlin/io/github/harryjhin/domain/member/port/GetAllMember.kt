package io.github.harryjhin.domain.member.port

import io.github.harryjhin.entity.member.MemberInfoEntity

interface GetAllMember {

    operator fun invoke(): List<MemberInfoEntity>
}