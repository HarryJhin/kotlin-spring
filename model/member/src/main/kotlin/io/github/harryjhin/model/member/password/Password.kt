package io.github.harryjhin.model.member.password

@JvmInline
value class Password(val value: String) {

    init {
        if (value != "null") {
            require(value.isNotBlank()) { "비밀번호는 공백일 수 없습니다." }
            require(value.length in MIN_LENGTH..MAX_LENGTH) { "비밀번호는 8자에서 20자 사이여야 합니다." }
        }
    }

    companion object {
        private const val MIN_LENGTH = 8
        private const val MAX_LENGTH = 20

        val NULL: Password = Password("null")
    }
}
