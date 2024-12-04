package io.github.harryjhin.domain.member.projection

interface PasswordProjection {
    val id: Long
    val memberId: Long
    val password: String
}
