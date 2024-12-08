package io.github.harryjhin.domain.member.model

import io.github.harryjhin.entity.password.PasswordEntity
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.toMemberId
import io.github.harryjhin.model.password.*
import java.time.Instant

data class Password(
    val passwordId: PasswordId,
    val memberId: MemberId,
    val strength: PasswordStrength,
    val encodedPassword: EncodedPassword,
    val createdAt: Instant,
    val modifiedAt: Instant,
)

internal fun PasswordEntity.toModel() = Password(
    passwordId = id.toPasswordId(),
    memberId = memberId.toMemberId(),
    strength = strength.toPasswordStrength(),
    encodedPassword = password.toEncodedPassword(),
    createdAt = createdAt,
    modifiedAt = modifiedAt,
)
