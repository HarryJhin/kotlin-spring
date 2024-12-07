package io.github.harryjhin.sign.request

import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.password.RawPassword

data class SignUpRequest(
    val username: Username,
    val email: Email,
    val rawPassword: RawPassword,
)
