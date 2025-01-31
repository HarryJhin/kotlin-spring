package io.github.harryjhin.domain.member.projection

import com.querydsl.core.annotations.QueryProjection
import io.github.harryjhin.common.email.Email
import io.github.harryjhin.common.email.toEmail
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.id.toMemberId
import io.github.harryjhin.common.member.EncodedPassword
import io.github.harryjhin.common.member.Member
import io.github.harryjhin.common.member.MemberCompat
import io.github.harryjhin.common.member.Username
import io.github.harryjhin.common.member.toEncodedPassword
import io.github.harryjhin.common.member.toUsername
import io.github.harryjhin.common.name.Name
import io.github.harryjhin.common.name.toName

class MemberProjection @QueryProjection internal constructor(
    memberId: Long,
    name: String,
    email: String,
    username: String,
    password: String,
) : MemberCompat {

    override val memberId: MemberId = memberId.toMemberId()
    override val name: Name = name.toName()
    override val email: Email = email.toEmail()
    override val username: Username = username.toUsername()
    override val password: EncodedPassword = password.toEncodedPassword()

    fun toMember() = Member(
        memberId = memberId,
        name = name,
        email = email,
        username = username,
        password = password,
    )
}