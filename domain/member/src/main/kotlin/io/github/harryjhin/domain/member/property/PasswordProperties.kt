package io.github.harryjhin.domain.member.property

import io.github.harryjhin.model.member.PasswordStrength
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "password")
class PasswordProperties(
    val strength: PasswordStrength,
)