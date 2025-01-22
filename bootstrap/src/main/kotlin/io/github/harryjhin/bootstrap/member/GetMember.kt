package io.github.harryjhin.bootstrap.member

import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.member.Member
import io.github.harryjhin.common.member.Username

interface GetMember {

    operator fun invoke(memberId: MemberId): Member?

    operator fun invoke(username: Username): Member?
}