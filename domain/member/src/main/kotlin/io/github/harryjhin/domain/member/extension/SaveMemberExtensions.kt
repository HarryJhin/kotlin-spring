package io.github.harryjhin.domain.member.extension

import io.github.harryjhin.bootstrap.member.SaveMember
import io.github.harryjhin.entity.member.MemberInfoEntity
import io.github.harryjhin.entity.member.MemberAuthenticationEntity
import io.github.harryjhin.common.id.MemberId
import io.github.harryjhin.common.member.EncodedPassword
import io.github.harryjhin.common.member.PasswordStrength

internal fun SaveMember.Request.toMemberInfoEntity(): MemberInfoEntity = MemberInfoEntity {
    this@MemberInfoEntity.name = this@toMemberInfoEntity.name
    this@MemberInfoEntity.email = this@toMemberInfoEntity.email
}

internal fun SaveMember.Request.toMemberAuthenticationEntity(
    memberId: MemberId,
    strength: PasswordStrength,
    password: EncodedPassword,
): MemberAuthenticationEntity = MemberAuthenticationEntity {
    this@MemberAuthenticationEntity.memberId = memberId
    this@MemberAuthenticationEntity.username = this@toMemberAuthenticationEntity.username
    this@MemberAuthenticationEntity.strength = strength
    this@MemberAuthenticationEntity.password = password
}