package io.github.harryjhin.common.member

import java.util.regex.Pattern

@JvmInline
value class RawPassword(val value: String) {

    init {
        require(value.isNotBlank()) { "비밀번호는 공백일 수 없습니다." }
        require(PATTERN.matcher(value).matches()) { "비밀번호는 8자 이상 64자 이하로 구성되어야 합니다." }
    }

    override fun toString(): String {
        return "*****"
    }

    private companion object {
        val PATTERN: Pattern = Pattern.compile("^.{8,64}$")
    }
}

fun String.toRawPassword(): RawPassword = RawPassword(this)
