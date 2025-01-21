package io.github.harryjhin.domain.member.port

import io.github.harryjhin.entity.member.MemberInfoEntity
import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username

interface GetMember {

    operator fun invoke(memberId: MemberId): MemberInfoEntity?

    operator fun invoke(username: Username): MemberInfoEntity?

    operator fun invoke(email: Email): MemberInfoEntity?
}