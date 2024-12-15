package io.github.harryjhin.domain.member.projection

import com.querydsl.core.annotations.QueryProjection
import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.core.email.toEmail
import io.github.harryjhin.model.core.name.Name
import io.github.harryjhin.model.core.name.toName
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.member.toMemberId
import io.github.harryjhin.model.member.toUsername
import io.github.harryjhin.model.member.EncodedPassword
import io.github.harryjhin.model.member.toEncodedPassword

class CompositeMemberDto @QueryProjection constructor(
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
}