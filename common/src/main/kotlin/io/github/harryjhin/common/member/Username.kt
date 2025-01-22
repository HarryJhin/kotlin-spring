package io.github.harryjhin.common.member

import java.util.regex.Pattern

@JvmInline
value class Username(val value: String) {

    init {
        require(value.isNotBlank()) { "`username`은 공백일 수 없습니다." }
        require(value.length <= MAX_LENGTH) { "`username`은 4자 이상 20자 이하로 구성되어야 합니다." }
        require(PATTERN.matcher(value).matches()) { "`username`은 영문 대소문자, 숫자, 특수문자(`._%+-`)로 구성되어야 합니다." }
    }

    override fun toString(): String {
        return value
    }

    private companion object {
        const val MAX_LENGTH: Int = 254
        val PATTERN: Pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
    }
}

fun String.toUsername(): Username = Username(this)