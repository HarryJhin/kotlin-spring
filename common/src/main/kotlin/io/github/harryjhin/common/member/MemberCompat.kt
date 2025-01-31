package io.github.harryjhin.common.member

import io.github.harryjhin.common.email.Email
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.name.Name

interface MemberCompat {
    val memberId: MemberId
    val name: Name
    val username: Username
    val password: EncodedPassword
    val email: Email
}

data class Member(
    override val memberId: MemberId,
    override val name: Name,
    override val username: Username,
    override val password: EncodedPassword,
    override val email: Email,
) : MemberCompat