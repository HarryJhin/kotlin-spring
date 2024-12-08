package io.github.harryjhin.model.password

@JvmInline
value class PasswordId(val value: Long) {

    init {
        require(value > 0) { "password.id는 0보다 커야 합니다." }
    }

    override fun toString(): String {
        return "$value"
    }
}

fun Long.toPasswordId(): PasswordId = PasswordId(this)
