package io.github.harryjhin.model.member.username

import java.util.regex.Pattern

@JvmInline
value class Username(val value: String) {

    init {
        require(value.isNotBlank()) { "사용자 이름은 공백일 수 없습니다." }
        require(PATTERN.matcher(value).matches()) { "사용자 이름은 4자 이상 20자 이하로 구성되어야 합니다." }
    }

    override fun toString(): String {
        return value
    }

    private companion object {
        val PATTERN: Pattern = Pattern.compile("^[a-zA-Z0-9]{4,20}$")
    }
}
