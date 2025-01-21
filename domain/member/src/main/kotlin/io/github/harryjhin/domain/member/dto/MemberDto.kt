package io.github.harryjhin.domain.member.dto

import io.github.harryjhin.entity.member.MemberInfoEntity
import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.email.toEmail
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.toMemberId
import java.time.Instant

data class MemberDto(
    val memberId: MemberId,
    val email: Email,
    val createdAt: Instant,
    val modifiedAt: Instant,
)

internal fun MemberInfoEntity.toMember(): MemberDto = MemberDto(
    memberId = this.id.toMemberId(),
    email = this.email,
    createdAt = this.createdAt,
    modifiedAt = this.modifiedAt,
)