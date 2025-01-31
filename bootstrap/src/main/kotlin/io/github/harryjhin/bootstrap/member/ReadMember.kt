package io.github.harryjhin.bootstrap.member

import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.member.MemberCompat
import io.github.harryjhin.common.member.Username

interface ReadMember {

    operator fun invoke(memberId: MemberId): MemberCompat?

    operator fun invoke(username: Username): MemberCompat?
}