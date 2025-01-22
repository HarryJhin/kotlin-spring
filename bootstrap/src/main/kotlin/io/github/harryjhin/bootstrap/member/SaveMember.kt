package io.github.harryjhin.bootstrap.member

import io.github.harryjhin.common.email.Email
import io.github.harryjhin.common.member.Member
import io.github.harryjhin.common.member.RawPassword
import io.github.harryjhin.common.member.Username
import io.github.harryjhin.common.member.toUsername
import io.github.harryjhin.common.name.Name

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