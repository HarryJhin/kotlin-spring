package io.github.harryjhin.domain.member.extension

import io.github.harryjhin.bootstrap.member.CreateMember
import io.github.harryjhin.entity.member.MemberEntity
import io.github.harryjhin.entity.member.MemberAuthenticationEntity
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.member.EncodedPassword
import io.github.harryjhin.common.member.PasswordStrength

internal fun CreateMember.Request.toMemberEntity(): MemberEntity = MemberEntity {
    this@MemberEntity.name = this@toMemberEntity.name
    this@MemberEntity.email = this@toMemberEntity.email
}

internal fun CreateMember.Request.toMemberAuthenticationEntity(
    memberId: MemberId,
    strength: PasswordStrength,
    password: EncodedPassword,
): MemberAuthenticationEntity = MemberAuthenticationEntity {
    this@MemberAuthenticationEntity.memberId = memberId
    this@MemberAuthenticationEntity.username = this@toMemberAuthenticationEntity.username
    this@MemberAuthenticationEntity.strength = strength
    this@MemberAuthenticationEntity.password = password
}