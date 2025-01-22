package io.github.harryjhin.common.member

import io.github.harryjhin.common.email.Email
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.name.Name

sealed class Member {
    abstract val memberId: MemberId
    abstract val name: Name
    abstract val username: Username
    abstract val password: EncodedPassword
    abstract val email: Email
}

data class SimpleMember(
    override val memberId: MemberId,
    override val name: Name,
    override val username: Username,
    override val password: EncodedPassword,
    override val email: Email,
) : Member()