package io.github.harryjhin.domain.member.port

import io.github.harryjhin.domain.member.dto.MemberDto
import io.github.harryjhin.entity.member.MemberEntity
import io.github.harryjhin.model.core.email.Email
import io.github.harryjhin.model.member.Username
import io.github.harryjhin.model.password.RawPassword

interface SaveMember {

    operator fun invoke(request: Request): MemberDto

    operator fun invoke(
        builder: SaveMemberRequestBuilder = SaveMemberRequestBuilder(),
        buildToAction: SaveMemberRequestBuilder.() -> Unit,
    ): MemberDto

    data class Request(
        val username: Username,
        val email: Email,
        val rawPassword: RawPassword,
    )
}

class SaveMemberRequestBuilder internal constructor(
    var username: Username? = null,
    var email: Email? = null,
    var rawPassword: RawPassword? = null,
) {
    fun build(): SaveMember.Request = SaveMember.Request(
        username = requireNotNull(username),
        email = requireNotNull(email),
        rawPassword = requireNotNull(rawPassword),
    )
}

internal fun SaveMember.Request.toMemberEntity(): MemberEntity = MemberEntity {
    this@MemberEntity.username = this@toMemberEntity.username
    this@MemberEntity.email = this@toMemberEntity.email
}