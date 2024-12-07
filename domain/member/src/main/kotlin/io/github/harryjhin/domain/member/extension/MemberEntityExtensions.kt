package io.github.harryjhin.domain.member.extension

import io.github.harryjhin.domain.member.model.Member
import io.github.harryjhin.entity.member.MemberEntity
import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username

internal fun MemberEntity.toMember(): Member = Member(
    id = MemberId(this.id),
    username = Username(this.username),
    email = Email(this.email),
)
