package io.github.harryjhin.domain.member.port

import io.github.harryjhin.domain.member.projection.CompositeMemberDto
import io.github.harryjhin.entity.member.MemberInfoEntity
import io.github.harryjhin.entity.member.authentication.MemberAuthenticationEntity
import io.github.harryjhin.model.email.Email
import io.github.harryjhin.model.name.Name
import io.github.harryjhin.model.member.MemberId
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.member.EncodedPassword
import io.github.harryjhin.model.member.PasswordStrength
import io.github.harryjhin.model.member.RawPassword

interface SaveMember {

    operator fun invoke(request: Request): CompositeMemberDto

    operator fun invoke(
        builder: SaveMemberRequestBuilder = SaveMemberRequestBuilder(),
        buildToAction: SaveMemberRequestBuilder.() -> Unit,
    ): CompositeMemberDto

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
    var username: Username? = null,
    var rawPassword: RawPassword? = null,
) {
    fun build(): SaveMember.Request = SaveMember.Request(
        name = requireNotNull(name) { "name is required" },
        username = requireNotNull(username) { "username is required" },
        email = requireNotNull(email) { "email is required" },
        rawPassword = requireNotNull(rawPassword) { "rawPassword is required" },
    )
}

internal fun SaveMember.Request.toMemberEntity(): MemberInfoEntity = MemberInfoEntity {
    this@MemberInfoEntity.name = this@toMemberEntity.name
    this@MemberInfoEntity.email = this@toMemberEntity.email
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