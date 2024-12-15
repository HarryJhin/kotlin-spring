package io.github.harryjhin.domain.member.dto

import io.github.harryjhin.entity.member.MemberEntity
import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.core.email.toEmail
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.member.toMemberId
import io.github.harryjhin.model.member.toUsername
import java.time.Instant

data class MemberDto(
    val memberId: MemberId,
    val username: Username,
    val email: Email,
    val createdAt: Instant,
    val modifiedAt: Instant,
)

internal fun MemberEntity.toMember(): MemberDto = MemberDto(
    memberId = this.id.toMemberId(),
    username = this.username.toUsername(),
    email = this.email.toEmail(),
    createdAt = this.createdAt,
    modifiedAt = this.modifiedAt,
)