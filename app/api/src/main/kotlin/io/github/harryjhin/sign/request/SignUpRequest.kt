package io.github.harryjhin.sign.request

import io.github.harryjhin.common.email.Email
import io.github.harryjhin.common.name.Name
import io.github.harryjhin.common.member.RawPassword

data class SignUpRequest(
    val name: Name,
    val email: Email,
    val rawPassword: RawPassword,
)