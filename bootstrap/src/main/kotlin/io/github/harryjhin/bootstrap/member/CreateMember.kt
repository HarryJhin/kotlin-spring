package io.github.harryjhin.bootstrap.member

import io.github.harryjhin.common.email.Email
import io.github.harryjhin.common.member.MemberCompat
import io.github.harryjhin.common.member.RawPassword
import io.github.harryjhin.common.member.Username
import io.github.harryjhin.common.member.toUsername
import io.github.harryjhin.common.name.Name

/**
 * ### 회원 생성
 *
 * - 회원 생성 요청을 처리합니다.
 *
 * ```kotlin
 * val member: MemberDomain = createMember {
 *     name = "테스터".toName()
 *     email = "tester@example.com".toEmail()
 *     rawPassword = "password".toRawPassword()
 * }
 * ```
 */
interface CreateMember {

    operator fun invoke(request: Request): MemberCompat

    operator fun invoke(
        builder: Request.Builder = Request.Builder(),
        buildToAction: Request.Builder.() -> Unit,
    ): MemberCompat {
        return builder.apply(buildToAction)
            .build()
            .run(::invoke)
    }

    data class Request(
        val name: Name,
        val email: Email,
        val username: Username,
        val rawPassword: RawPassword,
    ) {
        class Builder internal constructor(
            var name: Name? = null,
            var email: Email? = null,
            var rawPassword: RawPassword? = null,
        ) {
            internal fun build() = Request(
                name = requireNotNull(name) { "name is required" },
                email = requireNotNull(email) { "email is required" },
                username = requireNotNull(email?.value?.toUsername()) { "username is required" },
                rawPassword = requireNotNull(rawPassword) { "rawPassword is required" },
            )
        }
    }
}