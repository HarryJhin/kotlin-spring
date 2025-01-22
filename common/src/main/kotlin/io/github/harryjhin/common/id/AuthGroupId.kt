package io.github.harryjhin.common.id

@JvmInline
value class AuthGroupId(val value: Long) {

    init {
        require(value > 0) { "auth_group.id는 0보다 커야합니다." }
    }

    override fun toString(): String = "$value"
}

fun Long.toAuthGroupId(): AuthGroupId = AuthGroupId(this)