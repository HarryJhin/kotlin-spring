package io.github.harryjhin.common.id

@JvmInline
value class MemberId(val value: Long) {

    init {
        require(value > 0) { "member.id는 0보다 커야합니다." }
    }

    override fun toString(): String = "$value"
}

fun Long.toMemberId(): MemberId = MemberId(this)
