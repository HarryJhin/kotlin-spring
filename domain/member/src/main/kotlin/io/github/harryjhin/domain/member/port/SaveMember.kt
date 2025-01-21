package io.github.harryjhin.domain.member.port

import io.github.harryjhin.domain.member.projection.Member
import io.github.harryjhin.entity.member.MemberInfoEntity
import io.github.harryjhin.entity.member.authentication.MemberAuthenticationEntity
import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.name.Name
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.member.EncodedPassword
import io.github.harryjhin.model.member.PasswordStrength
import io.github.harryjhin.model.member.RawPassword
import io.github.harryjhin.model.member.toUsername

interface SaveMember {

    operator fun invoke(request: Request): Member

    operator fun invoke(
        builder: SaveMemberRequestBuilder = SaveMemberRequestBuilder(),
        buildToAction: SaveMemberRequestBuilder.() -> Unit,
    ): Member

    data class Request(
        val name: Name,
        val email: Email,
        val username: Username,
        val rawPassword: RawPassword,
    )
}

class SaveMemberRequestBuilder internal constructor(
    var name: Name? = null,
    var email: Email? = null,
    var rawPassword: RawPassword? = null,
) {
    fun build(): SaveMember.Request = SaveMember.Request(
        name = requireNotNull(name) { "name is required" },
        email = requireNotNull(email) { "email is required" },
        username = requireNotNull(email?.value?.toUsername()) { "username is required" },
        rawPassword = requireNotNull(rawPassword) { "rawPassword is required" },
    )
}

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