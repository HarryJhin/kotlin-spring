package io.github.harryjhin.domain.member.dto

import io.github.harryjhin.entity.member.MemberEntity
import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.core.email.toEmail
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.toMemberId
import java.time.Instant

data class MemberDto(
    val memberId: MemberId,
    val email: Email,
    val createdAt: Instant,
    val modifiedAt: Instant,
)

internal fun MemberEntity.toMember(): MemberDto = MemberDto(
    memberId = this.id.toMemberId(),
    email = this.email.toEmail(),
    createdAt = this.createdAt,
    modifiedAt = this.modifiedAt,
)