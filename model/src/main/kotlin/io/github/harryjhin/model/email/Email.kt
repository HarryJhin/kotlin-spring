package io.github.harryjhin.model.email

import io.github.harryjhin.model.valid
import java.util.regex.Pattern

@JvmInline
value class Email(val value: String) {

    init {
        valid(value.isNotBlank()) { "email.not-blank" }
        valid(value.length <= MAX_LENGTH, { "email.max-length" }, MAX_LENGTH)
        require(PATTERN.matcher(value).matches()) { "`email`은 영문 대소문자, 숫자, 특수문자(`._%+-`)로 구성되어야 합니다." }
    }

    override fun toString(): String {
        return value
    }

    companion object {
        const val MAX_LENGTH: Int = 254

        /**
         * - `a-zA-Z0-9._%+-`: 이메일의 로컬 파트는 영문 대소문자, 숫자, 특수문자(`._%+-`)로 구성됩니다.
         * - `@`: 이메일의 로컬 파트와 도메인 파트를 구분하는 기호로, 반드시 포함되어야 합니다.
         * - `a-zA-Z0-9.-`: 이메일의 도메인 파트는 영문 대소문자, 숫자, 특수문자(`.-`)로 구성됩니다.
         */
        private val PATTERN: Pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
    }
}

fun String.toEmail(): Email = Email(this)
