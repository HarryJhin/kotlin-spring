package io.github.harryjhin.domain.member.model

import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username

data class Member(
    val id: MemberId,
    val username: Username,
    val email: Email,
)
