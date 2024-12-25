package io.github.harryjhin.sign.request

import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.name.Name
import io.github.harryjhin.model.member.RawPassword
import io.github.harryjhin.model.member.Username

data class SignUpRequest(
    val name: Name,
    val email: Email,
    val rawPassword: RawPassword,
)