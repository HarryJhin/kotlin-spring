package io.github.harryjhin.model.core.email

@JvmInline
value class Email(val value: String) {

    val username: String
        get() = value.substringBefore("@")
    val domain: String
        get() = value.substringAfter("@")

    init {
        if (value != "null") {
            require(value.isNotBlank()) { "이메일은 공백일 수 없습니다." }
            require(value.length <= MAX_LENGTH) { "이메일은 100자를 넘을 수 없습니다." }
            require(value.contains("@")) { "이메일은 '@'를 포함해야 합니다." }
            require(DOMAINS.any { value.endsWith(it) }) { "지원하지 않는 도메인입니다." }
            // username은 5자에서 64자 사이
            require(username.length in 5..64) { "아이디는 5자에서 64자 사이여야 합니다." }
        }
    }

    companion object {
        private const val MAX_LENGTH = 100
        private val DOMAINS = setOf(
            "gmail.com",
            "naver.com",
        )
        val NULL: Email = Email("null")
    }
}