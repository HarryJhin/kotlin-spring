package io.github.harryjhin.domain.member.projection

import com.querydsl.core.annotations.QueryProjection
import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.member.EncodedPassword
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.member.toMemberId
import io.github.harryjhin.model.name.Name

class Member @QueryProjection constructor(
    memberId: Long,
    val name: Name,
    val email: Email,
    val username: Username,
    val password: EncodedPassword
) {
    val memberId: MemberId = memberId.toMemberId()
}