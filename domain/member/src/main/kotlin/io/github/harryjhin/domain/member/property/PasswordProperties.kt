package io.github.harryjhin.domain.member.property

import io.github.harryjhin.model.password.PasswordStrength
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "password")
class PasswordProperties(
    val strength: PasswordStrength,
)