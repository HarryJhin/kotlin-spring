package io.github.harryjhin.domain.member.projection

import com.querydsl.core.annotations.QueryProjection
import io.github.harryjhin.common.email.Email
import io.github.harryjhin.common.email.toEmail
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.id.toMemberId
import io.github.harryjhin.common.member.EncodedPassword
import io.github.harryjhin.common.member.SimpleMember
import io.github.harryjhin.common.member.Username
import io.github.harryjhin.common.member.toEncodedPassword
import io.github.harryjhin.common.member.toUsername
import io.github.harryjhin.common.name.Name
import io.github.harryjhin.common.name.toName

class MemberProjection @QueryProjection constructor(
    memberId: Long,
    name: String,
    email: String,
    username: String,
    password: String,
) {
    val memberId: MemberId = memberId.toMemberId()
    val name: Name = name.toName()
    val email: Email = email.toEmail()
    val username: Username = username.toUsername()
    val password: EncodedPassword = password.toEncodedPassword()

    fun toSimpleMember(): SimpleMember {
        return SimpleMember(
            memberId = memberId,
            name = name,
            email = email,
            username = username,
            password = password,
        )
    }
}